package com.mycompany.projeto1;

import java.io.File;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CriarCa extends javax.swing.JFrame {

    public CriarCa() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(
                new ImageIcon(getClass().getResource("/nebula.png")).getImage()
        );
        DataValidade.setDate(Date.from(ZonedDateTime.now(ZoneOffset.UTC).toLocalDate().plusYears(1).atStartOfDay(ZoneOffset.UTC).toInstant()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtnCriarCa = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        TxtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        DataValidade = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BtnCriarCa.setText("Criar");
        BtnCriarCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCriarCaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Nome :");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Validade");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(TxtNome, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addComponent(BtnCriarCa)
                    .addComponent(jLabel2)
                    .addComponent(DataValidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(DataValidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(BtnCriarCa)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCriarCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCriarCaActionPerformed
        if (!TxtNome.getText().isBlank()) {
            if (DataValidade.getDate() != null) {
                try {
                    JFileChooser chooser = new JFileChooser();
                    chooser.setDialogTitle("Selecione a pasta de destino");
                    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    chooser.setCurrentDirectory(new File(FuncoesMain.vlr.getCaminhoNe()));
                    chooser.setAcceptAllFileFilterUsed(false);

                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Certificado (*.crt)", "crt");
                    chooser.addChoosableFileFilter(filter);

                    chooser.setSelectedFile(new File(TxtNome.getText() + ".crt"));

                    if (!TxtNome.getText().matches("^[A-Za-z0-9\\-_]+$")) {
                        JOptionPane.showMessageDialog(null, "O nome não pode conter caracteres especiais", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    long HoraAtual = System.currentTimeMillis();
                    if ((DataValidade.getDate().getTime() - HoraAtual) / (1000 * 60 * 60) < 72) {
                        JOptionPane.showMessageDialog(null, "O seu certificado não pode ter menos de 3 dias de validade!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if ((DataValidade.getDate().getTime() - HoraAtual) / (1000 * 60 * 60)
                            > (Date.from(ZonedDateTime.now(ZoneOffset.UTC).toLocalDate().plusYears(150).atStartOfDay(ZoneOffset.UTC).toInstant()).getTime() - HoraAtual) / (1000 * 60 * 60)) {
                        JOptionPane.showMessageDialog(null, "A data de validade do seu CA não pçode ser superior a aproximadamente 150 anos", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                        File arquivoSelecionado = chooser.getSelectedFile();

                        // Garante extensão .crt
                        if (!arquivoSelecionado.getName().toLowerCase().endsWith(".crt")) {
                            arquivoSelecionado = new File(arquivoSelecionado.getAbsolutePath() + ".crt");
                        }

                        String caminhoCrt = arquivoSelecionado.getAbsolutePath();
                        String nomeBase = arquivoSelecionado.getName().replaceFirst("[.][^.]+$", ""); // Remove extensão
                        String pasta = arquivoSelecionado.getParent(); // Diretório onde o usuário salvou

                        new ProcessBuilder(FuncoesMain.vlr.getCaminhoNe(), "ca", "-name", TxtNome.getText(),
                                "-out-crt", caminhoCrt,
                                "-out-key", pasta + File.separator + nomeBase + ".key",
                                "-duration", (DataValidade.getDate().getTime() - HoraAtual) / (1000 * 60 * 60) + "h"
                        ).inheritIO().start().waitFor();
                        GerirCa.TxtCaminhoCa.setText(caminhoCrt);
                        FuncoesMain.vlr.setCaminhoCa(caminhoCrt);
                        TxtNome.setText("");
                        this.dispose();

                    }
                } catch (IOException | InterruptedException | NullPointerException e) {
                    System.out.println("Erro " + e);
                    JOptionPane.showMessageDialog(null, "Algo de errado\n Verifique o se o seu caminho nebula esta certo", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Essa data de validade não é valida", "Cuidado", JOptionPane.ERROR_MESSAGE);
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
        java.awt.EventQueue.invokeLater(() -> {
            new CriarCa().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCriarCa;
    private com.toedter.calendar.JDateChooser DataValidade;
    private javax.swing.JTextField TxtNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
