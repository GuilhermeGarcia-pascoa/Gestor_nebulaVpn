package com.mycompany.projeto1;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import static com.mycompany.projeto1.InfoHosts.TxtNome;
import static com.mycompany.projeto1.MenuPrincipal.TabelaHosts;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.URISyntaxException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

public class FuncoesMain {

    private static Path getTemplatePath() throws IOException {
        InputStream in = FuncoesMain.class.getResourceAsStream("/ConfigHost.yml");
        if (in == null) {
            throw new IllegalStateException("ConfigHost.yml não encontrado dentro do JAR");
        }

        Path tmp = Files.createTempFile("ConfigHost-", ".yml");
        Files.copy(in, tmp, StandardCopyOption.REPLACE_EXISTING);
        return tmp;
    }

    private static final Path BASE_DIR;

    static {
        Path tmp;
        try {
            tmp = Paths.get(FuncoesMain.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI())
                    .getParent();
        } catch (URISyntaxException e) {
            tmp = Paths.get("").toAbsolutePath();
        }
        BASE_DIR = tmp;
    }
    public static Connection conn;
    public static Statement stmt;
    public static Path tempDir;
    public static Path ConfDir;
    static Valores vlr;

    public static void main(String[] args) {

        LigarBaseDados();
        try {
            stmt.executeUpdate("""
                                            CREATE TABLE IF NOT EXISTS Hosts (
                                                ID INTEGER PRIMARY KEY,
                                                nome TEXT NOT NULL,
                                                ip TEXT NOT NULL,
                                                ipPublico TEXT NOT NULL,
                                                validade TEXT NOT NULL,
                                                Descricao TEXT NOT NULL,
                                                Certificado_Value TEXT NOT NULL,
                                                CertificadoAutoridade_Value TEXT NOT NULL,
                                                PublicKey_Value TEXT NOT NULL,
                                                PrivateKey_Value TEXT,
                                                IsLighthouse INTEGER CHECK (IsLighthouse IN (0, 1)),
                                                Notbefore DATETIME NOT NULL DEFAULT (datetime('now')),
                                                Apagado INTEGER CHECK (Apagado IN(0, 1))
                                           );
                                       """);
            stmt.execute("""
                                            CREATE TABLE IF NOT EXISTS Groups (
                                                ID INTEGER PRIMARY KEY,
                                                Nome TEXT NOT NULL,
                                                Outbound TEXT NOT NULL,
                                                Inbound TEXT NOT NULL
                                            );
                         """);
            ResultSet rs = ExecutarComandoSql("SELECT * FROM Groups");
            if (!rs.next()) {
                stmt.execute("INSERT INTO Groups (Nome, Outbound, Inbound) VALUES ('Admin', 'any|any|any<Host>', 'any|any|any<Host>');");
            }

            stmt.execute("""
                         CREATE TABLE IF NOT EXISTS Hosts_groups(
                                     ID INTEGER PRIMARY KEY,
                                     ID_Host INTEGER,
                                     ID_Group INTEGER
                         );
            """);

            stmt.execute("""
                         CREATE TABLE IF NOT EXISTS Cache(
                            ID INTEGER PRIMARY KEY,
                            Nome TEXT NOT NULL,
                            Valor TEXT NOT NULL
                         );
                         """);

            vlr = new Valores();

            tempDir = BASE_DIR.resolve("temp");
            ConfDir = BASE_DIR.resolve("Configs");

            rs = ExecutarComandoSql("SELECT Valor FROM Cache WHERE Nome = 'Tema'");
            String tema;
            if (rs.next()) {
                tema = rs.getString("Valor");
            } else {
                tema = "Light";
                stmt.execute("INSERT INTO Cache (Nome, Valor) VALUES ('Tema', '" + tema + "')");
            }
            Tema(tema);
            MenuPrincipal In = new MenuPrincipal();
            In.setLocationRelativeTo(null);
            In.setVisible(true);

            if (vlr.getCaminhoNe() == null) {
                JOptionPane.showMessageDialog(null, "Para iniciar primeiro insira o caminho para o seu nebula\nVá para gerir caminhos", "Aviso!", JOptionPane.WARNING_MESSAGE);
                MenuPrincipal.Gerirca.setVisible(true);
            }
            if (!Files.exists(tempDir)) {
                Files.createDirectories(tempDir);
            }
            if (!Files.exists(ConfDir)) {
                Files.createDirectories(ConfDir);
            }
        } catch (HeadlessException | IOException | SQLException e) {
            System.out.println("Teste erro " + e);
        }

    }

