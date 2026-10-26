package br.ufc.cinetrinta.exceptions;

/**
 * Exceção lançada quando uma Conta tenta criar mais perfis do que o limite
 * permitido pelo seu Plano de Assinatura (RN01).
 * 
 * Responsável pela utilização: João Cauã Barbosa Silva (RF02)
 */
public class LimitePerfisExcedidoException extends Exception {

    // TODO (João Cauã - RF02):
    // 1. Você pode adicionar atributos extras se desejar (ex: nomePlano, limiteMaximo).
    // 2. Mantenha o construtor abaixo passando a mensagem de erro para super(mensagem).

    public LimitePerfisExcedidoException(String mensagem) {
        super(mensagem);
    }
}
