package com.mycompany.projeto1;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.cert.CertificateException;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class MenuPrincipal extends javax.swing.JFrame {

    public static boolean iniciandoComponentes = true;
    public static CriarHost Criarhost;
    public static GerirCa Gerirca = new GerirCa();
    public static InfoHosts criarConfig = new InfoHosts();
    public static Grupos grupos = new Grupos();
    public static Configuracoes configuracoes = new Configuracoes();

    public MenuPrincipal() {
        initComponents();
        this.setIconImage(
                new ImageIcon(getClass().getResource("/nebula.png")).getImage()
        );
        ImageIcon IconeConfig = new ImageIcon(new ImageIcon(getClass().getResource("/configuracao.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        BtnConfig.setContentAreaFilled(false);
        BtnConfig.setBorderPainted(false);
        BtnConfig.setFocusPainted(false);
        BtnConfig.setOpaque(false);
        BtnConfig.setIcon(IconeConfig);
        
        ImageIcon IconeAddHost = new ImageIcon(new ImageIcon(getClass().getResource("/AdicionarHost.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        BtnCriarHost.setContentAreaFilled(false);
        BtnCriarHost.setBorderPainted(false);
        BtnCriarHost.setFocusPainted(false);
        BtnCriarHost.setOpaque(false);
        BtnCriarHost.setIcon(IconeAddHost);
        
        ImageIcon IconeGrupos = new ImageIcon(new ImageIcon(getClass().getResource("/grupo.png")).getImage().getScaledInstance(27, 27, Image.SCALE_SMOOTH));
        BtnGrupos.setContentAreaFilled(false);
        BtnGrupos.setBorderPainted(false);
        BtnGrupos.setFocusPainted(false);
        BtnGrupos.setOpaque(false);
        BtnGrupos.setIcon(IconeGrupos);
        
        ImageIcon IconeGerirHost = new ImageIcon(new ImageIcon(getClass().getResource("/GerirHosts.png")).getImage().getScaledInstance(27, 27, Image.SCALE_SMOOTH));
        BtnGerirHosts.setContentAreaFilled(false);
        BtnGerirHosts.setBorderPainted(false);
        BtnGerirHosts.setFocusPainted(false);
        BtnGerirHosts.setOpaque(false);
        BtnGerirHosts.setIcon(IconeGerirHost);
        
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
                } catch (IOException | URISyntaxException ex) {
                    System.out.println("Erro ao mostrar url para github : " + e);
                }
            }
        });
        iniciandoComponentes = false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        BtnCriarHost = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabelaHosts = new javax.swing.JTable();
        BtnGerirHosts = new javax.swing.JButton();
        BtnGrupos = new javax.swing.JButton();
        LblCriadoPor = new javax.swing.JLabel();
        BtnConfig = new javax.swing.JButton();

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
        setTitle("Gestor Neubla");

        BtnCriarHost.setText("Criar Host");
        BtnCriarHost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCriarHostActionPerformed(evt);
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

        BtnGerirHosts.setText("Gerir Hosts");
        BtnGerirHosts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGerirHostsActionPerformed(evt);
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

        BtnConfig.setText("Configurações");
        BtnConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnConfigActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(BtnCriarHost, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnGerirHosts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(LblCriadoPor)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnCriarHost)
                    .addComponent(BtnGrupos)
                    .addComponent(BtnConfig)
                    .addComponent(BtnGerirHosts))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblCriadoPor, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCriarHostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCriarHostActionPerformed
        if (FuncoesMain.AlgoAberto()) {
            Criarhost.setVisible(true);
            FuncoesMain.carregarGruposCriarHost();
        }
    }//GEN-LAST:event_BtnCriarHostActionPerformed

    private void BtnGerirHostsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGerirHostsActionPerformed
        if (FuncoesMain.AlgoAberto()) {
            criarConfig.setVisible(true);
        }
    }//GEN-LAST:event_BtnGerirHostsActionPerformed

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
                    null, "Tem certeza que deseja apagar este host?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
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

    private void BtnConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnConfigActionPerformed
        if(FuncoesMain.AlgoAberto())
            configuracoes.setVisible(true);
    }//GEN-LAST:event_BtnConfigActionPerformed

    public static void FecharConfigs(){
        configuracoes.dispose();
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
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new RunnableImpl());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnConfig;
    private javax.swing.JButton BtnCriarHost;
    private javax.swing.JButton BtnGerirHosts;
    private javax.swing.JButton BtnGrupos;
    private javax.swing.JLabel LblCriadoPor;
    public static javax.swing.JTable TabelaHosts;
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
