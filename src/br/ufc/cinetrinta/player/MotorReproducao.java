package br.ufc.cinetrinta.player;

import br.ufc.cinetrinta.catalogo.Midia;
import br.ufc.cinetrinta.exceptions.ClassificacaoIndicativaException;
import br.ufc.cinetrinta.usuarios.Perfil;

/**
 * Motor de Reprodução Integrado (RF06).
 * Controla a execução da mídia ativa, interrompendo qualquer vídeo anterior
 * automaticamente, registrando o progresso no perfil e avançando episódios em séries.
 * 
 * @author Paulo Ícaro Matias Franco (RF06)
 */
public class MotorReproducao {

    // TODO (Paulo Ícaro - RF06):
    // 1. Atributos do Player:
    //    - private EstadoPlayer estadoAtual = EstadoPlayer.PARADO;
    //    - private Midia midiaEmReproducao;
    //    - private Perfil perfilAtivo;
    //    - private int minutoAtual;
    //    - private List<PlayerListener> listeners;
    //
    // 2. Implementar controles do ciclo de vida:
    //    - iniciarReproducao(Perfil perfil, Midia midia):
    //      * Se já houver vídeo tocando, salva o progresso anterior e para.
    //      * Valida acesso etário do perfil (RN02).
    //      * Inicia a nova mídia no minuto em que o perfil havia parado ("Continuar Assistindo").
    //    - pausar() / retomar()
    //    - avancar(int minutos) / retroceder(int minutos)
    //    - finalizar(): se for Série, avança automaticamente para o próximo episódio!

    public void iniciarReproducao(Perfil perfil, Midia midia) throws ClassificacaoIndicativaException {
        // TODO: Implementar início de reprodução com substituição automática de sessão anterior
    }

    public void pausar() {
        // TODO: Pausar reprodução e salvar progresso no perfil
    }

    public void avancar(int minutos) {
        // TODO: Avançar tempo de reprodução e atualizar progresso
    }

    public void retroceder(int minutos) {
        // TODO: Retroceder tempo de reprodução
    }

    public void parar() {
        // TODO: Encerrar reprodução atual e atualizar histórico
    }
}
