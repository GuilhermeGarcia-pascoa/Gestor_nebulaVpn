package com.mycompany.projeto1;

import static com.mycompany.projeto1.GerirCa.fileChooser;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.cert.CertificateException;
import java.sql.SQLException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CriarHost extends javax.swing.JFrame {

    FuncoesMain prj = new FuncoesMain();

    public CriarHost() throws SQLException, CertificateException, IOException {
        this.setIconImage(
                new ImageIcon(getClass().getResource("/nebula.png")).getImage()
        );
        initComponents();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        FuncoesMain.AutopreencherIp();
        validarIp();

        Date validadeDate = Date.from(ZonedDateTime.now(ZoneOffset.UTC).toLocalDate().plusMonths(1).atStartOfDay(ZoneOffset.UTC).toInstant());
        CampoObrigatorioNome.setVisible(false);
        CampoObrigatorioIp.setVisible(false);
        CampoObrigatorioGrupo.setVisible(false);
        this.setResizable(false);
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

        TxtIp.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarIp();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validarIp();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarIp();
            }
        });

        DataValidade.setDate(validadeDate);
    }

    public void validarNome() {
        CheckNome.setSelected(TxtNome.getText().matches("^[A-Za-z0-9\\-_]+$"));
        if (CheckNome.isSelected()) {
            TxtNome.setForeground(new Color(0, 150, 0));
        } else {
            TxtNome.setForeground(Color.red);
        }
    }

    public void validarIp() {
        CheckIp.setSelected(TxtIp.getText().matches("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$"));
        if (CheckIp.isSelected()) {
            TxtIp.setForeground(new Color(0, 150, 0));
        } else {
            TxtIp.setForeground(Color.red);
        }
    }

    public void VerificaCa() {
        if (GerirCa.TxtCaminhoNe.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Voce ainda não tem um caminho ao nebula definido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (GerirCa.TxtCaminhoCa.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Voce ainda não tem um caminho do Ca definido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TxtNome = new javax.swing.JTextField();
        TxtIp = new javax.swing.JTextField();
        ComboMask = new javax.swing.JComboBox<>();
        BtnVoltar = new javax.swing.JButton();
        BtnCriar = new javax.swing.JButton();
        Islighthouse = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        TxtDescricao = new javax.swing.JTextField();
        DataValidade = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        TxtIpPublico = new javax.swing.JTextField();
        TxtPort = new javax.swing.JTextField();
        Opcional1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        BtnPrc = new javax.swing.JButton();
        Opcional2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TxtChavePublica = new javax.swing.JTextArea();
        CheckNome = new javax.swing.JCheckBox();
        CheckIp = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        ComboGrupos = new javax.swing.JComboBox<>();
        CampoObrigatorioNome = new javax.swing.JLabel();
        CampoObrigatorioIp = new javax.swing.JLabel();
        CampoObrigatorioGrupo = new javax.swing.JLabel();

        jLabel7.setText("jLabel7");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Criar host");

        jLabel2.setText("Nome :");

        jLabel3.setText("Ip :");

        jLabel4.setText("Validade :");
        jLabel4.setToolTipText("Não preencha para usar o padrão");

        TxtIp.setToolTipText("");

        ComboMask.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "/08", "/16", "/24" }));
        ComboMask.setSelectedIndex(2);
        ComboMask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboMaskActionPerformed(evt);
            }
        });

        BtnVoltar.setText("Voltar");
        BtnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVoltarActionPerformed(evt);
            }
        });

        BtnCriar.setText("Criar");
        BtnCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCriarActionPerformed(evt);
            }
        });

        Islighthouse.setText("LightHouse");
        Islighthouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IslighthouseActionPerformed(evt);
            }
        });

        jLabel5.setText("Descrição :");

        TxtDescricao.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        DataValidade.setToolTipText("");
        DataValidade.setDateFormatString("dd/MM/yyyy");

        jLabel6.setText("Ip Publico/port :");

        TxtIpPublico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtIpPublicoActionPerformed(evt);
            }
        });

        TxtPort.setText("4242");

        Opcional1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Opcional1.setText("Opcional");

        jLabel8.setText("Chave publica :");

        BtnPrc.setText("🔍");
        BtnPrc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrcActionPerformed(evt);
            }
        });

        Opcional2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        TxtChavePublica.setColumns(20);
        TxtChavePublica.setRows(5);
        jScrollPane1.setViewportView(TxtChavePublica);

        CheckNome.setEnabled(false);
        CheckNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckNomeActionPerformed(evt);
            }
        });

        CheckIp.setEnabled(false);

        jLabel9.setText("Grupos :");

        CampoObrigatorioNome.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        CampoObrigatorioNome.setForeground(new java.awt.Color(255, 0, 0));
        CampoObrigatorioNome.setText("Este campo é obrigatorio *");

        CampoObrigatorioIp.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        CampoObrigatorioIp.setForeground(new java.awt.Color(255, 0, 0));
        CampoObrigatorioIp.setText("Este campo é obrigatorio *");

        CampoObrigatorioGrupo.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        CampoObrigatorioGrupo.setForeground(new java.awt.Color(255, 0, 0));
        CampoObrigatorioGrupo.setText("Este campo é obrigatorio *");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CampoObrigatorioIp)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel1)
                                            .addComponent(TxtIp, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ComboMask, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CheckIp))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CampoObrigatorioGrupo)
                                    .addComponent(ComboGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CampoObrigatorioNome)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CheckNome)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnVoltar)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BtnPrc, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DataValidade, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Opcional1)
                            .addComponent(Islighthouse)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TxtIpPublico, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Opcional2)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(BtnCriar)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(TxtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(BtnVoltar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(TxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CampoObrigatorioNome))
                    .addComponent(CheckNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(TxtIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ComboMask, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(CheckIp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CampoObrigatorioIp)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(ComboGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CampoObrigatorioGrupo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Islighthouse)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Opcional1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(TxtIpPublico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Opcional2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(BtnPrc, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(DataValidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnCriar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVoltarActionPerformed
        this.dispose();
    }//GEN-LAST:event_BtnVoltarActionPerformed

    private void BtnCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCriarActionPerformed
        if (tratandoErros(ProcurandoErros())) {
            try {
                long horasVal = (DataValidade.getDate().getTime() - System.currentTimeMillis()) / (1000 * 60 * 60);
                FuncoesMain.CriarHost(TxtNome.getText(), TxtIp.getText() + ComboMask.getSelectedItem(), Islighthouse.isSelected(), FuncoesMain.vlr.getCaminhoNe(), prj.vlr.getCaminhoCa(), horasVal + "h", TxtDescricao.getText(), TxtIpPublico.getText() + ":" + TxtPort.getText(), DataValidade.getDate());
                TxtNome.setText("");
                FuncoesMain.AutopreencherIp();
                CampoObrigatorioNome.setVisible(false);
                CampoObrigatorioIp.setVisible(false);
                CampoObrigatorioGrupo.setVisible(false);
                TxtDescricao.setText("");
                TxtIpPublico.setText("");
                TxtChavePublica.setText("");
                Islighthouse.setSelected(false);
                Opcional1.setVisible(true);
                Opcional2.setVisible(false);
            } catch (SQLException e) {
            }
        }
    }//GEN-LAST:event_BtnCriarActionPerformed

    public int ProcurandoErros() {

        if (TxtNome.getText().isBlank()) {
            return 1;
        }
        if (!CheckNome.isSelected()) {
            return 2;
        }
        if (TxtIp.getText().isBlank()) {
            return 3;
        }
        if (!CheckIp.isSelected()) {
            return 4;
        }
        if (ComboGrupos.getSelectedIndex() == -1) {
            return 5;
        }
        if (Islighthouse.isSelected()) {
            if (TxtIpPublico.getText().isBlank()) {
                return 6;
            }
        }
        if (DataValidade.getDate() == null) {
            return 7;
        }
        System.out.println((DataValidade.getDate().getTime() - System.currentTimeMillis()) / (1000 * 60 * 60));
        if ((DataValidade.getDate().getTime() - System.currentTimeMillis()) / (1000 * 60 * 60) < 3) {
            return 8;
        }
        try {
            if (FuncoesMain.hostJaExisteNome(TxtNome.getText())) {
                return 9;
            } else if (FuncoesMain.hostJaExisteIp(TxtIp.getText() + ComboMask.getSelectedItem())) {
                return 10;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar se o host ja existe no criar host! : " + e);
        }
        return 0;
    }

    public boolean tratandoErros(int erro) {
        switch (erro) {
            case 0 -> {
                return true;
            }
            case 1 -> {
                CampoObrigatorioNome.setVisible(true);
                return false;
            }
            case 2 -> {
                JOptionPane.showMessageDialog(null, "O nome não pode conter caracteres especiais", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            case 3 -> {
                CampoObrigatorioIp.setVisible(true);
                return false;
            }
            case 4 -> {
                JOptionPane.showMessageDialog(null, "Este ip não segue segue a estrutura certa", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            case 5 -> {
                CampoObrigatorioGrupo.setVisible(true);
                return false;
            }
            case 6 -> {
                JOptionPane.showMessageDialog(null, "Lighthouses tem de ter um ip publico obrigatoriamente", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            case 7 -> {
                JOptionPane.showMessageDialog(null, "A validade é opcional mais não pode estar vazia\nIsso pois todo host tem 1 mes de validade por padão", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            case 8 -> {
                JOptionPane.showMessageDialog(null, "A tada de validade não pode ser inferior a 3 horas", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            case 9 -> {
                JOptionPane.showMessageDialog(null, "Já existe um host com esse nome!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            case 10 -> {
                JOptionPane.showMessageDialog(null, "Ja existe um host com este mesmo IP!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            default -> {
                System.out.println("Nenhum erro econtrado para(Codigo de erro): " + erro);
                return true;
            }
        }
    }

    private void TxtIpPublicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtIpPublicoActionPerformed

    }//GEN-LAST:event_TxtIpPublicoActionPerformed

    private void BtnPrcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrcActionPerformed
        JFrame frame = new JFrame("Escolha o CA");
        fileChooser.getSelectedFile();
        fileChooser.setSelectedFile(new File(FuncoesMain.vlr.getCaminhoNe()));
        if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION && fileChooser.getSelectedFile().getAbsolutePath().endsWith(".pub")) {
            try {
                TxtChavePublica.setText(Files.readString(Path.of(fileChooser.getSelectedFile().getAbsolutePath())));
            } catch (IOException e) {
                System.out.println("Erro " + e);
            }
        }
    }//GEN-LAST:event_BtnPrcActionPerformed

    private void IslighthouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IslighthouseActionPerformed

        if (Islighthouse.isSelected()) {
            Opcional2.setVisible(true);
            Opcional1.setVisible(false);
        } else {
            Opcional1.setVisible(true);
            Opcional2.setVisible(false);
        }

    }//GEN-LAST:event_IslighthouseActionPerformed

    private void CheckNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckNomeActionPerformed

    }//GEN-LAST:event_CheckNomeActionPerformed

    private void ComboMaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboMaskActionPerformed

    }//GEN-LAST:event_ComboMaskActionPerformed
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CriarHost.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new MenuPrincipal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCriar;
    private javax.swing.JButton BtnPrc;
    private javax.swing.JButton BtnVoltar;
    private javax.swing.JLabel CampoObrigatorioGrupo;
    private javax.swing.JLabel CampoObrigatorioIp;
    private javax.swing.JLabel CampoObrigatorioNome;
    private javax.swing.JCheckBox CheckIp;
    private javax.swing.JCheckBox CheckNome;
    public static javax.swing.JComboBox<String> ComboGrupos;
    public static javax.swing.JComboBox<String> ComboMask;
    public static com.toedter.calendar.JDateChooser DataValidade;
    private javax.swing.JCheckBox Islighthouse;
    private javax.swing.JLabel Opcional1;
    private javax.swing.JLabel Opcional2;
    public static javax.swing.JTextArea TxtChavePublica;
    private javax.swing.JTextField TxtDescricao;
    public static javax.swing.JTextField TxtIp;
    public static javax.swing.JTextField TxtIpPublico;
    public static javax.swing.JTextField TxtNome;
    private javax.swing.JTextField TxtPort;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
