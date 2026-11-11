package br.ufc.cinetrinta.persistencia;

import java.util.ArrayList;
import java.util.List;

import br.ufc.cinetrinta.catalogo.Midia;
import br.ufc.cinetrinta.engajamento.Avaliacao;
import br.ufc.cinetrinta.usuarios.ContaAssinante;

/**
 * Objeto de Transferência de Dados (DTO) que agrupa todas as coleções do sistema
 * (Catálogo, Contas/Perfis e Avaliações) para facilitar o salvamento e leitura em arquivo.
 * 
 * @author Samuel Lucas Oliveira Santos (RF04)
 */
public class DadosSistemaDTO {

    private List<Midia> catalogo;
    private List<ContaAssinante> contas;
    private List<Avaliacao> avaliacoes;

    public DadosSistemaDTO() {
        this.catalogo = new ArrayList<>();
        this.contas = new ArrayList<>();
        this.avaliacoes = new ArrayList<>();
    }

    public DadosSistemaDTO(List<Midia> catalogo, List<ContaAssinante> contas,
            List<Avaliacao> avaliacoes) {
        this.catalogo = catalogo;
        this.contas = contas;
        this.avaliacoes = avaliacoes;
    }

    public List<Midia> getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(List<Midia> catalogo) {
        this.catalogo = catalogo;
    }

    public List<ContaAssinante> getContas() {
        return contas;
    }

    public void setContas(List<ContaAssinante> contas) {
        this.contas = contas;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
}
