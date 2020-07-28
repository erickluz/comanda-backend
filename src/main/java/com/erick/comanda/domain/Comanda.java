package com.erick.comanda.domain;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"numero"})})
public class Comanda implements Serializable {
    private static final long serialVersionUID = 8346038929669217365L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer numero;
    private String nomeCliente;
    private LocalDateTime horaAbertura;
    private LocalDateTime horaFechamento;
    private BigDecimal valorTotal;

    public Comanda() {
        this.horaAbertura = LocalDateTime.now();
    }

    public Comanda(Integer numero, String nomeCliente, LocalDateTime horaFechamento, BigDecimal valorTotal){
    	this.numero = numero;
    	this.nomeCliente = nomeCliente;
    	this.horaAbertura = LocalDateTime.now();
    	this.horaFechamento = horaFechamento;
    	this.valorTotal = valorTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public LocalDateTime getHoraAbertura() {
        return horaAbertura;
    }

    public void setHoraAbertura(LocalDateTime horaAbertura) {
        this.horaAbertura = horaAbertura;
    }

    public LocalDateTime getHoraFechamento() {
        return horaFechamento;
    }

    public void setHoraFechamento(LocalDateTime horaFechamento) {
        this.horaFechamento = horaFechamento;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}