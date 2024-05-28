package com.granjadepatos.DTO;

import com.granjadepatos.enums.PatoStatus;

public class PatoDTO {
    private String nome;
    private Long maeId; // ID da mãe, se existir
    private double valor;
    private PatoStatus status;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getMaeId() {
        return maeId;
    }

    public void setMaeId(Long maeId) {
        this.maeId = maeId;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public PatoStatus getStatus() {
        return status;
    }

    public void setStatus(PatoStatus status) {
        this.status = status;
    }
}