    public static void Tema(String tema) {
        try {
            if ("Dark".equals(tema)) {
                UIManager.setLookAndFeel(new FlatDarkLaf());
            } else if ("Light".equals(tema)) {
                UIManager.setLookAndFeel(new FlatLightLaf());
            }
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("Erro ao deixar bonito: " + e);
        }

    }

    public String tirar_Mascara(String ip) {
        return ip.substring(0, ip.length() - 3);
    }

    public static boolean IsWin() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

    public static void CriarHost(String nome, String ip, boolean IsLighthouse, String CaminhoNe, String CaminhoCa, String Horas, String Descricao, String ipPub, Date DataValidade) {
        try {
            Path crtFile = tempDir.resolve(nome + ".crt");
            Path keyFile = tempDir.resolve(nome + ".key");
            Path pubFile = tempDir.resolve(nome + ".pub");

            if (CriarHost.TxtChavePublica.getText().isBlank()) {
                if (IsWin()) {
                    new ProcessBuilder(vlr.getCaminhoNe(), "keygen", "-out-key", keyFile.toString(), "-out-pub", pubFile.toString())
                            .inheritIO().start().waitFor();
                } else {
                    new ProcessBuilder(vlr.getCaminhoNe(), "keygen", "-out-key", keyFile.toString(), "-out-pub", pubFile.toString())
                            .inheritIO().start().waitFor();
                }
            } else {
                Files.writeString(pubFile, CriarHost.TxtChavePublica.getText(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                keyFile = null;
            }
            ProcessBuilder pb = new ProcessBuilder(vlr.getCaminhoNe(), "sign",
                    "-name", nome,
                    "-ip", ip,
                    "-duration", Horas,
                    "-groups", CriarHost.ComboGrupos.getItemAt(CriarHost.ComboGrupos.getSelectedIndex()),
                    "-out-crt", crtFile.toString(),
                    "-in-pub", pubFile.toString(),
                    "-ca-crt", vlr.getCaminhoCa(),
                    "-ca-key", obterCaminhoCaKey(CaminhoCa));

            Process process = pb.start();
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String linha;
            while ((linha = errorReader.readLine()) != null) {
                if (linha.contains("certificate expires after signing certificate")) {
                    JOptionPane.showMessageDialog(null, "A validade do seu host excede a validade do seu certificado de autoridade", "Error", JOptionPane.ERROR_MESSAGE);

                }
                return;
            }

            criarBd(pubFile.toString(), keyFile, crtFile, vlr.getCaminhoCa(), nome, Descricao, ip, Horas, IsLighthouse, ipPub, DataValidade);

        } catch (SQLException | IOException | InterruptedException e) {
            System.out.println("Erro " + e);
            System.out.println(e.toString());
            if (e.toString().endsWith("O sistema não conseguiu localizar o ficheiro especificado")) {
                JOptionPane.showMessageDialog(null, "O aplicativo nebula-cert não foi encontrado neste caminho", "Cuidado", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void ReiniciarApp() {
        try {
            String javaBin = System.getProperty("java.home") + "/bin/java";
            String jarPath = new java.io.File(
                    FuncoesMain.class.getProtectionDomain().getCodeSource().getLocation().toURI()
            ).getPath();

            Process  p = new ProcessBuilder(javaBin, "-jar", jarPath)
            .start();
            
             p.waitFor(1, java.util.concurrent.TimeUnit.SECONDS);
             
        } catch (IOException | URISyntaxException e) {
            System.out.println("Erro ao reiniciar : " + e);
        } catch (InterruptedException ex) {
            Logger.getLogger(FuncoesMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.exit(0);
    }

    public static void LigarBaseDados() {
        try {
            String currentDir = new File(".").getCanonicalPath();
            String url = "jdbc:sqlite:" + currentDir + File.separator + "BdDadosGestorNebula.db";

            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
        } catch (SQLException | IOException e) {
            System.out.println("Erro ao conectar ao banco de dados : " + e);
        }
    }

    public static void LigarHostGroup(String NomeHost, String nomeGrupo) {
        try {
            ResultSet rs = ExecutarComandoSql("SELECT ID FROM Hosts WHERE nome = '" + NomeHost + "'");
            int idHost = rs.getInt("ID");

            rs = ExecutarComandoSql("SELECT ID FROM Groups WHERE Nome = '" + nomeGrupo + "'");
            int idGroup = rs.getInt("ID");
            System.out.println(NomeHost + " " + nomeGrupo);

            ExecutarComandoSql("INSERT INTO Hosts_groups (ID_Host, ID_Group) VALUES ('" + idHost + "', '" + idGroup + "') ");

        } catch (SQLException e) {
            System.out.println("Erro " + e);
        }
    }

    public static void criarBd(String pubFile, Path keyFile, Path crtFile, String CaminhoCa, String nome, String Descricao, String ip, String horas, boolean IsLighthouse, String ipPub, Date DataValidade) throws FileNotFoundException, SQLException, IOException {
        Map<String, String> lighthouseMap = carregarLighthouseMap();
        if (!IsLighthouse && lighthouseMap.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    """
                    Ainda não existe nenhum host Lighthouse.
                    Crie primeiro um Lighthouse ou marque este como Lighthouse.
                    """,
                    "Configuração inválida",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String ChavePublica;
        if (CriarHost.TxtChavePublica.getText().isBlank()) {
            ChavePublica = Files.readString(Path.of(pubFile));
        } else {
            ChavePublica = CriarHost.TxtChavePublica.getText();
        }
        String ChavePrivadaV = "";
        if (keyFile != null && Files.exists(keyFile)) {
            ChavePrivadaV = Files.readString(keyFile);
        }

        String Certificado = Files.readString(crtFile);
        String CertificadoA = "";
        int lighthouse = (IsLighthouse ? 1 : 0);

        BufferedReader reader = new BufferedReader(new FileReader(CaminhoCa));
        String line;
        while ((line = reader.readLine()) != null) {
            CertificadoA += line + '\n';
        }
        String sql = """
    INSERT INTO Hosts (
        nome, ip, ipPublico, validade, Descricao,
        Certificado_Value, CertificadoAutoridade_Value,
        PublicKey_Value, PrivateKey_Value, IsLighthouse, Apagado
    ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)
""";

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try (PreparedStatement ps = stmt.getConnection().prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.setString(2, ip);
            ps.setString(3, ipPub);
            ps.setString(4, sdf.format(DataValidade) + "(" + horas + ")");
            ps.setString(5, Descricao);
            ps.setString(6, Certificado);
            ps.setString(7, CertificadoA);
            ps.setString(8, ChavePublica);
            ps.setString(9, ChavePrivadaV.isEmpty() ? null : ChavePrivadaV);
            ps.setInt(10, lighthouse);
            ps.executeUpdate();
        }
        try (PreparedStatement ps = stmt.getConnection().prepareStatement("UPDATE Hosts SET ipPublico = '' WHERE ipPublico = ':'")) {
            ps.executeUpdate();
        }

        carregarTabelaHosts();
        apagarTudoNaPasta(tempDir.toString());

        FuncoesMain.LigarHostGroup(CriarHost.TxtNome.getText(), CriarHost.ComboGrupos.getItemAt(CriarHost.ComboGrupos.getSelectedIndex()));
        Path gerado = ConfigGenerator.gerarConfigHost(getTemplatePath(), ConfDir, nome, CertificadoA, Certificado, (ChavePrivadaV == null ? "" : ChavePrivadaV), ip.split("/")[0], (lighthouse == 1), (":".equals(ipPub) ? "" : ipPub.split(":")[0]), lighthouseMap);
        System.out.println(gerado.toAbsolutePath());
    }

    public static Map<String, String> carregarLighthouseMap()
            throws SQLException {
        String sql = """
        SELECT ipPublico, ip
          FROM Hosts
         WHERE IsLighthouse = 1 AND Apagado is not 1
    """;

        Map<String, String> lighthouseMap = new LinkedHashMap<>();

        ResultSet rs = ExecutarComandoSql(sql);

        while (rs.next()) {
            String ipNebula = rs.getString("IpPublico").trim();

            String ipPublico = rs.getString("ip")
                    .split("/")[0]
                    .trim();

            lighthouseMap.put(ipPublico, ipNebula);
        }

        return lighthouseMap;
    }

    public static void apagarTudoNaPasta(String nomeDaPasta) throws IOException {
        Path pasta = Paths.get(nomeDaPasta);

        if (Files.exists(pasta)) {
            Files.walkFileTree(pasta, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }

    public static String obterCaminhoCaKey(String caminhoCert) {
        if (caminhoCert.toLowerCase().endsWith(".crt")) {
            return caminhoCert.substring(0, caminhoCert.length() - 4) + ".key";
        }
        throw new IllegalArgumentException("O arquivo não termina com .crt" + caminhoCert);
    }

    public static void carregarTabelaGrupos() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[]{"Nome"});

        try {
            ResultSet rs = ExecutarComandoSql("SELECT Nome FROM Groups");
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("Nome")
                });
            }
        } catch (Exception e) {
            System.out.println("Erro " + e);
        }
        Grupos.TblGrupos.setModel(modelo);
    }

    public static void carregarTabelaHosts() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[]{"Nome", "IP", "Validade", "Descrição", "Lighthouse", "X"});

        try {
            ResultSet rs = ExecutarComandoSql("SELECT nome, ip, validade, Descricao, IsLighthouse FROM Hosts WHERE Apagado = 0 ORDER BY ID ");

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("nome"),
                    rs.getString("ip"),
                    rs.getString("validade"),
                    rs.getString("Descricao"),
                    rs.getInt("IsLighthouse") == 1 ? "Sim" : "Não",
                    "X"
                });
            }

        } catch (SQLException e) {
            System.out.println("Erro " + e);
        }

        TabelaHosts.setModel(modelo);
        int idxX = TabelaHosts.getColumnCount() - 1;

        TableColumn colX = TabelaHosts.getColumnModel().getColumn(idxX);

        int largura = 28;
        colX.setMinWidth(largura);
        colX.setMaxWidth(largura);
        colX.setPreferredWidth(largura);
        colX.setResizable(false);

        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);
        colX.setCellRenderer(centro);

        TabelaHosts.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
    }

