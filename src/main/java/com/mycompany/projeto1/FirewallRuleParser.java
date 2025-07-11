package com.mycompany.projeto1;

import java.util.*;

public class FirewallRuleParser {

    public static List<Map<String, Object>> parseRules(String rules) {
        List<Map<String, Object>> parsed = new ArrayList<>();

        if (rules == null || rules.isBlank()) {
            return parsed;
        }
        String[] parts = rules.split(";");
        for (String part : parts) {
            part = part.trim();
            if (part.isEmpty()) {
                continue;
            }

            int idxStart = part.lastIndexOf('<');
            int idxEnd = part.lastIndexOf('>');
            if (idxStart == -1 || idxEnd == -1 || idxEnd <= idxStart) {
                throw new IllegalArgumentException("Regra mal formatada: " + part);
            }

            String protoPortHost = part.substring(0, idxStart);
            String type = part.substring(idxStart + 1, idxEnd).toLowerCase();

            String[] values = protoPortHost.split("\\|");
            if (values.length != 3) {
                throw new IllegalArgumentException("Esperado 3 valores separados por | em: " + protoPortHost);
            }

            Map<String, Object> map = new LinkedHashMap<>();
            if (values[0].trim().matches("\\d+")) {
                map.put("port", Integer.parseInt(values[0].trim()));
            } else {
                map.put("port", values[0].trim());
            }
            map.put("proto", values[1].trim());
            if (type.equals("host")) {
                map.put("host", values[2].trim());
            } else if (type.equals("group")) {
                map.put("group", values[2].trim());
            } else {
                System.out.println("Erro ao ler: " + type);
            }

            parsed.add(map);
        }
        return parsed;
    }
}
