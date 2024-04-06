package br.edu.ifba.avaliacao.encomendas.sensoriamento;

import java.util.List;

public interface Sensoriamento<TipoDado> {
    public List<TipoDado> gerar(int totalLeituras);
}
