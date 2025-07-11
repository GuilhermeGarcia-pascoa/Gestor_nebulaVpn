package com.mycompany.projeto1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CriarCa extends javax.swing.JFrame {

    public CriarCa() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtnCriarCa = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        TxtNome = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BtnCriarCa.setText("Criar");
        BtnCriarCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCriarCaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Nome :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(TxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnCriarCa))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnCriarCa)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCriarCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCriarCaActionPerformed
        if (!TxtNome.getText().isBlank()) {
            try {
                //Pede o local onde deseja criar o ca para evitar erros:
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Selecione a pasta de destino");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setCurrentDirectory(new File(FuncoesMain.vlr.getCaminhoNe()));
                chooser.setAcceptAllFileFilterUsed(false);
                String pasta = "";
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    if (!TxtNome.getText().matches("^[A-Za-z0-9\\-_]+$")) {
                        JOptionPane.showMessageDialog(null, "O nome não pode conter caracteres especiais", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    pasta = chooser.getSelectedFile().getAbsolutePath();

                    new ProcessBuilder(FuncoesMain.vlr.getCaminhoNe(), "ca", "-name", TxtNome.getText(),
                            "-out-crt", pasta + "/" + TxtNome.getText() + ".crt",
                            "-out-key", pasta + "/" + TxtNome.getText() + ".key").inheritIO().start().waitFor();
                    GerirCa.TxtCaminhoCa.setText(pasta + (FuncoesMain.IsWin() ? "\\" : "/") + TxtNome.getText() + ".crt");
                    FuncoesMain.vlr.setCaminhoCa(pasta + (FuncoesMain.IsWin() ? "\\" : "/") + TxtNome.getText() + ".crt");

                    TxtNome.setText("");
                    this.dispose();
                }
            } catch (IOException | InterruptedException | NullPointerException e) {
                System.out.println("Erro " + e);
                JOptionPane.showMessageDialog(null, "Algo de errado\n Verifique o se o seu caminho nebula esta certo", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Primeiro insira um nome", "Cuidado", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BtnCriarCaActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CriarCa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new CriarCa().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCriarCa;
    private javax.swing.JTextField TxtNome;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
