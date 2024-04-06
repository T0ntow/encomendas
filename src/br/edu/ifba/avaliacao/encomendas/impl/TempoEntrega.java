package br.edu.ifba.avaliacao.encomendas.impl;

public class TempoEntrega implements Comparable<TempoEntrega> {
    Integer valor = 0;

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public TempoEntrega(Integer tempoEntrega) {
        this.valor = tempoEntrega;
    }
    
    @Override
    public String toString() {
        return "tempo de entrega: " + valor;
    }

    @Override
    public int compareTo(TempoEntrega o) {
        return Integer.valueOf(valor).compareTo(o.getValor());
    }
}
