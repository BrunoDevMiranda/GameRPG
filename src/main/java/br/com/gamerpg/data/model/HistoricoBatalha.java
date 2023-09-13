package br.com.gamerpg.data.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_historico_batalha")
@Data
public class HistoricoBatalha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "personagem_id")
    private Personagem personagem;
    @ManyToOne
    @JoinColumn(name = "resultado_id")
    private ResultadoBatalha resultadoBatalha;

    @Column(name = "vencedor_turno")
    private String vencedorTurno;

    @Column(name = "numero_turno")
    private int numeroTurno;

    @Column(name = "ataque_player")
    private int ataquePlayer;

    @Column(name = "defesa_player")
    private int defesaPlayer;

    @Column(name = "defesa_oponente")
    private int defesaOponente;

    @Column(name = "dano_causado_player")
    private int danoCausadoPlayer;

    @Column(name = "ataque_oponente")
    private int ataqueOponente;

    @Column(name = "dano_causado_oponente")
    private int danoCausadoOponente;

}

