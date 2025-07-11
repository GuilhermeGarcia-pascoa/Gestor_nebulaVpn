package com.mycompany.projeto1;

public class Grupo {

    private final String inbound;
    private final String outbound;

    public Grupo(String inbound, String outbound) {
        this.inbound = inbound;
        this.outbound = outbound;
    }

    public String getInbound() {
        return inbound;
    }

    public String getOutbound() {
        return outbound;
    }
}
