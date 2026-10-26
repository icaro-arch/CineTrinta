package br.ufc.cinetrinta.engajamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import br.ufc.cinetrinta.catalogo.Genero;
import br.ufc.cinetrinta.catalogo.Midia;
import br.ufc.cinetrinta.usuarios.ContaAssinante;
import br.ufc.cinetrinta.usuarios.TipoPlano;

/**
 * Classe responsável pela geração dos relatórios gerenciais, rankings de audiência
 * e métricas financeiras da plataforma (RF03).
 * 
 * @author Gustavo Nicolas Teixeira Pinheiro (RF03)
 */
public class GeradorRelatorios {

    // TODO (Gustavo Nicolas - RF03):
    // Implemente os 3 relatórios principais previstos na proposta:

    /**
     * 1. Ranking Top 10 Títulos Mais Assistidos / Mais Bem Avaliados.
     */
    public List<Midia> gerarTop10MaisAssistidos(List<Midia> catalogoCompleto) {
        // TODO: Ordenar a lista de mídias pelo total de visualizações (ou nota média)
        // de forma decrescente e retornar os 10 primeiros títulos.
        return new ArrayList<>();
    }

    /**
     * 2. Relatório de Consumo Agrupado por Gênero.
     */
    public Map<Genero, Integer> gerarConsumoPorGenero(List<Midia> catalogoCompleto) {
        // TODO: Somar o total de visualizações agrupado por cada Genero do catálogo.
        return new HashMap<>();
    }

    /**
     * 3. Relatório Financeiro de Assinaturas Ativas e Faturamento Mensal Estimado.
     */
    public String gerarRelatorioFinanceiro(List<ContaAssinante> contasAtivas) {
        // TODO: Contar quantas contas existem em cada TipoPlano (BASICO, PADRAO, PREMIUM),
        // somar o valor das mensalidades (plano.getValorMensalidade()) e retornar um resumo formatado.
        return "";
    }
}
