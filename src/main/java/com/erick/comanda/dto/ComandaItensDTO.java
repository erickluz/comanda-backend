package com.erick.comanda.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.erick.comanda.domain.Comanda;
import com.erick.comanda.domain.ItensComanda;

public class ComandaItensDTO {
    private Integer id;
    private Integer numero;
    private String nomeCliente;
    private LocalDateTime horaAbertura;
    private LocalDateTime horaFechamento;
    private BigDecimal valorTotal;
    private List<ItensComanda> itensComanda = new ArrayList<>();

    public ComandaItensDTO(){
        
    }

    public ComandaItensDTO(Comanda comanda, List<ItensComanda> itensComanda){
        this.id = comanda.getId();
        this.numero = comanda.getNumero();
        this.nomeCliente = comanda.getNomeCliente();
        this.horaAbertura = comanda.getHoraAbertura();
        this.horaFechamento = comanda.getHoraFechamento();
        this.valorTotal = comanda.getValorTotal();
        this.itensComanda = itensComanda;
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

    public List<ItensComanda> getItensComanda() {
        return itensComanda;
    }

    public void setItensComanda(List<ItensComanda> itensComanda) {
        this.itensComanda = itensComanda;
    }
    
}