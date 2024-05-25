package com.granjadepatos.model;

import com.granjadepatos.enums.ClienteElegivel;
import jakarta.persistence.*;

@Entity
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClienteElegivel elegivel;

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

    public ClienteElegivel getElegivel() {
        return elegivel;
    }

    public void setElegivel(ClienteElegivel elegivel) {
        this.elegivel = elegivel;
    }
}
