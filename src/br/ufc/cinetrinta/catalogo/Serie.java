package br.ufc.cinetrinta.catalogo;

/**
 * Subclasse concreta que representa uma Série, estruturada em uma coleção
 * de Temporadas e Episódios sequenciais.
 * 
 * @author Davi Osugi Alencar Ferreira Gomes (RF01)
 */
public class Serie extends Midia {

    // TODO (Davi Osugi - RF01):
    // 1. Declare os atributos específicos de Serie:
    //    - private List<Temporada> temporadas; (inicialize com new ArrayList<>())
    //    - private String criador;
    //
    // 2. Crie o construtor chamando 'super(...)'.
    //
    // 3. Implemente métodos de gerenciamento das temporadas:
    //    - public void adicionarTemporada(Temporada t)
    //    - public List<Temporada> getTemporadas()
    //    - public int getQuantidadeTotalEpisodios() -> percorre as temporadas somando os episódios
    //
    // 4. No método getDuracaoTotalMinutos() abaixo, percorra todas as temporadas
    //    e some a duração de todos os episódios!

    @Override
    public int getDuracaoTotalMinutos() {
        // TODO: Somar e retornar a duração de todas as temporadas/episódios
        return 0;
    }

    @Override
    public String getTipoMidia() {
        return "Série";
    }
}
