package br.ufc.cinetrinta.catalogo;

/**
 * Enumeração das faixas de classificação etária (idade mínima recomendada).
 * Importante para a validação da Regra de Negócio RN02 (Perfil Kids <= 12 anos).
 * 
 * @author Davi Osugi Alencar Ferreira Gomes (RF01)
 */
public enum ClassificacaoIndicativa {
    LIVRE(0),
    DEZ_ANOS(10),
    DOZE_ANOS(12),
    QUATORZE_ANOS(14),
    DEZESSEIS_ANOS(16),
    DEZOITO_ANOS(18);

    private final int idadeMinima;

    ClassificacaoIndicativa(int idadeMinima) {
        this.idadeMinima = idadeMinima;
    }

    public int getIdadeMinima() {
        return idadeMinima;
    }

    // TODO (Davi Osugi - RF01):
    // 1. Se desejar, adicione um método 'getRotulo()' que retorne "L", "10+", "12+", "14+", "16+", "18+".
}
