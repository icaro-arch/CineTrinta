package br.ufc.cinetrinta.catalogo;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pelo gerenciamento do acervo de mídias do CineTrinta (CRUD e buscas).
 * 
 * @author Davi Osugi Alencar Ferreira Gomes (RF01)
 */
public class RepositorioCatalogo {

    // TODO (Davi Osugi - RF01):
    // 1. Declare uma coleção privada para armazenar as mídias do catálogo:
    //    Sugestão: private List<Midia> acervo = new ArrayList<>();
    //    (ou Map<String, Midia> indexado pelo ID da mídia).
    //
    // 2. Implemente os métodos exigidos no RF01:

    public void cadastrarMidia(Midia midia) {
        // TODO: Validar se a mídia não é nula e adicionar ao acervo
    }

    public boolean atualizarMidia(String id, Midia novosDados) {
        // TODO: Localizar a mídia pelo ID e atualizar seus dados
        return false;
    }

    public boolean removerMidia(String id) {
        // TODO: Remover a mídia correspondente ao ID informado
        return false;
    }

    public Midia buscarPorId(String id) {
        // TODO: Buscar e retornar a mídia com o ID exato (ou null se não existir)
        return null;
    }

    public List<Midia> buscarPorTitulo(String termoBusca) {
        // TODO: Retornar lista de mídias cujo título contenha 'termoBusca' (ignorar maiúsculas/minúsculas)
        return new ArrayList<>();
    }

    public List<Midia> filtrarPorGenero(Genero genero) {
        // TODO: Retornar todas as mídias do gênero especificado
        return new ArrayList<>();
    }

    public List<Midia> filtrarPorAno(int ano) {
        // TODO: Retornar todas as mídias lançadas no ano informado
        return new ArrayList<>();
    }

    public List<Midia> listarTodas() {
        // TODO: Retornar a lista completa de mídias cadastradas no acervo
        return new ArrayList<>();
    }
}
