package com.mycompany.projeto1;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.security.cert.CertificateException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MenuPrincipal extends javax.swing.JFrame {

    public static CriarHost Criarhost;
    public static GerirCa Gerirca = new GerirCa();
    public static InfoHosts criarConfig = new InfoHosts();
    public static Grupos grupos = new Grupos();

    public MenuPrincipal() {
        initComponents();
        FuncoesMain.carregarTabelaHosts();
        criarConfig.setLocationRelativeTo(null);
        TabelaHosts.setDefaultEditor(Object.class, null);
        TabelaHosts.getTableHeader().setReorderingAllowed(false);
        this.setResizable(false);
        try {
            Criarhost = new CriarHost();

        } catch (SQLException | CertificateException | IOException e) {
            System.out.println("Erro ao declarar classes no main : " + e);
        }

        LblCriadoPor.setCursor(new Cursor(Cursor.HAND_CURSOR));
        LblCriadoPor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/GuilhermeGarcia-pascoa"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        BtnCriarHost = new javax.swing.JButton();
        GerirCa = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabelaHosts = new javax.swing.JTable();
        BtnCriarConfig = new javax.swing.JButton();
        BtnGrupos = new javax.swing.JButton();
        LblCriadoPor = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Nebula");

        BtnCriarHost.setText("Criar Host");
        BtnCriarHost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCriarHostActionPerformed(evt);
            }
        });

        GerirCa.setText("Gerir caminhos");
        GerirCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GerirCaActionPerformed(evt);
            }
        });

        TabelaHosts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nome", "Ips", "Validade", "Descrição", "LightHouse", "❌"
            }
        ));
        TabelaHosts.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TabelaHosts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaHostsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TabelaHosts);

        BtnCriarConfig.setText("Criar Configs");
        BtnCriarConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCriarConfigActionPerformed(evt);
            }
        });

        BtnGrupos.setText("Grupos");
        BtnGrupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGruposActionPerformed(evt);
            }
        });

        LblCriadoPor.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        LblCriadoPor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblCriadoPor.setText("<html><p style='text-align: right;'>Criado por <a href=''>Guilherme Vommaro Garcia</a></html>");
        LblCriadoPor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(279, 279, 279))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LblCriadoPor)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnCriarHost, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(GerirCa)
                        .addGap(18, 18, 18)
                        .addComponent(BtnCriarConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnCriarHost)
                    .addComponent(BtnGrupos)
                    .addComponent(GerirCa)
                    .addComponent(BtnCriarConfig))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(LblCriadoPor, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCriarHostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCriarHostActionPerformed
        if (FuncoesMain.AlgoAberto())
            Criarhost.setVisible(true);
    }//GEN-LAST:event_BtnCriarHostActionPerformed

    private void GerirCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GerirCaActionPerformed
        if (FuncoesMain.AlgoAberto())
            Gerirca.setVisible(true);
    }//GEN-LAST:event_GerirCaActionPerformed

    private void BtnCriarConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCriarConfigActionPerformed
        if (FuncoesMain.AlgoAberto())
            criarConfig.setVisible(true);
    }//GEN-LAST:event_BtnCriarConfigActionPerformed

    private void TabelaHostsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaHostsMouseClicked
        int linha = TabelaHosts.rowAtPoint(evt.getPoint());
        int coluna = TabelaHosts.columnAtPoint(evt.getPoint());
        if (coluna == 0 && linha >= 0 && evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) {
            if (FuncoesMain.AlgoAberto()) {
                Object nomeObj = TabelaHosts.getValueAt(linha, coluna);

                if (nomeObj != null) {
                    InfoHosts.TxtNome.setText(nomeObj.toString());
                    FuncoesMain.ProcurarHosts(nomeObj.toString());
                    criarConfig.setVisible(true);
                }

            }
        }

        if (coluna == TabelaHosts.getColumnCount() - 1 && linha >= 0) {
            if (JOptionPane.showConfirmDialog(
                    null, "Tem certeza que deseja realizar esta ação?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                System.out.println("Aceite");
                FuncoesMain.ApagarUsuario((TabelaHosts.getValueAt(linha, 0)).toString());
            }
        }

    }//GEN-LAST:event_TabelaHostsMouseClicked

    private void BtnGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGruposActionPerformed
        if (FuncoesMain.AlgoAberto()) {
            grupos.setVisible(true);
            FuncoesMain.carregarTabelaGrupos();
        }
    }//GEN-LAST:event_BtnGruposActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new RunnableImpl());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCriarConfig;
    private javax.swing.JButton BtnCriarHost;
    private javax.swing.JButton BtnGrupos;
    private javax.swing.JButton GerirCa;
    private javax.swing.JLabel LblCriadoPor;
    public static javax.swing.JTable TabelaHosts;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private static class RunnableImpl implements Runnable {

        public RunnableImpl() {
        }

        @Override
        public void run() {
            new MenuPrincipal().setVisible(true);
        }
    }

}
