package br.edu.ifba.avaliacao.encomendas.impl;

import java.util.List;

import br.edu.ifba.avaliacao.encomendas.ordenador.Ordenador;

public class OrdenadorImpl extends Ordenador<TempoEntrega>  {

    public OrdenadorImpl(List<TempoEntrega> leituras) {
        super(leituras);
    }

    /**
     * complexidade quadrática, O(N^2)
     */
    @Override
    public void ordenar() {
        int n = leituras.size();
        for (int i = 1; i < n; ++i) {
            TempoEntrega chave = leituras.get(i);
            int j = i - 1;
            while (j >= 0 && leituras.get(j).getValor() > chave.getValor()) {
                leituras.set(j + 1, leituras.get(j));
                j = j - 1;
            }
            leituras.set(j + 1, chave);
        }
    }
}
