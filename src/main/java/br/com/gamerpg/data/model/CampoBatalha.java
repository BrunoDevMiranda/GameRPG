package br.com.gamerpg.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampoBatalha {
    private Herois heroi;
    private Monstros monstro;
    private List<TurnoBatalha> historico;
    private boolean vitoriaHeroi;
    private boolean vitoriaMonstro;
}