    public static boolean TemCelulaVazia(JTable tabela) {
        TableModel model = tabela.getModel();
        for (int linha = 0; linha < model.getRowCount(); linha++) {
            for (int col = 0; col < model.getColumnCount(); col++) {
                Object valor = model.getValueAt(linha, col);

                if (valor == null || valor.toString().trim().isBlank()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void carregarGruposCriarHost() {
        try {
            ResultSet rs = ExecutarComandoSql("SELECT Nome FROM Groups");
            CriarHost.ComboGrupos.removeAllItems();
            while (rs.next()) {
                CriarHost.ComboGrupos.addItem(rs.getString("Nome"));
            }
            CriarHost.ComboGrupos.setSelectedIndex(-1);
        } catch (Exception e) {
            System.out.println("Erro " + e);
        }
    }

    public static ResultSet ExecutarComandoSql(String comando) throws SQLException {
        return stmt.executeQuery(comando);

    }

    public static void AutopreencherIp() throws SQLException {
        ResultSet rs;
        CriarHost.TxtIp.setText("");
        rs = FuncoesMain.ExecutarComandoSql("SELECT Valor FROM Cache WHERE NOME = 'GerarIpAuto'");
        if(!(rs.next() && rs.getString("Valor").equals("on")))
            return;
        String IpMaisUsado = "";
        String cidr = "";
        String baseIp = "";

        rs = FuncoesMain.ExecutarComandoSql("SELECT Valor FROM Cache WHERE Nome = 'FaixaAutomatica'");
        if (rs.next()) {
            String faixaAuto = rs.getString("Valor");

            if (faixaAuto.equals("nao")) {
                rs = FuncoesMain.ExecutarComandoSql("SELECT Valor FROM Cache WHERE Nome = 'IpFaixaAutomatica'");
                if (rs.next()) {
                    IpMaisUsado = rs.getString("Valor");
                }
            } else {
                rs = FuncoesMain.ExecutarComandoSql("SELECT ip, count(*) FROM Hosts WHERE Apagado = 0 GROUP BY ip ORDER BY count(*) DESC LIMIT 1;");
                if (rs.next()) {
                    IpMaisUsado = rs.getString("ip");
                }
            }
            if (IpMaisUsado.contains("/")) {
                int barra = IpMaisUsado.indexOf("/");
                cidr = IpMaisUsado.substring(barra);
                String[] partes = IpMaisUsado.substring(0, barra).split("\\.");
                baseIp = partes[0] + "." + partes[1] + "." + partes[2];
                System.out.println(baseIp);
            }

            Set<Integer> usados = new HashSet<>();
            rs = FuncoesMain.ExecutarComandoSql("SELECT ip from Hosts where ip LIKE '" + baseIp + ".%' ");
            while (rs.next()) {
                String ip = rs.getString("ip");
                if (ip.contains("/")) {
                    int barra = ip.indexOf("/");
                    String semCidr = ip.substring(0, barra);
                    String[] partes = semCidr.split("\\.");
                    int ultimo = Integer.parseInt(partes[3]);
                    usados.add(ultimo);
                }
            }

            int ipLivre = -1;
            rs = FuncoesMain.ExecutarComandoSql("SELECT Valor FROM Cache WHERE Nome = 'IndentificadorMin'");
            int ValorMinimo = Integer.parseInt(rs.getString("Valor"));
            rs = FuncoesMain.ExecutarComandoSql("SELECT Valor FROM Cache WHERE Nome = 'IndentificadorMax'");
            int ValorMaximo = Integer.parseInt(rs.getString("Valor"));
            for (int i = ValorMinimo; i < ValorMaximo; i++) {
                if (!usados.contains(i)) {
                    ipLivre = i;
                    break;
                }
            }
            if (ipLivre != -1 && baseIp != "") {
                CriarHost.TxtIp.setText(baseIp + "." + ipLivre);
                for (int i = 0; i < CriarHost.ComboMask.getComponentCount(); i++) {
                    if (CriarHost.ComboMask.getItemAt(i).equals(cidr)) {
                        CriarHost.ComboMask.setSelectedIndex(i);
                        break;
                    }
                }
            }
        }
    }

    public static boolean hostJaExisteNome(String nome) throws SQLException {
        ResultSet rs = ExecutarComandoSql("SELECT COUNT(*) FROM Hosts WHERE (nome = '" + nome + "') AND Apagado = 0");
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
        return false;
    }

    public static boolean hostJaExisteIp(String ip) throws SQLException {
        ResultSet rs = ExecutarComandoSql("SELECT COUNT(*) FROM Hosts WHERE (ip = '" + ip + "') AND Apagado = 0");
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
        return false;
    }

    public static boolean GrupoJaExiste(String nome) throws SQLException {

        ResultSet rs = ExecutarComandoSql("SELECT COUNT(*) FROM Groups WHERE Nome = '" + nome + "'");
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }

        return false;
    }

    public static void EscreverConfig(String caminho) throws IOException {
        InputStream input = Files.newInputStream(Paths.get(caminho));

        Yaml yaml = new Yaml();
        Object data = yaml.load(input);
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setIndent(2);
        options.setPrettyFlow(true);
        Yaml yamlFormatter = new Yaml(new BlockRepresenter(options));
        String formattedYaml = yamlFormatter.dump(data);
        InfoHosts.TxtAreaConifg.setText(formattedYaml);
        InfoHosts.TxtAreaConifg.setCaretPosition(0);
    }

    public static void RemoverLighthouseHosts(String IpNebula) {
        Yaml yaml = new Yaml();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(ConfDir, "*.yml")) {
            for (Path yml : stream) {
                Map<String, Object> root;
                try (InputStream in = Files.newInputStream(yml)) {
                    root = yaml.load(in);
                }
                if (root == null) {
                    continue;
                }

                boolean mudou = false;

                Map<String, Object> shm = (Map<String, Object>) root.get("static_host_map");
                if (shm != null && shm.remove(IpNebula) != null) {
                    mudou = true;
                    if (shm.isEmpty()) {
                        root.remove("static_host_map");
                    }
                }

                Map<String, Object> lighthouse = (Map<String, Object>) root.get("lighthouse");
                if (lighthouse != null) {
                    @SuppressWarnings("unchecked")
                    List<String> hosts = (List<String>) lighthouse.get("hosts");
                    if (hosts != null && hosts.remove(IpNebula)) {
                        mudou = true;
                        if (hosts.isEmpty()) {
                            lighthouse.remove("hosts");
                        }
                    }
                }

                if (mudou) {
                    System.out.println("Atualizando " + yml.getFileName());

                    DumperOptions opt = new DumperOptions();
                    opt.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
                    opt.setPrettyFlow(true);
                    Yaml out = new Yaml(opt);

                    try (Writer w = Files.newBufferedWriter(
                            yml,
                            StandardOpenOption.TRUNCATE_EXISTING,
                            StandardOpenOption.CREATE)) {
                        out.dump(root, w);
                    } catch (IOException e) {
                        System.out.println("Erro ao regravar config apos apagar lighthouse: " + e);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(FuncoesMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void adicionarLighthouses(boolean op, String nome) {
        try {
            String ConfigAtual = InfoHosts.TxtAreaConifg.getText();
            Yaml yaml = new Yaml();
            Map<String, Object> config = yaml.load(ConfigAtual);
            String ip;
            String ipPublico;
            Map<String, Object> StaticHostMap = (Map<String, Object>) config.get("static_host_map");
            Map<String, Object> lighthouse = (Map<String, Object>) config.computeIfAbsent("lighthouse", k -> new LinkedHashMap<>());
            if (StaticHostMap == null) {
                StaticHostMap = new LinkedHashMap<>();
                config.put("static_host_map", StaticHostMap);
            }
            try {
                ResultSet rs;
                if (op) {
                    rs = ExecutarComandoSql("SELECT ip, ipPublico FROM Hosts WHERE isLighthouse = 1 and Apagado is not 1");
                } else {
                    if (!nome.isBlank()) {
                        if (FuncoesMain.hostJaExisteNome(nome)) {
                            rs = ExecutarComandoSql("SELECT ip, ipPublico FROM Hosts WHERE nome = '" + nome + "'");

                        } else {
                            return;
                        }
                    } else {
                        return;
                    }

                }
                while (rs.next()) {
                    ip = rs.getString("ip");
                    ipPublico = rs.getString("ipPublico");
                    if (!StaticHostMap.containsKey(ip)) {
                        StaticHostMap.put(ip.split("/")[0], List.of(ipPublico));
                    }

                    if (!Boolean.TRUE.equals(lighthouse.get("am_lighthouse"))) {
                        @SuppressWarnings("unchecked")
                        List<String> hosts = (List<String>) lighthouse.computeIfAbsent("hosts", k -> new ArrayList<>());
                        if (!hosts.contains(ip.split("/")[0])) {
                            hosts.add(ip.split("/")[0]);
                        }
                    }
                }
                DumperOptions options = new DumperOptions();
                options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
                options.setIndent(2);
                options.setPrettyFlow(true);
                Yaml yamlOut = new Yaml(new BlockRepresenter(options));

                String yamlAtualizado = yamlOut.dump(config);
                InfoHosts.TxtAreaConifg.setText("");
                InfoHosts.TxtAreaConifg.setText(yamlAtualizado);
            } catch (SQLException e) {
                System.out.println("ERRO " + e);
            }

        } catch (Exception e) {
            System.out.println("ERRO");
        }
    }

    public static void ProcurarHosts(String nome) {
        try {
            ResultSet rs = ExecutarComandoSql("SELECT * FROM Hosts WHERE nome = '" + nome + "'");
            String Teste = rs.getString("nome");
            if (Teste == null) {
                JOptionPane.showMessageDialog(null, "❌ Nenhum host encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            InfoHosts.TxtIp.setText(rs.getString("ip"));
            InfoHosts.TxtValidade.setText(rs.getString("Validade"));

            Path outputPath = ConfDir;

            EscreverConfig(outputPath.toString() + "/Config_" + TxtNome.getText() + ".yml");

        } catch (HeadlessException | IOException | SQLException e) {
            System.out.println("erro ao escrever config: " + e);
            String Erro = e.toString();
            if (e.toString().startsWith("java.nio.file.NoSuchFileException:")) {
                JOptionPane.showMessageDialog(null, "Não foi encontrada nenhuma configruação para este host", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            String url = "jdbc:sqlite:Projeto.db";
            conn = DriverManager.getConnection(url);
            conn.setAutoCommit(true);
        }
        return conn;
    }

    public static void ApagarUsuario(String nome) {
        try {
            System.out.println("Nome: " + nome);
            ResultSet rs = ExecutarComandoSql("SELECT * FROM Hosts WHERE nome = '" + nome + "' AND Apagado is not 1");
            if (rs.getInt("IsLighthouse") == 1) {
                if ((ExecutarComandoSql("SELECT count(*) from Hosts WHERE IsLighthouse = 1 AND Apagado is not 1").getInt(1)) == 1) {
                    JOptionPane.showMessageDialog(null, "Esta é sua unica lighthouse, não deve ser excluida", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                rs = ExecutarComandoSql("SELECT ip FROM Hosts WHERE nome= '" + nome + "' AND Apagado is not 1");
                String ip = rs.getString("ip");
                RemoverLighthouseHosts(ip.split("/")[0]);
            }
            rs = ExecutarComandoSql("SELECT * FROM Hosts WHERE nome = '" + nome + "' AND Apagado is not 1");
            while (rs.next()) {
                int id = rs.getInt("ID");
                System.out.println(id);
                stmt.execute("UPDATE Hosts SET Apagado = 1 WHERE ID = " + id);
                carregarTabelaHosts();
                File Conf = new File(ConfDir + "/Config_" + nome + ".yml");
                System.out.println(Conf.getAbsolutePath());
                Conf.delete();

            }
        } catch (SQLException e) {
            System.out.println("Erro ao apagar host: " + e);
        }
    }

    public static void carregarDetalhesGrupo(String nomeGrupo, JTable tblInbound, JTable tblOutbound, JTable tblHosts) {
        try {
            Connection conn = FuncoesMain.conn;

            String sqlGrupo = """
            SELECT Inbound, Outbound, ID
              FROM Groups
             WHERE LOWER(Nome) = LOWER(?)
        """;
            String inboundStr, outboundStr;
            int idGrupo;

            try (PreparedStatement ps = conn.prepareStatement(sqlGrupo)) {
                ps.setString(1, nomeGrupo.trim());
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Grupo \"" + nomeGrupo + "\" não encontrado.");
                    return;
                }
                inboundStr = rs.getString("Inbound");
                outboundStr = rs.getString("Outbound");
                idGrupo = rs.getInt("ID");
            }
            DefaultTableModel modIn;
            DefaultTableModel modOut;
            if (inboundStr.endsWith("<Host>")) {
                modIn = new DefaultTableModel(
                        new String[]{"Port", "Proto", "Host"}, 0);
            } else {
                modIn = new DefaultTableModel(
                        new String[]{"Port", "Proto", "Group"}, 0);
            }
            if (outboundStr.endsWith("<Host>")) {
                modOut = new DefaultTableModel(
                        new String[]{"Port", "Proto", "Host"}, 0);
            } else {
                modOut = new DefaultTableModel(
                        new String[]{"Port", "Proto", "Group"}, 0);
            }

            DefaultTableModel modH = new DefaultTableModel(
                    new String[]{"Hosts"}, 0);

            for (Map<String, Object> r : FirewallRuleParser.parseRules(inboundStr)) {
                modIn.addRow(new Object[]{
                    r.get("port"),
                    r.get("proto"),
                    r.getOrDefault("host", r.get("group"))
                });
            }

            for (Map<String, Object> r : FirewallRuleParser.parseRules(outboundStr)) {
                modOut.addRow(new Object[]{
                    r.get("port"),
                    r.get("proto"),
                    r.getOrDefault("host", r.get("group"))
                });
            }

            String sqlHosts = """
            SELECT h.nome
              FROM Hosts h
              JOIN Hosts_groups hg ON h.ID = hg.ID_Host
             WHERE hg.ID_Group = ? AND h.Apagado = 0
             ORDER BY h.NotBefore
        """;
            try (PreparedStatement ph = conn.prepareStatement(sqlHosts)) {
                ph.setInt(1, idGrupo);
                ResultSet rh = ph.executeQuery();
                while (rh.next()) {
                    modH.addRow(new Object[]{rh.getString("nome")});
                }
            }

            tblInbound.setModel(modIn);
            tblOutbound.setModel(modOut);
            tblHosts.setModel(modH);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao carregar dados do grupo: " + e.getMessage());
        }
    }

    public static String tabelaParaString(javax.swing.JTable tbl) {
        var m = (javax.swing.table.DefaultTableModel) tbl.getModel();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m.getRowCount(); i++) {
            String port = String.valueOf(m.getValueAt(i, 0)).trim();
            String proto = String.valueOf(m.getValueAt(i, 1)).trim();
            String dest = String.valueOf(m.getValueAt(i, 2)).trim();
            if (port.isBlank() && proto.isBlank() && dest.isBlank()) {
                continue;
            }
            String tipo = dest.contains(".") ? "Host" : "Group";
            sb.append(port).append('|').append(proto).append('|')
                    .append(dest).append('<').append(tipo).append('>');
            if (i < m.getRowCount() - 1) {
                sb.append(';');
            }
        }
        return sb.toString();
    }

    public static void RenovaData(String nome, Date DataNova) {
        System.out.println("Cehgou!");
        try {
            //Primeiro apanhar os valores dos certificados e chaves do usuario
            ResultSet rs = ExecutarComandoSql("SELECT * FROM Hosts WHERE nome = '" + nome + "' AND Apagado IS NOT 1");
            String DescobreGrupo = ("""
                                               SELECT g.nome 
                                               FROM Groups g
                                               JOIN Hosts_groups hg ON g.ID = hg.ID_Group
                                               JOIN Hosts h ON h.ID = hg.ID_Host
                                               WHERE hg.ID_Host = ?
                                               ORDER BY h.Notbefore;
                                               """);

            PreparedStatement ph = conn.prepareStatement(DescobreGrupo);
            ph.setInt(1, rs.getInt("ID"));
            ResultSet rs2 = ph.executeQuery();

            String Ca = rs.getString("CertificadoAutoridade_Value").trim();
            String pubKey = rs.getString("PublicKey_Value");

            //Verificar se o Caminho do Ca leva ao mesmo caminho do ca do hoste!
            String CaAtual = Files.readString(Paths.get(vlr.getCaminhoCa())).trim();
            System.out.println("Ca:\n " + Ca);
            System.out.println("Ca Cqaminho:\n " + CaAtual);
            if (!Ca.equals(CaAtual)) {
                JOptionPane.showMessageDialog(null, """
                        O Ca deste host n\u00e3o \u00e9 o mesmo que voce tem no seu caminho para o CA
                        Para fazer esta opra\u00e7\u00e3o voce precisa de acesso ao Ca deste host""",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String CaKey = Files.readString(Paths.get(vlr.getCaminhoCa().substring(0, vlr.getCaminhoCa().length() - 4) + ".key"));
            System.out.println("Chave Privada do Ca:\n" + CaKey + "\n-----------------------------");

            //Passar valores para ficheiros.
            Path pubFile = tempDir.resolve(nome + ".pub");
            Path caKeyFile = tempDir.resolve("ca.key");
            Path caCrtFile = tempDir.resolve("ca.crt");
            Path novoCrtFile = tempDir.resolve(nome + "-novo.crt");

            Files.writeString(pubFile, pubKey);
            Files.writeString(caCrtFile, Ca);
            Files.writeString(caKeyFile, CaKey);

            //Usar esses ca minhos para criar um novo certificado
            ProcessBuilder pb = new ProcessBuilder(
                    vlr.getCaminhoNe(), "sign",
                    "-name", nome,
                    "-ip", rs.getString("ip"),
                    "-groups", rs2.getString("Nome"),
                    "-in-pub", pubFile.toString(),
                    "-out-crt", novoCrtFile.toString(),
                    "-ca-crt", caCrtFile.toString(),
                    "-ca-key", caKeyFile.toString(),
                    "-duration", Long.toString(Duration.between(Instant.now(), DataNova.toInstant()).toHours()) + "h"
            );
            //Verificar erros sobre a criação do novo ProcessBuilder
            Process process = pb.start();
            StringBuilder erros = new StringBuilder();
            try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                String linha;
                while ((linha = errorReader.readLine()) != null) {
                    System.out.println("Erros ao fazer o processbuiter: " + erros.append(linha).append("\n"));
                }

            }

            //Na base de dados alterar o certificado atual do usuario para o certificado criado com a nova data
            //e alterar a Validade para a data nova
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            stmt.execute("UPDATE Hosts SET Certificado_Value = '" + novoCrtFile.toString() + "', Validade = '" + sdf.format(DataNova) + " (" + Duration.between(Instant.now(), DataNova.toInstant()).toHours() + ")' WHERE Nome = '" + nome + "'");
            carregarTabelaHosts();
            apagarTudoNaPasta(tempDir.toString());

        } catch (SQLException | IOException e) {
            System.out.println("Erro ao renovar a data do usuario: " + e);
        }
    }

    public static boolean AlgoAberto() {
        return !(MenuPrincipal.Criarhost.isVisible()
                || MenuPrincipal.criarConfig.isVisible()
                || MenuPrincipal.Gerirca.isVisible()
                || MenuPrincipal.grupos.isVisible()
                || MenuPrincipal.configuracoes.isVisible());
    }

}
