package com.granjadepatos.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class VendaModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteModel cliente;

    @ManyToOne
    @JoinColumn(name = "pato_id", nullable = false)
    private PatoModel pato;

    @Column(nullable = false)
    private LocalDateTime dataCompra;

    @PrePersist
    protected void onCreate() {
        dataCompra = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public PatoModel getPato() {
        return pato;
    }

    public void setPato(PatoModel pato) {
        this.pato = pato;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }
}
