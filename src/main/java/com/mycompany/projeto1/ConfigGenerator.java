package com.mycompany.projeto1;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.file.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ConfigGenerator {

    public static Path gerarConfigHost(
            Path templatePath,
            Path ConfDir,
            String hostName,
            String caPem,
            String certPem,
            String keyPem,
            String nebulaIp,
            boolean isLighthouse,
            String publicIp,
            Map<String, String> lighthouseMap) throws IOException {
          /*
        Objects.requireNonNull(templatePath, "templatePath");
        Objects.requireNonNull(ConfDir, "tempDir");
        Objects.requireNonNull(hostName, "hostName");
        Objects.requireNonNull(caPem, "caPem");
        Objects.requireNonNull(certPem, "certPem");
        Objects.requireNonNull(keyPem, "keyPem");
        Objects.requireNonNull(nebulaIp, "nebulaIp");
        Objects.requireNonNull(lighthouseMap, "lighthouseMap");
          */
          
          if (isLighthouse && (publicIp == null || publicIp.isBlank())) {
            throw new IllegalArgumentException("publicIp é obrigatório para lighthouse");
        }

        Yaml yaml = new Yaml();
        Map<String, Object> root = null;
        try (InputStream in = Files.newInputStream(templatePath)) {
            root = yaml.load(in);
            if (root == null) {
                root = new LinkedHashMap<>();
            }
        } catch (IOException ex) {
            Logger.getLogger(ConfigGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

        Map<String, Object> pki = getOrCreate(root, "pki");
        pki.put("ca", caPem.trim());
        pki.put("cert", certPem.trim());
        pki.put("key", (keyPem.equals("") ? "" : keyPem.trim()));

        Map<String, Object> shm = getOrCreate(root, "static_host_map");
        for (Map.Entry<String, String> e : lighthouseMap.entrySet()) {
            String nebIp = e.getKey();
            String pubIp = e.getValue();
            shm.put(nebIp, List.of(pubIp));
        }

        Map<String, Object> lighthouse = getOrCreate(root, "lighthouse");
        lighthouse.put("am_lighthouse", isLighthouse);
        lighthouse.putIfAbsent("interval", 60);

        if (isLighthouse) {
            lighthouse.remove("hosts");
        } else {
            List<String> hosts = new ArrayList<>(lighthouseMap.keySet());
            hosts.remove(nebulaIp);
            lighthouse.put("hosts", hosts);
        }

        Map<String, Object> tun = getOrCreate(root, "tun");
        if (tun.get("routes") == null) {
            tun.remove("routes");
        }

        Connection conn = FuncoesMain.conn;
        String debugSql = """
                          Select * from Hosts_groups
""";
        try (Statement st = conn.createStatement(); ResultSet r = st.executeQuery(debugSql)) {
            while (r.next()) {
                System.out.println("ID: " + r.getInt("ID"));
                System.out.println("ID_Hosts: " + r.getString("ID_Host"));
                System.out.println("ID_Group: " + r.getString("ID_Group"));
                System.out.println("_____________________________________");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao verificar todos as ligações de hosts ao grupos!");
        }

        List<Grupo> gruposDoHost = new ArrayList<>();
        System.out.println(hostName);

        String sql = "SELECT g.Inbound AS inbound, g.Outbound AS outbound FROM Groups g JOIN Hosts_groups hg ON g.ID = hg.ID_Group JOIN Hosts h ON h.ID = hg.ID_Host WHERE lower(h.nome) = LOWER(?) AND h.Apagado = 0";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hostName.trim());
            System.out.println("Host pesquisado: [" + hostName.trim() + "]");
            try (ResultSet rs = ps.executeQuery()) {
                System.out.println(ps.toString());
                while (rs.next()) {
                    String inbound = rs.getString("inbound");
                    String outbound = rs.getString("outbound");
                    gruposDoHost.add(new Grupo(inbound, outbound));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro a procura do host:" + sql + " " + hostName.trim());
        }

        List<Map<String, Object>> inboundRules = new ArrayList<>();
        List<Map<String, Object>> outboundRules = new ArrayList<>();

        for (Grupo grupo : gruposDoHost) {
            inboundRules.addAll(FirewallRuleParser.parseRules(grupo.getInbound()));
            outboundRules.addAll(FirewallRuleParser.parseRules(grupo.getOutbound()));
        }

        if (inboundRules.isEmpty()) {
            Map<String, Object> defaultInbound = new LinkedHashMap<>();
            defaultInbound.put("port", "any");
            defaultInbound.put("proto", "any");
            defaultInbound.put("host", "any");
            inboundRules.add(defaultInbound);
        }
        if (outboundRules.isEmpty()) {
            Map<String, Object> defaultOutbound = new LinkedHashMap<>();
            defaultOutbound.put("port", "any");
            defaultOutbound.put("proto", "any");
            defaultOutbound.put("host", "any");
            outboundRules.add(defaultOutbound);
        }

        Map<String, Object> firewall = getOrCreate(root, "firewall");
        firewall.put("inbound", inboundRules);
        firewall.put("outbound", outboundRules);

        DumperOptions opts = new DumperOptions();
        opts.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        opts.setPrettyFlow(true);
        Yaml outYaml = new Yaml(opts);

        Files.createDirectories(ConfDir);
        Path out = ConfDir.resolve("Config_" + hostName + ".yml");

        try (Writer w = Files.newBufferedWriter(
                out,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING)) {
            outYaml.dump(root, w);
        }
        return out;
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> getOrCreate(Map<String, Object> parent, String key) {
        return (Map<String, Object>) parent.computeIfAbsent(key, k -> new LinkedHashMap<>());
    }
}
