package br.ufc.cinetrinta.player;

import br.ufc.cinetrinta.catalogo.Midia;

/**
 * Interface Observer para notificar a Interface Gráfica (RF05) sempre que o estado
 * do Player ou o tempo de reprodução mudar.
 * 
 * @author Paulo Ícaro Matias Franco (RF05/RF06)
 */
public interface PlayerListener {
    void onEstadoAlterado(EstadoPlayer novoEstado, Midia midiaAtual);
    void onProgressoAtualizado(int minutoAtual, int duracaoTotal);
    void onEpisodioAvancado(String nomeProximoEpisodio);
}
