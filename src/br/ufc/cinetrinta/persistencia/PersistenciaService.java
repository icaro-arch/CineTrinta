package br.ufc.cinetrinta.persistencia;

import java.io.IOException;

/**
 * Interface que define o contrato de persistência em arquivo do CineTrinta (RF04).
 * Permite desacoplar o restante do sistema da forma como os arquivos são gravados.
 * 
 * @author Samuel Lucas Oliveira Santos (RF04)
 */
public interface PersistenciaService {

    /**
     * Salva todo o estado da plataforma em arquivo.
     */
    void salvarDados(DadosSistemaDTO dados, String diretorioDestino) throws IOException;

    /**
     * Carrega todo o estado da plataforma a partir dos arquivos salvos.
     */
    DadosSistemaDTO carregarDados(String diretorioOrigem) throws IOException;
}
