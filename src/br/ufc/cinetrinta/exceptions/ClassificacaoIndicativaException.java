package br.ufc.cinetrinta.exceptions;

/**
 * Exceção lançada quando um Perfil Infantil (Kids) tenta acessar ou reproduzir
 * uma mídia com classificação indicativa superior a 12 anos (RN02).
 * 
 * Responsável pela utilização: João Cauã Barbosa Silva (RF02) / Paulo Ícaro (RF06)
 */
public class ClassificacaoIndicativaException extends Exception {

    // TODO (João Cauã - RF02):
    // 1. Personalize a mensagem de erro informando o título da obra e a idade mínima exigida.

    public ClassificacaoIndicativaException(String mensagem) {
        super(mensagem);
    }
}
