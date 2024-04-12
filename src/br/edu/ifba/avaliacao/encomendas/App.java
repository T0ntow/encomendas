package br.edu.ifba.avaliacao.encomendas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.time.LocalTime;
import java.util.Random;

import br.edu.ifba.avaliacao.encomendas.impl.Encomenda;
import br.edu.ifba.avaliacao.encomendas.impl.OperacoesImpl;
import br.edu.ifba.avaliacao.encomendas.impl.SensoriamentoImpl;
import br.edu.ifba.avaliacao.encomendas.impl.TempoEntrega;
import br.edu.ifba.avaliacao.encomendas.operacoes.Operacoes;
import br.edu.ifba.avaliacao.encomendas.sensoriamento.Sensoriamento;

public class App {

    private static final int TOTAL_ENCOMENDAS = 10;
    private static final int TOTAL_LEITURAS = 10;

    public static void main(String[] args) throws Exception {
        Sensoriamento<TempoEntrega> sensoriamento = new SensoriamentoImpl();

        //variavel para d4
        Map<Encomenda, List<LocalTime>> horariosEntrega = new TreeMap<>();
        Map<Encomenda, List<TempoEntrega>> leituras = new TreeMap<>();

        for (int i = 0; i < TOTAL_ENCOMENDAS; i++) {
            Encomenda encomenda = new Encomenda(String.valueOf(i), String.valueOf(i), randomizarHorarioEntrega());
            List<LocalTime> horariosEntregaEncomenda = new ArrayList<>(); // Lista para armazenar os horários de entrega da encomenda
            
            horariosEntregaEncomenda.add(encomenda.getHorarioEntrega());
            horariosEntrega.put(encomenda, horariosEntregaEncomenda);

            List<TempoEntrega> leiturasEncomenda = sensoriamento.gerar(TOTAL_LEITURAS);
            leituras.put(encomenda, leiturasEncomenda);
        }

        Operacoes<Encomenda, TempoEntrega> operacoes = new OperacoesImpl();

        // d.1 imprimindo os encomendas
        System.out.println("imprimindo as encomendas:");
        operacoes.imprimir(new ArrayList<Encomenda>(leituras.keySet()));

        // d2. imprimindo leituras das encomendas
        System.out.println("imprimindo leituras por cada encomenda:");
        operacoes.imprimirLeituras(leituras);

        // d3. ordenando os dados das leituras por encomenda
        System.out.println("ordenando as leituras de temperatura por cada encomenda:");
        Map<Encomenda, List<TempoEntrega>> leiturasOrdenadas = operacoes.ordenar(leituras);
        operacoes.imprimirLeituras(leiturasOrdenadas);

        //d4. verificando conflitos de horário nas entregas
        operacoes.verificarConflitosEntrega(horariosEntrega);
    }

    private static LocalTime randomizarHorarioEntrega() {
        Random random = new Random();
        int hora = random.nextInt(23); // Horário aleatório de 0 a 23
        return LocalTime.of(hora, 0); // Criar LocalTime com a hora aleatória e minutos = 0
    }
}
