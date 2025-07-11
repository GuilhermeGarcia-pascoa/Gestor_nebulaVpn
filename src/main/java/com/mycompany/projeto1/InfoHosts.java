package com.mycompany.projeto1;

import java.awt.Component;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class InfoHosts extends javax.swing.JFrame {

    StaticHostMap HM = new StaticHostMap();

    public InfoHosts() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                if (!HM.isVisible()) {
                    dispose();
                }
            }
        });
        this.setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        Criar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        TxtNome = new javax.swing.JTextField();
        BtnVoltar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TxtAreaConifg = new javax.swing.JTextArea();
        BtnGravar = new javax.swing.JButton();
        BtnReset = new javax.swing.JButton();
        BtnLupa = new javax.swing.JButton();
        StcHostMap = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        TxtIp = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TxtValidade = new javax.swing.JTextField();
        BtnRenovar = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));

        Criar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Criar.setText("Informações Hosts");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Nome do host :");

        TxtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNomeActionPerformed(evt);
            }
        });

        BtnVoltar.setText("Voltar");
        BtnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVoltarActionPerformed(evt);
            }
        });

        TxtAreaConifg.setColumns(20);
        TxtAreaConifg.setRows(5);
        jScrollPane3.setViewportView(TxtAreaConifg);

        BtnGravar.setText("Gravar");
        BtnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGravarActionPerformed(evt);
            }
        });

        BtnReset.setText("Padrão");
        BtnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResetActionPerformed(evt);
            }
        });

        BtnLupa.setText("🔍");
        BtnLupa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLupaActionPerformed(evt);
            }
        });

        StcHostMap.setText("Ligthhouses"); // NOI18N
        StcHostMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StcHostMapActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Ip:");

        TxtIp.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Validade :");

        TxtValidade.setEnabled(false);

        BtnRenovar.setText("Renovar");
        BtnRenovar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRenovarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Criar)
                    .addComponent(StcHostMap)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(TxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TxtIp, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(BtnRenovar)
                                    .addComponent(TxtValidade))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnLupa)))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(BtnReset)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(BtnGravar)))
                    .addComponent(BtnVoltar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(BtnVoltar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnGravar)
                            .addComponent(BtnReset)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Criar)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(TxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnLupa))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(TxtIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(TxtValidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnRenovar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(StcHostMap, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVoltarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_BtnVoltarActionPerformed

    private void TxtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNomeActionPerformed
        TxtAreaConifg.setText("");
    }//GEN-LAST:event_TxtNomeActionPerformed

    private void BtnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResetActionPerformed
        try {
            FuncoesMain.EscreverConfig(FuncoesMain.ConfDir.toString() + "/Config_" + TxtNome.getText() + ".yml");
        } catch (IOException e) {
            System.out.println("Erro " + e);
        }
    }//GEN-LAST:event_BtnResetActionPerformed

    private void BtnLupaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLupaActionPerformed
        FuncoesMain.ProcurarHosts(TxtNome.getText());
    }//GEN-LAST:event_BtnLupaActionPerformed

    private void BtnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGravarActionPerformed

        String yamlText = TxtAreaConifg.getText();
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Guardar configuração");
        fc.setDialogType(JFileChooser.SAVE_DIALOG);
        fc.setFileFilter(new FileNameExtensionFilter("Ficheiros YAML (*.yml, *.yaml)", "yml", "yaml"));
        fc.setSelectedFile(new java.io.File(TxtNome.getText() + ".yml"));

        if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            Path destino = fc.getSelectedFile().toPath();

            String fname = destino.getFileName().toString();
            if (!fname.endsWith(".yml") && !fname.endsWith(".yaml")) {
                destino = destino.resolveSibling(fname + ".yml");
            }

            try {
                Files.writeString(destino, yamlText, StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException e) {
                System.out.println("Erro ao gravar ficheiro: " + e);
                return;
            }

            destino = Paths.get(FuncoesMain.ConfDir + "/Config_" + TxtNome.getText() + ".yml");

            try {
                Files.writeString(destino, yamlText, StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(InfoHosts.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_BtnGravarActionPerformed

    private void StcHostMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StcHostMapActionPerformed
        if (!TxtAreaConifg.getText().isBlank()) {
            if (HM.isVisible()) {
                return;
            } else {
                HM.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Primeiro selecione uma configuração para alterar.", "Aviso!", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_StcHostMapActionPerformed

    private void BtnRenovarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRenovarActionPerformed
        //Pede a data ao usuario!
        Date novaData = solicitarNovaDataValidade(null);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        
        
        
        TxtValidade.setText(sdf.format(novaData) + "(" + Duration.between(Instant.now(), novaData.toInstant()).toHours() + ")");
        FuncoesMain.RenovaData(TxtNome.getText(), novaData);
        
        
    }//GEN-LAST:event_BtnRenovarActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InfoHosts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InfoHosts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InfoHosts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InfoHosts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    public static Date solicitarNovaDataValidade(Component parent) {
        SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        JSpinner spinner = new JSpinner(model);
        spinner.setEditor(new JSpinner.DateEditor(spinner, "dd/MM/yyyy"));

        int result = JOptionPane.showOptionDialog(
                parent,
                spinner,
                "Selecione nova data de validade",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                null
        );
        if (result == JOptionPane.OK_OPTION) {
            return model.getDate();
        } else {
            return null; // Cancelado
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGravar;
    private javax.swing.JButton BtnLupa;
    private javax.swing.JButton BtnRenovar;
    private javax.swing.JButton BtnReset;
    private javax.swing.JButton BtnVoltar;
    private javax.swing.JLabel Criar;
    private javax.swing.JButton StcHostMap;
    public static javax.swing.JTextArea TxtAreaConifg;
    public static javax.swing.JTextField TxtIp;
    public static javax.swing.JTextField TxtNome;
    public static javax.swing.JTextField TxtValidade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    private DefaultTableModel modelo;
    private DefaultTableModel TbHostMap;
}
