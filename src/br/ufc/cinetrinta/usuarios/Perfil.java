package br.ufc.cinetrinta.usuarios;

import br.ufc.cinetrinta.catalogo.Midia;

/**
 * Classe Abstrata que representa um Perfil de Visualização dentro da conta de um assinante.
 * 
 * Conceitos POO: Herança, Polimorfismo (validação de acesso por idade) e Coleções.
 * 
 * @author João Cauã Barbosa Silva (RF02)
 */
public abstract class Perfil {

    // TODO (João Cauã - RF02):
    // 1. Declare os atributos privados do Perfil:
    //    - private String nome;
    //    - private Set<Midia> minhaListaFavoritos; (usar LinkedHashSet ou HashSet para garantir RN03!)
    //    - private Map<String, Integer> historicoTempoAssistido; (guarda idMidia -> minutos assistidos)
    //
    // 2. Crie o construtor recebendo o nome do perfil e inicializando as coleções.
    //
    // 3. Implemente os Getters e métodos auxiliares:
    //    - public String getNome()
    //    - public Set<Midia> getMinhaListaFavoritos()
    //    - public Map<String, Integer> getHistoricoTempoAssistido()
    //    - public void registrarProgresso(String idMidia, int minutosAssistidos)
    //    - public int getMinutosAssistidos(String idMidia)
    //
    // 4. Mantenha o método abstrato polimórfico abaixo para validar a RN02:

    /**
     * Verifica polimorficamente se este perfil tem permissão etária para acessar a mídia.
     * - Em PerfilAdulto: retorna sempre true.
     * - Em PerfilInfantil: retorna true APENAS se a classificação da mídia for <= 12 anos (RN02).
     */
    public abstract boolean podeAcessarMidia(Midia midia);

    /**
     * Retorna se o perfil é da modalidade Infantil ("Kids") ou "Adulto".
     */
    public abstract boolean isInfantil();
}
