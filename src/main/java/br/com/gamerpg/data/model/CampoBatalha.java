package br.com.gamerpg.data.model;

import br.com.gamerpg.data.service.HistoricoBatalhaService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampoBatalha {

    private HistoricoBatalhaService service;
    private HistoricoBatalha historicoBatalha;
    private Personagem personagem;


    private int numeroTurno;
    private int ataquePlayer;
    private int defesaPlayer;
    private int danoCausadoPlayer;
    private int ataqueOponente;
    private int defesaOponente;
    private int danoCausadoOponente;


    // Adicione o construtor que inclui os novos campos

}





