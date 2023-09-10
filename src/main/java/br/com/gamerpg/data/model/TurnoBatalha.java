package br.com.gamerpg.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurnoBatalha {
    private int numeroTurno;
    private int ataqueHeroi;
    private int defesaHeroi;
    private int danoCausadoHeroi;
    private int ataqueMonstro;
    private int defesaMonstro;
    private int danoCausadoMonstro;




}
