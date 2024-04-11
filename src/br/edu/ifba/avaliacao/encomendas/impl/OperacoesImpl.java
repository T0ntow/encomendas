package br.edu.ifba.avaliacao.encomendas.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.time.LocalTime;

import br.edu.ifba.avaliacao.encomendas.operacoes.Operacoes;
import br.edu.ifba.avaliacao.encomendas.ordenador.Ordenador;

public class OperacoesImpl implements Operacoes<Encomenda, TempoEntrega> {

    /**
     * Método que imprime as encomendas.
     * Complexidade: linear, O(N)
     * Justificativa: O método itera sobre a lista de encomendas e imprime cada uma delas uma vez.
     * Consequências: A complexidade linear indica que o tempo de execução aumenta proporcionalmente ao tamanho da lista de encomendas. Se a lista for muito grande, o tempo de execução pode ser significativo.
     */
    @Override
    public void imprimir(List<Encomenda> monitorados) {
        for (Encomenda encomenda : monitorados) {
            System.out.println("encomenda sendo monitorada: " + encomenda);
        }
    }

    /**
     * Método que imprime as leituras de tempo de entrega para cada encomenda.
     * Complexidade: quadrática, O(N^2)
     * Justificativa: O método itera sobre o mapa de leituras e para cada encomenda, itera sobre a lista de tempo de entrega.
     * Consequências: A complexidade quadrática indica que o tempo de execução aumenta quadráticamente com o número de encomendas e o número de leituras para cada encomenda. Se o número de encomendas ou o número de leituras para cada encomenda for grande, o tempo de execução pode se tornar significativo.
     */
    @Override
    public void imprimirLeituras(Map<Encomenda, List<TempoEntrega>> leituras) {
        for (Encomenda encomenda : leituras.keySet()) {
            System.out.println("leituras da encomenda " + encomenda + ":");
            for (TempoEntrega leitura : leituras.get(encomenda)) {
                System.out.println(leitura);
            }
        }
    }

    /**
    * Método que ordena as leituras de tempo de entrega para cada encomenda.
    * complexidade cúbica, O(N^3)
    * Justificativa:O método itera sobre as encomendas no mapa leituras. Para cada encomenda, cria um objeto OrdenadorImpl e chama o método de ordenação ordenar. Dentro * do método ordenar do OrdenadorImpl, há 2 loops. Portanto, considerando o loop externo no método ordenar e os dois loops internos no OrdenadorImpl, há um total de * 3 loops, resultando em uma complexidade cúbica, ou seja, O(N^3)
    * Consequências: A complexidade cúbica indica que o tempo de execução aumenta cúbicamente com o número de encomendas. Se o número de encomendas for grande, o tempo de execução pode se tornar muito alto.
    */
    @Override
    public Map<Encomenda, List<TempoEntrega>> ordenar(Map<Encomenda, List<TempoEntrega>> leituras) {
        Map<Encomenda, List<TempoEntrega>> leiturasOrdenadas = new TreeMap<>();

        for (Encomenda encomenda : leituras.keySet()) {
            System.out.println("ordenando leituras da encomenda: " + encomenda);

            List<TempoEntrega> temposEntregas = leituras.get(encomenda);
            Ordenador<TempoEntrega> ordenador = new OrdenadorImpl(temposEntregas);
            ordenador.ordenar();

            leiturasOrdenadas.put(encomenda, temposEntregas);
        }

        return leiturasOrdenadas;
    }

    /**
     * Método que verifica conflitos de entrega entre as encomendas.
     * Complexidade: O(N^4)
     * Justificativa: O método itera sobre todas as encomendas no mapa de horários de entrega e, para cada par de encomendas, itera sobre todas as combinações possíveis * de horários de entrega. Isso resulta em quatro loops aninhados, levando a uma complexidade quadrática.       
     * Consequências: O tempo de execução aumenta exponencialmente com o número de encomendas e o número médio de horários de entrega por encomenda, tornando-se 
     * rapidamente impraticável para conjuntos de dados grandes. Pode levar a tempos de resposta extremamente longos e uso intensivo de recursos.
     */
    public void verificarConflitosEntrega(Map<Encomenda, List<LocalTime>> horariosEntrega) {
        for (Map.Entry<Encomenda, List<LocalTime>> entry1 : horariosEntrega.entrySet()) {
            Encomenda encomenda1 = entry1.getKey();
            List<LocalTime> horariosEncomenda1 = entry1.getValue();

            for (Map.Entry<Encomenda, List<LocalTime>> entry2 : horariosEntrega.entrySet()) {
                Encomenda encomenda2 = entry2.getKey();
                List<LocalTime> horariosEncomenda2 = entry2.getValue();

                if (encomenda1.compareTo(encomenda2) < 0) {
                    for (LocalTime horario1 : horariosEncomenda1) {
                        for (LocalTime horario2 : horariosEncomenda2) {
                            if (horario1.equals(horario2)) {
                                System.out.println("Conflito entre encomendas: " + encomenda1.getIdentificador() + " e " + encomenda2.getIdentificador() + ", horário " + horario1);
                            }
                        }
                    }
                }
            }
        }
    }
}
