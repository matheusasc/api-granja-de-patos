package com.granjadepatos.model;

import com.granjadepatos.enums.PatoStatus;
import jakarta.persistence.*;

@Entity
public class PatoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "mae_id")
    private PatoModel mae;

    @Column(nullable = false)
    private double valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PatoStatus status;

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public PatoModel getMae() {
        return mae;
    }

    public void setMae(PatoModel mae) {
        this.mae = mae;
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
