package br.ufc.cinetrinta.catalogo;

/**
 * Subclasse concreta que representa um Documentário ou Curta-Metragem Temático.
 * 
 * @author Davi Osugi Alencar Ferreira Gomes (RF01)
 */
public class Documentario extends Midia {

    // TODO (Davi Osugi - RF01):
    // 1. Declare os atributos específicos de Documentario:
    //    - private String temaAbordado;
    //    - private int duracaoMinutos;
    //
    // 2. Crie o construtor chamando 'super(...)' e inicializando os atributos específicos.
    //
    // 3. Crie os Getters e Setters.

    @Override
    public int getDuracaoTotalMinutos() {
        // TODO: Retornar this.duracaoMinutos
        return 0;
    }

    @Override
    public String getTipoMidia() {
        return "Documentário";
    }
}
