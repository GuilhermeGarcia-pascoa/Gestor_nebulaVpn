package com.mycompany.projeto1;

import static com.mycompany.projeto1.MenuPrincipal.Gerirca;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Configuracoes extends javax.swing.JFrame {

    public String TemaInicial;
    public boolean iniciandoComponentes = true;

    public Configuracoes() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        try {
            ResultSet rs = FuncoesMain.ExecutarComandoSql("SELECT Valor FROM Cache WHERE Nome = 'Tema'");
            if (rs.next()) {
                String tema = rs.getString("Valor");
                if (tema.equals("Light")) {
                    ComboTema.setSelectedIndex(0);
                } else if (tema.equals("Dark")) {
                    ComboTema.setSelectedIndex(1);
                }
            }

            rs = FuncoesMain.ExecutarComandoSql("SELECT Valor FROM Cache WHERE Nome = 'GerarIpAuto'");
            if (rs.next()) {
                if ("on".equals(rs.getString("Valor"))) {
                    GerarIps.setSelected(true);
                } else {
                    GerarIps.setSelected(false);
                }
            } else {
                System.out.println("Não encontrou!");
                FuncoesMain.stmt.execute("INSERT INTO Cache (Nome, Valor) VALUES ('GerarIpAuto', 'off')");
            }
            GerandoIpsAuto();

            rs = FuncoesMain.ExecutarComandoSql("SELECT Valor FROM Cache WHERE Nome = 'FaixaAutomatica'");
            if (rs.next()) {
                if ("sim".equals(rs.getString("Valor"))) {
                    CheckFaixaAuto.setSelected(true);
                } else {
                    CheckFaixaAuto.setSelected(false);
                }
            } else {
                FuncoesMain.stmt.execute("INSERT INTO Cache (Nome, Valor) VALUES ('FaixaAutomatica', 'nao')");
            }
            FaixaAuto();

            rs = FuncoesMain.ExecutarComandoSql("SELECT Valor FROM Cache WHERE Nome = 'IpFaixaAutomatica'");
            if (!rs.next()) {
                FuncoesMain.stmt.execute("INSERT INTO Cache (Nome, Valor) VALUES ('IpFaixaAutomatica', '/08')");
            }
            String ip = rs.getString("Valor");
            System.out.println(ip);
            TxtFaixaIp.setText(ip.split("/")[0]);
            switch (ip.split("/")[1]) {
                case "08" ->
                    ComboMask.setSelectedIndex(0);
                case "16" ->
                    ComboMask.setSelectedIndex(1);
                case "24" ->
                    ComboMask.setSelectedIndex(2);
                default -> {
                    ComboMask.addItem("/" + ip.split("/")[1]);
                    ComboMask.setSelectedIndex(3);
                }
            }

            rs = FuncoesMain.ExecutarComandoSql("SELECT Valor FROM Cache WHERE Nome = 'IndentificadorMin'");
            if (!rs.next()) {
                FuncoesMain.stmt.execute("INSERT INTO Cache (Nome, Valor) VALUES ('IndentificadorMin', '1')");
            }
            ValorMinIndentificador.setText(rs.getString(("Valor")));

            rs = FuncoesMain.ExecutarComandoSql("SELECT Valor FROM Cache WHERE Nome = 'IndentificadorMax'");
            if (!rs.next()) {
                FuncoesMain.stmt.execute("INSERT INTO Cache (Nome, Valor) VALUES ('IndentificadorMax', '255')");
            }
            ValorMaxIndentificador.setText(rs.getString(("Valor")));

            TemaInicial = ComboTema.getSelectedItem().toString();
            System.out.println(TemaInicial);

        } catch (SQLException e) {
            System.out.println("Erro iniciando Configurações/ComboTema");
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (!Gerirca.isVisible()) {
                    MenuPrincipal.FecharConfigs();
                }
            }
        });
        Aplicar.setEnabled(false);
        registrarAlteracoes();
        iniciandoComponentes = false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ComboTema = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        GerarIps = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        TxtFaixaIp = new javax.swing.JTextField();
        ComboMask = new javax.swing.JComboBox<>();
        CheckFaixaAuto = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ValorMinIndentificador = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        ValorMaxIndentificador = new javax.swing.JTextField();
        Aplicar = new javax.swing.JButton();
        BtnCaminhos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Configurações");

        ComboTema.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Claro", "Escuro" }));
        ComboTema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboTemaActionPerformed(evt);
            }
        });

        jLabel2.setText("Tema :");
        jLabel2.setToolTipText("");

        GerarIps.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        GerarIps.setText("Gerar ips automaticamente");
        GerarIps.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        GerarIps.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        GerarIps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GerarIpsActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Faixa de ip");
        jLabel3.setEnabled(false);

        TxtFaixaIp.setEnabled(false);

        ComboMask.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "/08", "/16", "/24" }));
        ComboMask.setSelectedIndex(2);
        ComboMask.setEnabled(false);
        ComboMask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboMaskActionPerformed(evt);
            }
        });

        CheckFaixaAuto.setText("Faixa automatica");
        CheckFaixaAuto.setEnabled(false);
        CheckFaixaAuto.setHideActionText(true);
        CheckFaixaAuto.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        CheckFaixaAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckFaixaAutoActionPerformed(evt);
            }
        });

        jLabel5.setText("Indentificadores de host entre ");
        jLabel5.setEnabled(false);

        ValorMinIndentificador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ValorMinIndentificador.setText("1");
        ValorMinIndentificador.setEnabled(false);

        jLabel6.setText("e");
        jLabel6.setEnabled(false);

        ValorMaxIndentificador.setText("255");
        ValorMaxIndentificador.setEnabled(false);

        Aplicar.setText("Aplicar");
        Aplicar.setEnabled(false);
        Aplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AplicarActionPerformed(evt);
            }
        });

        BtnCaminhos.setText("Caminhos");
        BtnCaminhos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCaminhosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(GerarIps)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ComboTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(Aplicar))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(CheckFaixaAuto)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(TxtFaixaIp, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ComboMask, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel4)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ValorMinIndentificador, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ValorMaxIndentificador, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(BtnCaminhos)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnCaminhos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ComboTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(GerarIps)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(2, 2, 2)
                .addComponent(CheckFaixaAuto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtFaixaIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboMask, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(ValorMinIndentificador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(ValorMaxIndentificador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(Aplicar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void registrarAlteracoes() {
        TxtFaixaIp.getDocument().addDocumentListener(criarListenerAlteracao());
        ValorMinIndentificador.getDocument().addDocumentListener(criarListenerAlteracao());
        ValorMaxIndentificador.getDocument().addDocumentListener(criarListenerAlteracao());

        // JCheckBox
        GerarIps.addActionListener(e -> Aplicar.setEnabled(true));
        CheckFaixaAuto.addActionListener(e -> Aplicar.setEnabled(true));

        // JComboBox
        ComboTema.addActionListener(e -> {
            if (!iniciandoComponentes) {
                Aplicar.setEnabled(true);
            }
        });
        ComboMask.addActionListener(e -> Aplicar.setEnabled(true));
    }

    private javax.swing.event.DocumentListener criarListenerAlteracao() {
        return new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                Aplicar.setEnabled(true);
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                Aplicar.setEnabled(true);
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                Aplicar.setEnabled(true);
            }
        };
    }

    private void ComboTemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboTemaActionPerformed

    }//GEN-LAST:event_ComboTemaActionPerformed

    private void ComboMaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboMaskActionPerformed

    }//GEN-LAST:event_ComboMaskActionPerformed

    private void GerandoIpsAuto() {
        if (!GerarIps.isSelected()) {
            TxtFaixaIp.setEnabled(false);
            CheckFaixaAuto.setEnabled(false);
            ComboMask.setEnabled(false);
            jLabel3.setEnabled(false);
            ValorMaxIndentificador.setEnabled(false);
            ValorMinIndentificador.setEnabled(false);
            jLabel6.setEnabled(false);
            jLabel5.setEnabled(false);
        } else {
            TxtFaixaIp.setEnabled(true);
            CheckFaixaAuto.setEnabled(true);
            ComboMask.setEnabled(true);
            jLabel3.setEnabled(true);
            ValorMaxIndentificador.setEnabled(true);
            ValorMinIndentificador.setEnabled(true);
            jLabel6.setEnabled(true);
            jLabel5.setEnabled(true);
        }
    }

    private void GerarIpsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GerarIpsActionPerformed
        GerandoIpsAuto();
    }//GEN-LAST:event_GerarIpsActionPerformed

    private void FaixaAuto() {
        if (CheckFaixaAuto.isSelected()) {
            ComboMask.setEnabled(false);
            TxtFaixaIp.setEnabled(false);
        } else {
            TxtFaixaIp.setEnabled(true);
            ComboMask.setEnabled(true);
        }
    }
    private void CheckFaixaAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckFaixaAutoActionPerformed
        FaixaAuto();
    }//GEN-LAST:event_CheckFaixaAutoActionPerformed

    private void AplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AplicarActionPerformed
        try {
            //Passar Tema
            if (TemaInicial != ComboTema.getSelectedItem()) {
                String tema;
                if (ComboTema.getSelectedIndex() == 0) {
                    tema = "Light";
                } else {
                    tema = "Dark";
                }
                FuncoesMain.stmt.execute("UPDATE Cache SET Valor = '" + tema + "' WHERE Nome = 'Tema'");

            }
        } catch (SQLException e) {
            System.out.println("Erro ao mudar tema : " + e);
        }

        try {
            FuncoesMain.stmt.execute("UPDATE Cache SET Valor = '" + (GerarIps.isSelected() ? "on" : "off") + "' WHERE Nome = 'GerarIpAuto'");

            FuncoesMain.stmt.execute("UPDATE Cache SET Valor = '" + (CheckFaixaAuto.isSelected() ? "sim" : "nao") + "' WHERE Nome = 'FaixaAutomatica'");

            FuncoesMain.stmt.execute("UPDATE Cache SET Valor = '" + TxtFaixaIp.getText() + ComboMask.getSelectedItem() + "' WHERE Nome = 'IpFaixaAutomatica'");

            FuncoesMain.stmt.execute("UPDATE Cache SET Valor = '" + ValorMinIndentificador.getText() + "' WHERE Nome = 'IndentificadorMin'");

            FuncoesMain.stmt.execute("UPDATE Cache SET Valor = '" + ValorMaxIndentificador.getText() + "' WHERE Nome = 'IndentificadorMax'");

            int escolha = JOptionPane.showOptionDialog(
                    null,
                    "Reinicie o aplicativo para aplicar as novas configurações.",
                    "Alterar",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new String[]{"Reiniciar", "Cancelar"},
                    "Não"
            );

            if (escolha == JOptionPane.YES_OPTION) {
                FuncoesMain.ReiniciarApp();
            }

            Aplicar.setEnabled(false);

        } catch (SQLException e) {
            System.out.println("Erro ao inserir GerarIps: " + e);
        }


    }//GEN-LAST:event_AplicarActionPerformed

    private void BtnCaminhosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCaminhosActionPerformed
        if (!Gerirca.isVisible()) {
            Gerirca.setVisible(true);
        }
    }//GEN-LAST:event_BtnCaminhosActionPerformed
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Configuracoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new Configuracoes().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Aplicar;
    private javax.swing.JButton BtnCaminhos;
    private javax.swing.JCheckBox CheckFaixaAuto;
    public static javax.swing.JComboBox<String> ComboMask;
    private javax.swing.JComboBox<String> ComboTema;
    private javax.swing.JCheckBox GerarIps;
    private javax.swing.JTextField TxtFaixaIp;
    private javax.swing.JTextField ValorMaxIndentificador;
    private javax.swing.JTextField ValorMinIndentificador;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

}
