package br.com.gamerpg.data.model;

public enum TipoPersonagem {
    HEROI("Cavaleiro, Barbaro,Cavaleiro"),
    MONSTRO("Orc, Lobisome,Gigante");

    private final String descricao;

    TipoPersonagem(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
