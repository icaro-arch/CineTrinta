package br.ufc.cinetrinta.exceptions;

/**
 * Exceção lançada caso o usuário tente adicionar em duplicidade um mesmo
 * título na lista de favoritos ("Minha Lista") do perfil (RN03).
 * 
 * Responsável pela utilização: Gustavo Nicolas Teixeira Pinheiro (RF03)
 */
public class FavoritoDuplicadoException extends Exception {

    public FavoritoDuplicadoException(String mensagem) {
        super(mensagem);
    }
}
