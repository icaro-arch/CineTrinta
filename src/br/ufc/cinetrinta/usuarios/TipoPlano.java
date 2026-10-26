package br.ufc.cinetrinta.usuarios;

/**
 * Enumeração dos planos de assinatura do CineTrinta.
 * Define o limite máximo de perfis permitidos na conta (RN01) e o valor mensal.
 * 
 * @author João Cauã Barbosa Silva (RF02)
 */
public enum TipoPlano {
    BASICO("Plano Básico", 1, 19.90),
    PADRAO("Plano Padrão", 3, 39.90),
    PREMIUM("Plano Premium", 5, 59.90);

    private final String nome;
    private final int limiteMaximoPerfis;
    private final double valorMensalidade;

    TipoPlano(String nome, int limiteMaximoPerfis, double valorMensalidade) {
        this.nome = nome;
        this.limiteMaximoPerfis = limiteMaximoPerfis;
        this.valorMensalidade = valorMensalidade;
    }

    public String getNome() {
        return nome;
    }

    public int getLimiteMaximoPerfis() {
        return limiteMaximoPerfis;
    }

    public double getValorMensalidade() {
        return valorMensalidade;
    }
}
