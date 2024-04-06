package br.edu.ifba.avaliacao.encomendas.ordenador;

import java.util.List;

public abstract class Ordenador<T> {
    protected List<T> leituras = null;

    public Ordenador(List<T> leituras) {
        this.leituras = leituras;
    }

    public List<T> getLeituras() {
        return leituras;
    }

    public abstract void ordenar();
}
