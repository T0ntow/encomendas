package br.edu.ifba.avaliacao.encomendas.impl;

import java.time.LocalTime;

public class Encomenda implements Comparable<Encomenda>{
    private String identificador;
    private String tempoEntregaEstimado;
    private LocalTime horarioEntrega;

    public Encomenda(String identificador, String tempoEntregaEstimado, LocalTime horarioEntrega) {
        this.identificador = identificador;
        this.tempoEntregaEstimado = tempoEntregaEstimado;
        this.horarioEntrega = horarioEntrega;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getTempoEntregaEstimado() {
        return tempoEntregaEstimado;
    }

    public void setTempoEntregaEstimado(String tempoEntregaEstimado) {
        this.tempoEntregaEstimado = tempoEntregaEstimado;
    }

    public LocalTime getHorarioEntrega() {
        return horarioEntrega;
    }

    public void setHorarioEntrega(LocalTime horarioEntrega) {
        this.horarioEntrega = horarioEntrega;
    }

    @Override
    public String toString() {
        return "ID: " + identificador + ", horário de entrega: " + horarioEntrega;
    }

    @Override
    public int compareTo(Encomenda o) {
        return this.identificador.compareTo(o.getIdentificador());
    }
}
