package com.mycompany.projeto1;

import static com.mycompany.projeto1.FuncoesMain.stmt;
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class Grupos extends javax.swing.JFrame {

    JTable InboundAntiga;
    JTable OutboundAntiga;
    boolean CriandoNova = false;
    public static String Clicado;

    public Grupos() {
        initComponents();
        this.setIconImage(
                new ImageIcon(getClass().getResource("/nebula.png")).getImage()
        );
        this.setLocationRelativeTo(null);
        TxtNome.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarNome();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validarNome();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarNome();
            }
        });
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        OutboundTable.setModel(GestorGrupos.novoModeloPadrao());
        InboundTable.setModel(GestorGrupos.novoModeloPadrao());
        configurarComboHeader(OutboundTable, 2);
        configurarComboHeader(InboundTable, 2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar1 = new java.awt.MenuBar();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblHosts = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        TxtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        OutboundTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        InboundTable = new javax.swing.JTable();
        BtnAddOutbound = new javax.swing.JButton();
        BtnRmvOutbound = new javax.swing.JButton();
        BtnRmvInbound = new javax.swing.JButton();
        BtnAddInbound = new javax.swing.JButton();
        BtnGravar = new javax.swing.JButton();
        CheckName = new javax.swing.JCheckBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        TblGrupos = new javax.swing.JTable();
        BtnAdd = new javax.swing.JButton();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TblHosts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hosts"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TblHosts);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Adicionar");
        jLabel1.setToolTipText("");

        TxtNome.setToolTipText("");
        TxtNome.setEnabled(false);

        jLabel2.setText("Nome");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Outbound");

        OutboundTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Port", "Proto", "Host"
            }
        ));
        jScrollPane2.setViewportView(OutboundTable);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Inbound");

        InboundTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Port", "Proto", "Host"
            }
        ));
        jScrollPane3.setViewportView(InboundTable);

        BtnAddOutbound.setText("+");
        BtnAddOutbound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAddOutboundActionPerformed(evt);
            }
        });

        BtnRmvOutbound.setText("-");
        BtnRmvOutbound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRmvOutboundActionPerformed(evt);
            }
        });

        BtnRmvInbound.setText("-");
        BtnRmvInbound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRmvInboundActionPerformed(evt);
            }
        });

        BtnAddInbound.setText("+");
        BtnAddInbound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAddInboundActionPerformed(evt);
            }
        });

        BtnGravar.setText("Gravar");
        BtnGravar.setToolTipText("");
        BtnGravar.setEnabled(false);
        BtnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGravarActionPerformed(evt);
            }
        });

        CheckName.setEnabled(false);

        TblGrupos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Grupos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblGrupos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblGruposMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TblGrupos);

        BtnAdd.setText("Adicionar");
        BtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(BtnAddOutbound)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnRmvOutbound))
                            .addComponent(BtnGravar))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnAddInbound)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnRmvInbound))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CheckName))
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnAdd)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CheckName))
                                .addGap(9, 9, 9))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(BtnAddOutbound, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(BtnRmvOutbound))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(BtnAddInbound, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(BtnRmvInbound)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnGravar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void configurarComboHeader(javax.swing.JTable table, int colIndex) {
        String[] opcoes = {"Host", "Group"};
        javax.swing.table.TableColumn coluna = table.getColumnModel().getColumn(colIndex);
        javax.swing.table.JTableHeader header = table.getTableHeader();

        coluna.setHeaderRenderer((tbl, val, sel, foc, row, col) -> {
            javax.swing.JLabel lbl = new javax.swing.JLabel(String.valueOf(val), javax.swing.SwingConstants.CENTER);
            lbl.setBorder(javax.swing.UIManager.getBorder("TableHeader.cellBorder"));
            lbl.setOpaque(true);
            lbl.setBackground(tbl.getTableHeader().getBackground());
            return lbl;
        });

        header.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int c = header.columnAtPoint(e.getPoint());
                if (c != colIndex) {
                    return;
                }

                javax.swing.JPopupMenu menu = new javax.swing.JPopupMenu();
                for (String op : opcoes) {
                    javax.swing.JMenuItem item = new javax.swing.JMenuItem(op);
                    item.addActionListener(ev -> {
                        coluna.setHeaderValue(op);
                        header.repaint();
                    });
                    menu.add(item);
                }
                menu.show(header, e.getX(), e.getY());
            }
        });
    }
    private void BtnAddOutboundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAddOutboundActionPerformed
        if (!GestorGrupos.ultimaLinhaPreenchida(OutboundTable)) {
            return;
        }

        DefaultTableModel model = (DefaultTableModel) OutboundTable.getModel();
        Object[] Linha = {"any", "any", "any"};
        model.addRow(Linha);

    }//GEN-LAST:event_BtnAddOutboundActionPerformed

    private void BtnAddInboundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAddInboundActionPerformed
        if (!GestorGrupos.ultimaLinhaPreenchida(InboundTable)) {
            return;
        }
        DefaultTableModel model = (DefaultTableModel) InboundTable.getModel();
        Object[] Linha = {"any", "any", "any"};
        model.addRow(Linha);
    }//GEN-LAST:event_BtnAddInboundActionPerformed

    private void BtnRmvOutboundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRmvOutboundActionPerformed
        int linha = OutboundTable.getSelectedRow();
        if (linha >= 0) {
            DefaultTableModel modelo = (DefaultTableModel) OutboundTable.getModel();
            modelo.removeRow(linha);
        }
    }//GEN-LAST:event_BtnRmvOutboundActionPerformed

    private void BtnRmvInboundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRmvInboundActionPerformed
        int linha = InboundTable.getSelectedRow();
        if (linha >= 0) {
            DefaultTableModel modelo = (DefaultTableModel) InboundTable.getModel();
            modelo.removeRow(linha);
        }
    }//GEN-LAST:event_BtnRmvInboundActionPerformed

    private void BtnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGravarActionPerformed

        if (GestorGrupos.saoIguais(InboundTable, InboundAntiga) && GestorGrupos.saoIguais(OutboundTable, OutboundAntiga)) {
            System.out.println("As tabelas são iguais");
            return;
        }

        if (FuncoesMain.TemCelulaVazia(OutboundTable) || FuncoesMain.TemCelulaVazia(InboundTable)) {
            JOptionPane.showMessageDialog(null, "As tabelas não podem ter celulas vazias \nO valor predefinido é \"any\"!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String OutboundText = "";
        for (int i = 0; i < OutboundTable.getRowCount(); i++) {
            OutboundText += OutboundTable.getValueAt(i, 0) + "|"
                    + OutboundTable.getValueAt(i, 1).toString() + "|"
                    + OutboundTable.getValueAt(i, 2) + "<" + OutboundTable.getColumnModel()
                    .getColumn(2).getHeaderValue().toString() + ">;";
        }
        String InboundText = "";
        for (int i = 0; i < InboundTable.getRowCount(); i++) {
            InboundText += InboundTable.getValueAt(i, 0).toString() + "|"
                    + InboundTable.getValueAt(i, 1).toString() + "|"
                    + InboundTable.getValueAt(i, 2) + "<" + InboundTable.getColumnModel()
                    .getColumn(2).getHeaderValue().toString() + ">;";
        }
        try {
            stmt.execute("UPDATE Groups SET Outbound = '" + OutboundText.substring(0, OutboundText.length() - 1) + "', Inbound = '" + InboundText.substring(0, InboundText.length() - 1) + "' WHERE Nome = '" + TxtNome.getText() + "'");
            System.out.println("O grupo " + TxtNome.getText() + " foi atualizado com sucesso!!!!");
        } catch (SQLException e) {
            System.out.println("Erro ao dar o update do grupo na tabela: " + e);
        }


    }//GEN-LAST:event_BtnGravarActionPerformed

    private void TblGruposMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblGruposMouseClicked
        BtnGravar.setEnabled(true);
        
        int linha = TblGrupos.rowAtPoint(evt.getPoint());
        int coluna = TblGrupos.columnAtPoint(evt.getPoint());
        
        
        
        if (linha >= 0) {
            String nomeGp = TblGrupos.getValueAt(linha, coluna).toString();
            FuncoesMain.carregarDetalhesGrupo(nomeGp, InboundTable, OutboundTable, TblHosts);
            configurarComboHeader(InboundTable, 2);
            configurarComboHeader(OutboundTable, 2);
            TxtNome.setText(nomeGp);
            InboundAntiga = clonarTabela(InboundTable);
            OutboundAntiga = clonarTabela(OutboundTable);
            CriandoNova = false;
            TxtNome.setEnabled(false);
        }

    }//GEN-LAST:event_TblGruposMouseClicked
    private JTable clonarTabela(JTable origem) {
        DefaultTableModel src = (DefaultTableModel) origem.getModel();
        DefaultTableModel dst = new DefaultTableModel();

        for (int c = 0; c < src.getColumnCount(); c++) {
            dst.addColumn(origem.getColumnModel().getColumn(c).getHeaderValue());
        }
        for (int r = 0; r < src.getRowCount(); r++) {
            Object[] linha = new Object[src.getColumnCount()];
            for (int c = 0; c < src.getColumnCount(); c++) {
                linha[c] = src.getValueAt(r, c);
            }
            dst.addRow(linha);
        }
        AddCount = false;
        return new JTable(dst);
    }

    private boolean AddCount = false;

    private void BtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAddActionPerformed
        if (!AddCount) {
            AddCount = true;
            CriandoNova = true;
            BtnGravar.setEnabled(false);
            InboundTable = limparTabela(InboundTable, true);
            OutboundTable = limparTabela(OutboundTable, true);
            TblHosts = limparTabela(TblHosts, false);
            configurarComboHeader(OutboundTable, 2);
            configurarComboHeader(InboundTable, 2);
            TxtNome.setEnabled(true);
            TxtNome.setText("");
            TxtNome.requestFocus();
        } else {

            //Codigo copiado do gravar para Adicionar nova tabela kkk
            if (!CheckName.isSelected()) {
                JOptionPane.showMessageDialog(null, "O nome não pode conter caracteres especiais", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (FuncoesMain.TemCelulaVazia(OutboundTable) || FuncoesMain.TemCelulaVazia(InboundTable)) {
                JOptionPane.showMessageDialog(null, "As tabelas não podem ter celulas vazias \nO valor predefinido é \"any\"!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String OutboundText = "";
            for (int i = 0; i < OutboundTable.getRowCount(); i++) {
                OutboundText += OutboundTable.getValueAt(i, 0) + "|"
                        + OutboundTable.getValueAt(i, 1).toString() + "|"
                        + OutboundTable.getValueAt(i, 2) + "<" + OutboundTable.getColumnModel()
                        .getColumn(2).getHeaderValue().toString() + ">;";
            }
            String InboundText = "";
            for (int i = 0; i < InboundTable.getRowCount(); i++) {
                InboundText += InboundTable.getValueAt(i, 0).toString() + "|"
                        + InboundTable.getValueAt(i, 1).toString() + "|"
                        + InboundTable.getValueAt(i, 2) + "<" + InboundTable.getColumnModel()
                        .getColumn(2).getHeaderValue().toString() + ">;";
            }
            try {
                stmt.execute("INSERT INTO Groups (Nome, Outbound, Inbound) VALUES ('" + TxtNome.getText() + "', '" + OutboundText.substring(0, OutboundText.length() - 1) + "', '" + InboundText.substring(0, InboundText.length() - 1) + "')");
                FuncoesMain.carregarTabelaGrupos();
                TxtNome.setText("");
                DefaultTableModel modelo = new DefaultTableModel();
                modelo.setRowCount(0);
                InboundTable.setModel(modelo);
                OutboundTable.setModel(modelo);
                InboundTable.clearSelection();
                OutboundTable.clearSelection();
            } catch (SQLException e) {
                System.out.println("Erro ao adicionar o grupo na base de dados" + e);
            }

            AddCount = false;
        }
    }//GEN-LAST:event_BtnAddActionPerformed

    private JTable limparTabela(JTable Tabela, boolean op) {
        DefaultTableModel modelo;
        if (!op) {
            modelo = new DefaultTableModel(new String[]{"Hosts"}, 0);
        } else {
            modelo = new DefaultTableModel(new String[]{"Port", "Prot", "Group"}, 0);
        }
        modelo.setRowCount(0);
        Tabela.setModel(modelo);
        return Tabela;
    }

    public void validarNome() {
        CheckName.setSelected(TxtNome.getText().matches("^[A-Za-z0-9\\-_]+$"));
        if (CheckName.isSelected()) {
            TxtNome.setForeground(Color.GREEN);
        } else {
            TxtNome.setForeground(Color.red);
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Grupos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        }
        java.awt.EventQueue.invokeLater(() -> {
            new Grupos().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAdd;
    private javax.swing.JButton BtnAddInbound;
    private javax.swing.JButton BtnAddOutbound;
    private javax.swing.JButton BtnGravar;
    private javax.swing.JButton BtnRmvInbound;
    private javax.swing.JButton BtnRmvOutbound;
    private javax.swing.JCheckBox CheckName;
    private javax.swing.JTable InboundTable;
    private javax.swing.JTable OutboundTable;
    public static javax.swing.JTable TblGrupos;
    public static javax.swing.JTable TblHosts;
    private javax.swing.JTextField TxtNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private java.awt.MenuBar menuBar1;
    // End of variables declaration//GEN-END:variables
}
