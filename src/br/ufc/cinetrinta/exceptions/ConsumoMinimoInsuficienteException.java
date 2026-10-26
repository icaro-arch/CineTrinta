package br.ufc.cinetrinta.exceptions;

/**
 * Exceção lançada quando um perfil tenta avaliar um título sem ter assistido
 * a pelo menos 20% da duração total da obra (RN03).
 * 
 * Responsável pela utilização: Gustavo Nicolas Teixeira Pinheiro (RF03)
 */
public class ConsumoMinimoInsuficienteException extends Exception {

    // TODO (Gustavo Nicolas - RF03):
    // 1. Esta exceção deve ser disparada no serviço de avaliações caso o progresso
    //    do usuário no título seja menor que 20% da duração total.

    public ConsumoMinimoInsuficienteException(String mensagem) {
        super(mensagem);
    }
}
