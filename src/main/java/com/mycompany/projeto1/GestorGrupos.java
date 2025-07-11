package com.mycompany.projeto1;

import java.util.Objects;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class GestorGrupos {

    public static class LastRowEditableModel extends DefaultTableModel {

        public LastRowEditableModel(Object[] colNames) {
            super(colNames, 0);
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return row == getRowCount() - 1;
        }
    }

    public static DefaultTableModel novoModeloPadrao() {
        Object[] cols = {"Port", "Proto", "Host"};
        return new LastRowEditableModel(cols);
    }

    public static boolean ultimaLinhaPreenchida(javax.swing.JTable tabela) {
        int ultima = tabela.getRowCount() - 1;
        if ((ultima < 0)) {
            return true;
        }

        if (tabela.isEditing()) {
            return false;
        }
        javax.swing.table.TableModel m = tabela.getModel();

        for (int col = 0; col < m.getColumnCount(); col++) {
            Object v = m.getValueAt(ultima, col);
            if (v == null) {
                return false;
            }

            String s = v.toString().trim();
            if (s.isEmpty() || "any".equalsIgnoreCase(s)) {
                return false;
            }
        }
        return true;
    }

    public static boolean saoIguais(JTable t1, JTable t2) {
        if (t1.getRowCount() != t2.getRowCount()) {
            return false;
        }
        if (t1.getColumnCount() != t2.getColumnCount()) {
            return false;
        }

        if (t1.getColumnModel().getColumn(2).getHeaderValue().toString().equals(t2.getColumnModel().getColumn(2).getHeaderValue().toString())) {
            System.out.println("São iguais");
            return saoIguais(t1.getModel(), t2.getModel());
        } else {
            System.out.println("São diferentes");
            return false;
        }
    }

    public static boolean saoIguais(TableModel m1, TableModel m2) {

        for (int r = 0; r < m1.getRowCount(); r++) {
            for (int c = 0; c < m1.getColumnCount(); c++) {
                Object v1 = m1.getValueAt(r, c);
                Object v2 = m2.getValueAt(r, c);

                System.out.println(v1);
                System.out.println(v2);
                if (!Objects.equals(v1, v2)) {
                    return false;
                }
            }

        }
        return true;
    }
}
