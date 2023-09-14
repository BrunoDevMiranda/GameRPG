package br.com.gamerpg.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_historico_batalha")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoBatalha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

