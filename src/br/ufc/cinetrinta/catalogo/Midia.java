package br.ufc.cinetrinta.catalogo;

/**
 * Classe Abstrata base que representa qualquer conteúdo audiovisual do catálogo
 * (Filmes, Séries, Documentários/Curtas).
 * 
 * Conceitos POO: Abstração, Encapsulamento, Herança e Polimorfismo.
 * 
 * @author Davi Osugi Alencar Ferreira Gomes (RF01)
 */
public abstract class Midia {

    // TODO (Davi Osugi - RF01):
    // 1. Declare os atributos privados (private) comuns a todas as mídias:
    //    - String id (código único da obra, ex: "MID-01")
    //    - String titulo
    //    - String sinopse
    //    - Genero genero
    //    - ClassificacaoIndicativa classificacao
    //    - int anoLancamento
    //    - String caminhoPoster (caminho da imagem da capa)
    //    - String caminhoMidia (caminho do vídeo principal ou trailer)
    //    - double mediaAvaliacoes (nota média de 0.0 a 5.0)
    //    - int totalVisualizacoes (contador de quantas vezes foi assistido)
    //
    // 2. Crie o construtor recebendo os dados básicos da mídia e inicializando
    //    mediaAvaliacoes = 0.0 e totalVisualizacoes = 0.
    //
    // 3. Implemente todos os Getters e Setters com encapsulamento adequado.
    //
    // 4. Mantenha os métodos abstratos abaixo para que as subclasses (Filme, Serie, Documentario)
    //    implementem polimorficamente:

    /**
     * Retorna a duração total da obra em minutos.
     * - Em Filme/Documentario: retorna a própria duração do arquivo.
     * - Em Serie: retorna a soma das durações de todos os episódios de todas as temporadas.
     */
    public abstract int getDuracaoTotalMinutos();

    /**
     * Retorna uma String identificando o tipo ("Filme", "Série", "Documentário").
     */
    public abstract String getTipoMidia();
}
