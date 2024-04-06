package br.edu.ifba.avaliacao.encomendas.operacoes;

import java.util.List;
import java.util.Map;
import java.time.LocalTime;

import br.edu.ifba.avaliacao.encomendas.impl.Encomenda;
import br.edu.ifba.avaliacao.encomendas.impl.TempoEntrega;

public interface Operacoes<Monitorado, Sensor> {
     // implementando d.1
    public void imprimir(List<Monitorado> monitorados);

    // implementando d.2
    public void imprimirLeituras(Map<Monitorado, List<Sensor>> leituras);

    // implementando d.3
    public Map<Monitorado, List<Sensor>> ordenar(Map<Monitorado, List<Sensor>> leituras);

    // implementando d.4
    public void verificarConflitosEntrega(LocalTime[] horariosEntrega);

    public void verificarConflitosEntrega2(Map<Encomenda, List<LocalTime>> horariosEntrega);

}
