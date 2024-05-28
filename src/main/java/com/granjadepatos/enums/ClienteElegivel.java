package com.granjadepatos.enums;

public enum ClienteElegivel {
    COM_DESCONTO("com Desconto"),
    SEM_DESCONTO("sem Desconto");

    private final String descricao;

    ClienteElegivel(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

