package br.ufc.cinetrinta.usuarios;

import br.ufc.cinetrinta.catalogo.Midia;

/**
 * Subclasse concreta que representa um Perfil Infantil (Kids).
 * Aplica a Regra de Negócio RN02: impede acesso a conteúdos com classificação > 12 anos.
 * 
 * @author João Cauã Barbosa Silva (RF02)
 */
public class PerfilInfantil extends Perfil {

    // TODO (João Cauã - RF02):
    // 1. Crie o construtor recebendo 'String nome' e repassando para 'super(nome)'.
    // 2. Implemente a lógica da RN02 no método podeAcessarMidia abaixo:

    @Override
    public boolean podeAcessarMidia(Midia midia) {
        // TODO: Verificar se a classificação indicativa da mídia tem idadeMinima <= 12.
        // Se for maior que 12, retornar false!
        return false;
    }

    @Override
    public boolean isInfantil() {
        return true;
    }
}
