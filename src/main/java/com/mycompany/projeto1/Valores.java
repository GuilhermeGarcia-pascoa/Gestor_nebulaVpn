package com.mycompany.projeto1;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Valores {

    private String CaminhoCa;
    private String CaminhoNe;

    public Valores() {
        try {
            ResultSet rs = FuncoesMain.ExecutarComandoSql("SELECT * FROM Cache WHERE Nome = 'CaminhoNe'");
            if (rs.next()) {
                CaminhoNe = rs.getString("Valor");
                if (CaminhoNe.equals("null")) {
                    CaminhoNe = "";
                }
            }
            rs = FuncoesMain.ExecutarComandoSql("SELECT * FRom Cache WHERE Nome = 'CaminhoCa'");
            if (rs.next()) {
                CaminhoCa = rs.getString("Valor");
                if (CaminhoCa.equals("null")) {
                    CaminhoCa = "";
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao declarar os caminhos automaticamente: " + e);
        }
    }

    public String getCaminhoCa() {
        return CaminhoCa;
    }

    public void setCaminhoCa(String CaminhoCa) {
        this.CaminhoCa = CaminhoCa;
    }

    public String getCaminhoNe() {
        return CaminhoNe;
    }

    public void setCaminhoNe(String CaminhoNe) {
        this.CaminhoNe = CaminhoNe;
    }

}
