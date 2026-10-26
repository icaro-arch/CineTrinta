package br.ufc.cinetrinta.usuarios;

import java.util.ArrayList;
import java.util.List;
import br.ufc.cinetrinta.catalogo.Midia;
import br.ufc.cinetrinta.exceptions.ClassificacaoIndicativaException;

/**
 * Controlador responsável pelo cadastro, consulta e autenticação de contas de assinantes,
 * além da verificação das permissões de acesso de perfis (RF02).
 * 
 * @author João Cauã Barbosa Silva (RF02)
 */
public class GerenciadorUsuarios {

    // TODO (João Cauã - RF02):
    // 1. Declare uma coleção privada para armazenar as contas:
    //    Sugestão: private Map<String, ContaAssinante> contasPorEmail = new LinkedHashMap<>();
    //
    // 2. Implemente os métodos de gerenciamento de contas abaixo:

    public boolean cadastrarConta(ContaAssinante novaConta) {
        // TODO: Verificar se o e-mail já não está cadastrado e salvar no Map
        return false;
    }

    public ContaAssinante autenticar(String email, String senha) {
        // TODO: Buscar a conta pelo e-mail e verificar se a senha confere (.equals)
        return null;
    }

    public ContaAssinante buscarPorEmail(String email) {
        // TODO: Retornar a conta correspondente ao e-mail
        return null;
    }

    public List<ContaAssinante> listarTodasContas() {
        // TODO: Retornar lista com todas as contas cadastradas
        return new ArrayList<>();
    }

    /**
     * Valida a Regra de Negócio RN02 antes de abrir ou reproduzir uma mídia.
     * Caso o perfil seja Infantil e a mídia tenha classificação > 12 anos,
     * lança ClassificacaoIndicativaException!
     */
    public void validarAcessoEtario(Perfil perfil, Midia midia) throws ClassificacaoIndicativaException {
        // TODO: Chamar perfil.podeAcessarMidia(midia). Se retornar false, lançar ClassificacaoIndicativaException!
    }
}
