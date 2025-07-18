package com.mycompany.projeto1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GerirCa extends javax.swing.JFrame {

    public static CriarCa criarCa = new CriarCa();
    public static JFileChooser fileChooser = new JFileChooser();
    JFrame frame = new JFrame("Escolha o CA");

    public GerirCa() {
        initComponents();
        this.setIconImage(
                new ImageIcon(getClass().getResource("/nebula.png")).getImage()
        );
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtnVoltar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        BtnProcurarCa = new javax.swing.JButton();
        TxtCaminhoCa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        BtnProcurarNe = new javax.swing.JButton();
        TxtCaminhoNe = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        CriarCa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BtnVoltar.setText("Ok");
        BtnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVoltarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Gerir Ca");

        BtnProcurarCa.setText("procurar");
        BtnProcurarCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnProcurarCaActionPerformed(evt);
            }
        });

        TxtCaminhoCa.setText(FuncoesMain.vlr.getCaminhoCa());
        TxtCaminhoCa.setToolTipText("");
        TxtCaminhoCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtCaminhoCaActionPerformed(evt);
            }
        });

        jLabel2.setText("Caminho para o Ca");

        jLabel3.setText("Caminho para o nebula");

        BtnProcurarNe.setText("procurar");
        BtnProcurarNe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnProcurarNeActionPerformed(evt);
            }
        });

        TxtCaminhoNe.setText(FuncoesMain.vlr.getCaminhoNe());
        TxtCaminhoNe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtCaminhoNeActionPerformed(evt);
            }
        });

        jLabel4.setText("Não tem um Ca?");

        CriarCa.setText("Clique aqui");
        CriarCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CriarCaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TxtCaminhoCa, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnProcurarCa))
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TxtCaminhoNe, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnProcurarNe)))
                        .addGap(0, 92, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CriarCa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnVoltar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtCaminhoNe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnProcurarNe))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtCaminhoCa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnProcurarCa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(CriarCa)
                    .addComponent(BtnVoltar))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVoltarActionPerformed
        FuncoesMain.vlr.setCaminhoNe(VerificaCaminho(TxtCaminhoNe.getText()));
        try {
            if ((FuncoesMain.ExecutarComandoSql("SELECT * FROM Cache WHERE Nome = 'CaminhoCa'")).next()) {
                FuncoesMain.stmt.execute("UPDATE Cache SET Valor = '" + TxtCaminhoCa.getText() + "' WHERE Nome = 'CaminhoCa'");
            } else {
                FuncoesMain.stmt.execute("INSERT INTO Cache (Nome, Valor) VALUES ('CaminhoCa', '" + TxtCaminhoCa.getText() + "')");
            }
            if ((FuncoesMain.ExecutarComandoSql("SELECT * FROM Cache WHERE Nome = 'CaminhoNe'")).next()) {
                FuncoesMain.stmt.execute("UPDATE Cache SET Valor = '" + VerificaCaminho(TxtCaminhoNe.getText()) + "' WHERE Nome = 'CaminhoNe'");
            } else {
                FuncoesMain.stmt.execute("INSERT INTO Cache (Nome, Valor) VALUES ('CaminhoNe', '" + VerificaCaminho(TxtCaminhoNe.getText()) + "')");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao Inserir dados no cache: " + e);
        }

        this.setVisible(false);
    }//GEN-LAST:event_BtnVoltarActionPerformed

    private void BtnProcurarCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnProcurarCaActionPerformed
        fileChooser.getSelectedFile();
        fileChooser.setSelectedFile(new File(FuncoesMain.vlr.getCaminhoNe()));
        if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            TxtCaminhoCa.setText(fileChooser.getSelectedFile().getAbsolutePath());
            FuncoesMain.vlr.setCaminhoCa(TxtCaminhoCa.getText());
        }
    }//GEN-LAST:event_BtnProcurarCaActionPerformed

    private void TxtCaminhoCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtCaminhoCaActionPerformed

    }//GEN-LAST:event_TxtCaminhoCaActionPerformed

    private void BtnProcurarNeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnProcurarNeActionPerformed
        fileChooser.getSelectedFile();
        fileChooser.showOpenDialog(frame);
        TxtCaminhoNe.setText((fileChooser.getSelectedFile().getAbsolutePath()));
        FuncoesMain.vlr.setCaminhoNe(TxtCaminhoNe.getText());

    }//GEN-LAST:event_BtnProcurarNeActionPerformed

    private void TxtCaminhoNeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtCaminhoNeActionPerformed

    }//GEN-LAST:event_TxtCaminhoNeActionPerformed
    public String VerificaCaminho(String caminho) {
        Path exe = Paths.get(caminho);
        String nomeExe = exe.getFileName().toString().toLowerCase();   // pp.exe

        /* 1. arquivo existe e é executável? */
        if (!Files.exists(exe) || !Files.isExecutable(exe)) {
            JOptionPane.showMessageDialog(null,
                    "Arquivo inexistente ou não executável.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        try {
            Process p = new ProcessBuilder(exe.toString(), "--help")
                    .redirectErrorStream(true)
                    .start();

            p.waitFor(3, java.util.concurrent.TimeUnit.SECONDS);

            String out = new String(p.getInputStream().readAllBytes()).toLowerCase();
            System.out.println(out);

            boolean usoCorreto = out.contains("usage of")
                    && out.contains(nomeExe)
                    && out.contains("keygen")
                    && out.contains("sign");

            if (usoCorreto) {
                return exe.toString();
            }

        } catch (IOException | InterruptedException e) {
            JOptionPane.showMessageDialog(null,
                    "Falha ao executar o arquivo.\n" + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        JOptionPane.showMessageDialog(null,
                "O arquivo selecionado não parece ser o nebula‑cert.",
                "Aviso!", JOptionPane.ERROR_MESSAGE);
        return null;
    }
    private void CriarCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CriarCaActionPerformed
        try {
            if ((FuncoesMain.ExecutarComandoSql("SELECT * FROM Cache WHERE Nome = 'CaminhoNe'")).next()) {
                FuncoesMain.stmt.execute("UPDATE Cache SET Valor = '" + VerificaCaminho(TxtCaminhoNe.getText()) + "' WHERE Nome = 'CaminhoNe'");
            } else {
                FuncoesMain.stmt.execute("INSERT INTO Cache (Nome, Valor) VALUES ('CaminhoNe', '" + VerificaCaminho(TxtCaminhoNe.getText()) + "')");
            }
            FuncoesMain.vlr.setCaminhoNe(VerificaCaminho(TxtCaminhoNe.getText()));
            if (!criarCa.isVisible() && FuncoesMain.vlr.getCaminhoNe() != null) {
                criarCa.setVisible(true);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao clicar no criar CA: " + e);
        }
    }//GEN-LAST:event_CriarCaActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerirCa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new MenuPrincipal().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnProcurarCa;
    private javax.swing.JButton BtnProcurarNe;
    private javax.swing.JButton BtnVoltar;
    private javax.swing.JButton CriarCa;
    public static javax.swing.JTextField TxtCaminhoCa;
    public static javax.swing.JTextField TxtCaminhoNe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
