package br.ufc.cinetrinta.persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Implementação concreta do módulo de Persistência utilizando arquivos (texto/CSV/JSON)
 * via classes do pacote java.io / java.nio (BufferedReader, BufferedWriter, File).
 * 
 * @author Samuel Lucas Oliveira Santos (RF04)
 */
public class PersistenciaArquivoTexto implements PersistenciaService {

    private static final String ARQUIVO_CATALOGO = "catalogo.txt";
    private static final String ARQUIVO_CONTAS = "contas_perfis.txt";
    private static final String ARQUIVO_AVALIACOES = "avaliacoes.txt";

    @Override
    public void salvarDados(DadosSistemaDTO dados, String diretorioDestino) throws IOException {
        if (dados == null) {
            throw new IllegalArgumentException("Os dados a serem salvos não podem ser nulos.");
        }

        if (dados.getCatalogo() == null || dados.getContas() == null
                || dados.getAvaliacoes() == null) {
            throw new IllegalArgumentException("As listas do DTO não podem ser nulas.");
        }

        if (!dados.getCatalogo().isEmpty() || !dados.getContas().isEmpty()
                || !dados.getAvaliacoes().isEmpty()) {
            throw new IOException("Não é possível salvar registros ainda: as classes de mídia, "
                    + "conta e avaliação não possuem atributos implementados para montar as linhas.");
        }

        Path diretorio = validarDiretorio(diretorioDestino);
        Files.createDirectories(diretorio);

        criarArquivoVazio(diretorio.resolve(ARQUIVO_CATALOGO));
        criarArquivoVazio(diretorio.resolve(ARQUIVO_CONTAS));
        criarArquivoVazio(diretorio.resolve(ARQUIVO_AVALIACOES));
    }

    @Override
    public DadosSistemaDTO carregarDados(String diretorioOrigem) throws IOException {
        Path diretorio = validarDiretorio(diretorioOrigem);
        DadosSistemaDTO dados = new DadosSistemaDTO();

        verificarArquivoSemRegistros(diretorio.resolve(ARQUIVO_CATALOGO));
        verificarArquivoSemRegistros(diretorio.resolve(ARQUIVO_CONTAS));
        verificarArquivoSemRegistros(diretorio.resolve(ARQUIVO_AVALIACOES));

        return dados;
    }

    private Path validarDiretorio(String diretorio) {
        if (diretorio == null || diretorio.trim().isEmpty()) {
            throw new IllegalArgumentException("O caminho do diretório não pode ser vazio.");
        }
        return Paths.get(diretorio);
    }

    private void criarArquivoVazio(Path arquivo) throws IOException {
        try (BufferedWriter escritor = Files.newBufferedWriter(arquivo,
                StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE)) {
            // Não há linhas para escrever enquanto as listas estiverem vazias.
        }
    }

    private void verificarArquivoSemRegistros(Path arquivo) throws IOException {
        if (!Files.exists(arquivo)) {
            return;
        }

        try (BufferedReader leitor = Files.newBufferedReader(arquivo, StandardCharsets.UTF_8)) {
            String linha;
            int numeroLinha = 0;
            while ((linha = leitor.readLine()) != null) {
                numeroLinha++;
                String[] campos = linha.split(";", -1);
                throw new IOException("Não é possível reconstruir o registro na linha "
                        + numeroLinha + " de " + arquivo.getFileName() + ": os modelos ainda "
                        + "não possuem construtores e atributos implementados ("
                        + campos.length + " campo(s) encontrado(s)).");
            }
        }
    }
}
