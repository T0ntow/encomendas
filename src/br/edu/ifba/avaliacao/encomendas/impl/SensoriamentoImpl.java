package br.edu.ifba.avaliacao.encomendas.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.edu.ifba.avaliacao.encomendas.sensoriamento.Sensoriamento;

public class SensoriamentoImpl implements Sensoriamento<TempoEntrega> {
    private static final int TEMPO_ENTREGA_NORMAL = 5;
    private static final int VARIAÇÃO_MAXIMA = 4;

    public List<TempoEntrega> gerar(int totalEncomendas) {
        List<TempoEntrega> temposEntrega = new ArrayList<>();
        Random randomizador = new Random();
        
        for (int i = 0; i < totalEncomendas; i++) {
            int variacao = randomizador.nextInt(2 * VARIAÇÃO_MAXIMA + 1) - VARIAÇÃO_MAXIMA;
            Integer tempoEstimado = TEMPO_ENTREGA_NORMAL + variacao;
            
            TempoEntrega leitura = new TempoEntrega(tempoEstimado);
            temposEntrega.add(leitura);
        }

        return temposEntrega;
    }


}
