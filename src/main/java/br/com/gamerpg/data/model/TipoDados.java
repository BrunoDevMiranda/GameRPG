package br.com.gamerpg.data.model;

public enum TipoDados {
    D12("Dado 12 lados", 12),
    D8("Dado 8 lados", 8),
    D6("Dado 6 lados", 6),
    D4("Dado 4 lados", 4);

    private final String descricao;
    private final int valorDado;


    TipoDados(String descricao, int valorDado) {
        this.descricao = descricao;
        this.valorDado = valorDado;
    }


    public int getValorDado() {
        return valorDado;
    }
}
