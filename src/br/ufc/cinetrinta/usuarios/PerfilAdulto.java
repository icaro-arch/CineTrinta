package br.ufc.cinetrinta.usuarios;

import br.ufc.cinetrinta.catalogo.Midia;

/**
 * Subclasse concreta que representa um Perfil Comum/Adulto (sem restrição de faixa etária).
 * 
 * @author João Cauã Barbosa Silva (RF02)
 */
public class PerfilAdulto extends Perfil {

    // TODO (João Cauã - RF02):
    // 1. Crie o construtor recebendo 'String nome' e repassando para 'super(nome)'.

    @Override
    public boolean podeAcessarMidia(Midia midia) {
        return true; // Perfil adulto pode acessar qualquer classificação indicativa
    }

    @Override
    public boolean isInfantil() {
        return false;
    }
}
