package br.ufc.cinetrinta.catalogo;

/**
 * Enumeração dos gêneros audiovisuais disponíveis no catálogo do CineTrinta.
 * 
 * @author Davi Osugi Alencar Ferreira Gomes (RF01)
 */
public enum Genero {
    ACAO,
    COMEDIA,
    DRAMA,
    FICCAO_CIENTIFICA,
    TERROR,
    DOCUMENTARIO,
    ANIMACAO;

    // TODO (Davi Osugi - RF01):
    // 1. Adicione um atributo 'private String descricao;' (ex: "Ficção Científica")
    // 2. Crie o construtor do Enum e o método 'getDescricao()' para exibição bonita na tela.
}
