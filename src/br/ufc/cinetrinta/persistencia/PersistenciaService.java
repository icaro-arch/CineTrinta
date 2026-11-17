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
     * Salva em arquivo os dados agrupados no DTO.
     *
     * @param dados coleções do sistema que serão salvas
     * @param diretorioDestino caminho da pasta onde os arquivos serão gravados
     * @throws IOException se ocorrer um erro ao gravar os arquivos
     */
    void salvarDados(DadosSistemaDTO dados, String diretorioDestino) throws IOException;

    /**
     * Carrega os dados salvos em arquivo e os agrupa em um DTO.
     *
     * @param diretorioOrigem caminho da pasta de onde os arquivos serão lidos
     * @return DTO contendo os dados carregados
     * @throws IOException se ocorrer um erro ao ler os arquivos
     */
    DadosSistemaDTO carregarDados(String diretorioOrigem) throws IOException;
}
