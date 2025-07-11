package com.mycompany.projeto1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Valores {

    private String CaminhoCa;
    private String CaminhoNe;

    public Valores() {
        try {
            ResultSet rs = FuncoesMain.ExecutarComandoSql("SELECT * FROM Cache WHERE Nome = 'CaminhoNe'");
            if(rs.next()){
                CaminhoNe = rs.getString("Valor");
            }
            rs = FuncoesMain.ExecutarComandoSql("SELECT * FRom Cache WHERE Nome = 'CaminhoCa'");
            if(rs.next()){
                CaminhoCa = rs.getString("Valor");
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
