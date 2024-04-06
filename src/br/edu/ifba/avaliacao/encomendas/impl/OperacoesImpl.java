package br.edu.ifba.avaliacao.encomendas.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.time.LocalTime;

import br.edu.ifba.avaliacao.encomendas.operacoes.Operacoes;
import br.edu.ifba.avaliacao.encomendas.ordenador.Ordenador;

public class OperacoesImpl implements Operacoes<Encomenda, TempoEntrega> {

    @Override
    public void imprimir(List<Encomenda> monitorados) {
        for (Encomenda encomenda : monitorados) {
            System.out.println("encomenda sendo monitorada: " + encomenda);
        }
    }

    @Override
    public void imprimirLeituras(Map<Encomenda, List<TempoEntrega>> leituras) {
        for (Encomenda encomenda : leituras.keySet()) {
            System.out.println("leituras da encomenda " + encomenda + ":");
            for (TempoEntrega leitura : leituras.get(encomenda)) {
                System.out.println(leitura);
            }
        }
    }

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

    public void verificarConflitosEntrega(LocalTime[] horariosEntrega) {
        int totalEncomendas = horariosEntrega.length;

        for (int i = 0; i < totalEncomendas; i++) {
            for (int j = i + 1; j < totalEncomendas; j++) {
                if (horariosEntrega[i] != null && horariosEntrega[j] != null) {
                    LocalTime entrega1 = horariosEntrega[i];
                    LocalTime entrega2 = horariosEntrega[j];

                    if (entrega1.equals(entrega2)) {
                        System.out.println("Conflito entre as encomendas " + i + " e " + j);

                    }
                }
            }
        }
    }

    public  void verificarConflitosEntrega2(Map<Encomenda, List<LocalTime>> horariosEntrega) {
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
