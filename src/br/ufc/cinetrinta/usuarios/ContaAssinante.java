package br.ufc.cinetrinta.usuarios;

import br.ufc.cinetrinta.exceptions.LimitePerfisExcedidoException;

/**
 * Representa a Conta de um cliente assinante do CineTrinta.
 * Gerencia o plano contratado e a lista de perfis vinculados (aplicando a RN01).
 * 
 * @author João Cauã Barbosa Silva (RF02)
 */
public class ContaAssinante {

    // TODO (João Cauã - RF02):
    // 1. Declare os atributos privados:
    //    - private String email;
    //    - private String senha;
    //    - private String nomeTitular;
    //    - private TipoPlano plano;
    //    - private List<Perfil> perfis; (inicialize com new ArrayList<>())
    //
    // 2. Crie o construtor recebendo email, senha, nomeTitular e plano.
    //
    // 3. Implemente o método 'adicionarPerfil(Perfil novoPerfil)' validando a RN01:
    //    - Verifique se 'perfis.size() >= plano.getLimiteMaximoPerfis()'.
    //    - Se já atingiu o limite do plano, lance 'new LimitePerfisExcedidoException(...)'.
    //    - Caso contrário, adicione o perfil na lista!
    //
    // 4. Implemente métodos para remover perfil, buscar perfil pelo nome e Getters/Setters.

    public void adicionarPerfil(Perfil novoPerfil) throws LimitePerfisExcedidoException {
        // TODO: Implementar validação da RN01 e inserção na lista de perfis
    }
}
