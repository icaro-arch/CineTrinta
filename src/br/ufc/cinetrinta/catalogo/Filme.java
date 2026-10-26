package br.ufc.cinetrinta.catalogo;

/**
 * Subclasse concreta que representa um Filme (obra de duração única).
 * 
 * @author Davi Osugi Alencar Ferreira Gomes (RF01)
 */
public class Filme extends Midia {

    // TODO (Davi Osugi - RF01):
    // 1. Declare os atributos específicos de Filme:
    //    - private String diretor;
    //    - private int duracaoMinutos;
    //
    // 2. Crie o construtor chamando 'super(...)' com os dados da classe mãe Midia
    //    e inicializando 'diretor' e 'duracaoMinutos'.
    //
    // 3. Crie os Getters e Setters para diretor e duracaoMinutos.
    //
    // 4. Sobrescreva (@Override) os métodos abstratos de Midia abaixo:

    @Override
    public int getDuracaoTotalMinutos() {
        // TODO: Retornar this.duracaoMinutos
        return 0;
    }

    @Override
    public String getTipoMidia() {
        return "Filme";
    }
}
