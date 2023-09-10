package br.com.gamerpg.model;

public enum TipoDados {
    D12("Dado 12 lados"),
    D8("Dado 8 lados"),
    D6("Dado 6 lados"),
    D4("Dado 4 lados");

    private final String descricao;


    TipoDados(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
