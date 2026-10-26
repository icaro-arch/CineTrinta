package br.ufc.cinetrinta.persistencia;

import java.io.IOException;

/**
 * Implementação concreta do módulo de Persistência utilizando arquivos (texto/CSV/JSON)
 * via classes do pacote java.io / java.nio (BufferedReader, BufferedWriter, File).
 * 
 * @author Samuel Lucas Oliveira Santos (RF04)
 */
public class PersistenciaArquivoTexto implements PersistenciaService {

    // TODO (Samuel Lucas - RF04):
    // 1. Defina os nomes dos arquivos onde cada parte será salva, por exemplo:
    //    - "catalogo.txt" (guarda Filmes, Séries, Temporadas e Episódios)
    //    - "contas_perfis.txt" (guarda Contas, Planos, Perfis, Favoritos e Histórico)
    //    - "avaliacoes.txt" (guarda as notas e comentários registrados)
    //
    // 2. No método 'salvarDados', percorra as listas do DTO e escreva os atributos
    //    separados por ';' (ponto-e-vírgula) linha a linha usando BufferedWriter.
    //
    // 3. No método 'carregarDados', verifique se os arquivos existem. Se existirem,
    //    leia linha a linha usando BufferedReader, faça 'linha.split(";")', reconstrua
    //    os objetos correspondentes e devolva tudo dentro do DadosSistemaDTO!

    @Override
    public void salvarDados(DadosSistemaDTO dados, String diretorioDestino) throws IOException {
        // TODO: Implementar gravação em arquivo
    }

    @Override
    public DadosSistemaDTO carregarDados(String diretorioOrigem) throws IOException {
        // TODO: Implementar leitura e reconstrução dos objetos a partir do arquivo
        return new DadosSistemaDTO();
    }
}
