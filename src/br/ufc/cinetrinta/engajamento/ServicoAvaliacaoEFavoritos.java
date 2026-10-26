package br.ufc.cinetrinta.engajamento;

import java.util.ArrayList;
import java.util.List;
import br.ufc.cinetrinta.catalogo.Midia;
import br.ufc.cinetrinta.exceptions.ConsumoMinimoInsuficienteException;
import br.ufc.cinetrinta.exceptions.FavoritoDuplicadoException;
import br.ufc.cinetrinta.usuarios.Perfil;

/**
 * Serviço responsável por gerenciar as avaliações dos títulos, recalcular médias
 * e manipular a lista de favoritos ("Minha Lista") aplicando a RN03.
 * 
 * @author Gustavo Nicolas Teixeira Pinheiro (RF03)
 */
public class ServicoAvaliacaoEFavoritos {

    // TODO (Gustavo Nicolas - RF03):
    // 1. Declare uma estrutura para armazenar as avaliações de cada mídia:
    //    Sugestão: private List<Avaliacao> todasAvaliacoes = new ArrayList<>();
    //
    // 2. Implemente o método 'registrarAvaliacao' aplicando a Regra de Negócio RN03:
    //    - Verifique quantos minutos o 'perfil' já assistiu da 'midia'.
    //    - Calcule se os minutos assistidos representam pelo menos 20% (0.20) de midia.getDuracaoTotalMinutos().
    //    - Se for menor que 20%, lance 'new ConsumoMinimoInsuficienteException(...)'.
    //    - Se atingir >= 20%, crie e salve a Avaliacao e atualize a nota média da mídia!

    public void registrarAvaliacao(Perfil perfil, Midia midia, int estrelas, String comentario)
            throws ConsumoMinimoInsuficienteException {
        // TODO: Validar RN03 (>= 20% assistido), registrar avaliação e recalcular média da mídia
    }

    public double calcularMediaAvaliacoes(String idMidia) {
        // TODO: Percorrer as avaliações da mídia informada e retornar a média aritmética das estrelas
        return 0.0;
    }

    public List<Avaliacao> listarAvaliacoesDaMidia(String idMidia) {
        // TODO: Retornar todas as avaliações referentes ao idMidia
        return new ArrayList<>();
    }

    /**
     * Adiciona uma mídia à lista de favoritos do perfil garantindo unicidade (RN03).
     */
    public void adicionarAosFavoritos(Perfil perfil, Midia midia) throws FavoritoDuplicadoException {
        // TODO: Verificar se a mídia já está em perfil.getMinhaListaFavoritos().
        // Se já estiver, lançar FavoritoDuplicadoException. Caso contrário, adicionar ao Set!
    }

    public void removerDosFavoritos(Perfil perfil, Midia midia) {
        // TODO: Remover a mídia da coleção de favoritos do perfil
    }
}
