package br.com.gamerpg.data.service;

import br.com.gamerpg.data.model.CampoBatalha;
import br.com.gamerpg.data.model.HistoricoBatalha;
import br.com.gamerpg.data.model.Personagem;
import br.com.gamerpg.data.model.ResultadoBatalha;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.System.*;

@Service
@RequiredArgsConstructor
public class BatalhaService {
    private final PersonagemService personagemService;
    private final HistoricoBatalhaService historicoBatalhaService;
    private final ResultadoService resultadoBatalhaService;
    private final HttpSession session;
    private final List<HistoricoBatalha> historico = new ArrayList<>();

    public CampoBatalha realizarBatalha() {

        HistoricoBatalha historicoBatalha = new HistoricoBatalha();
        HistoricoBatalha historicoTurno = new HistoricoBatalha();
        ResultadoBatalha resultadoBatalha = new ResultadoBatalha();

        Personagem personagemEscolhido = (Personagem) session.getAttribute("personagemEscolhido");
        if (personagemEscolhido == null) {
            personagemEscolhido = personagemService.buscarAleatorio();
            session.setAttribute("personagemEscolhido", personagemEscolhido);
        }

        Personagem oponente = personagemService.buscarAleatorio();

        int numeroTurno = 1;
        int ataquePlayer = 0;
        int defesaPlayer = 0;
        int defesaOponente = 0;
        int danoCausadoPlayer = 0;
        int ataqueOponente = 0;
        int danoCausadoOponente = 0;
        int hpAtualPlayer = personagemEscolhido.getVida();
        int hpAtualOponente = oponente.getVida();
        boolean vitoriaPlayer = false;
        boolean vitoriaOponente = false;


        Random random = new Random();
        int resultadoDadoPlayer = random.nextInt(20) + 1;
        out.println("Dado do Player foi :" + resultadoDadoPlayer);// Lança um dado de 20 lados para o Player

        int resultadoDadoOponente = random.nextInt(20) + 1;
        out.println("Dado do Oponente foi :" + resultadoDadoOponente);// Lança um dado de 20 lados para o Oponente

        while (resultadoDadoPlayer == resultadoDadoOponente) {
            resultadoDadoPlayer = random.nextInt(20) + 1;
            out.println("Dado do Player foi :" + resultadoDadoPlayer);

            resultadoDadoOponente = random.nextInt(20) + 1;
            out.println("Dado do Oponente foi :" + resultadoDadoOponente);
        }


        boolean playerComecaProximoTurno = resultadoDadoPlayer > resultadoDadoOponente;

        boolean iniciativaDefinida = false;

        if (!iniciativaDefinida) {
            // Defina quem inicia a primeira rodada com base na iniciativa.
            iniciativaDefinida = true;
            String quemInicia = resultadoDadoPlayer >= resultadoDadoOponente ? "Player" : "Oponente";
            resultadoBatalha.setQuemIniciou(quemInicia);
        }

        while (!vitoriaPlayer && !vitoriaOponente) {


                int dicePlayer = personagemEscolhido.rolarDado12();
                int diceOponente = oponente.rolarDado12();



                if (playerComecaProximoTurno) {

                    ataquePlayer = personagemEscolhido.getForca() + personagemEscolhido.getAgilidade() + dicePlayer;
                    defesaOponente = oponente.getDefesa() + oponente.getAgilidade() + diceOponente;

                    if (ataquePlayer > defesaOponente) {
                        danoCausadoPlayer = personagemEscolhido.rolarDado(personagemEscolhido.getQuantidadeDados()
                                + personagemEscolhido.getForca());
                        int danoRecebidoOponente = danoCausadoPlayer;
                        hpAtualOponente -= danoRecebidoOponente;
                        out.println("Turno " + numeroTurno + " - Atacante: Player - Dano: " + danoCausadoPlayer +
                                " - Defensor: Oponente - Redução do HP: " + danoRecebidoOponente);


                        historicoTurno.setNumeroTurno(numeroTurno);
                        historicoTurno.setPersonagem(personagemEscolhido);
                        historicoTurno.setAtaquePlayer(ataquePlayer);
                        historicoTurno.setDefesaOponente(defesaOponente);
                        historicoTurno.setDanoCausadoPlayer(danoCausadoPlayer);
                        historicoTurno.setVencedorTurno(personagemEscolhido.getClasse());
                        historico.add(historicoTurno);

                    } else {
                        historicoTurno.setVencedorTurno("empate");
                    }


                } else {

                    ataqueOponente = oponente.getForca() + oponente.getAgilidade() + diceOponente;
                    defesaPlayer = personagemEscolhido.getDefesa() + personagemEscolhido.getAgilidade() + dicePlayer;

                    if (ataqueOponente > defesaPlayer) {
                        danoCausadoOponente = oponente.rolarDado(oponente.getQuantidadeDados())
                        +oponente.getForca();
                        int danoRecebidoPlayer = danoCausadoOponente;
                        hpAtualPlayer -= danoRecebidoPlayer;
                        out.println("Turno " + numeroTurno + " - Atacante: Oponente - Dano: " + danoCausadoOponente +
                                " - Defensor: Player - Redução do HP: " + danoRecebidoPlayer);

                        historicoTurno.setNumeroTurno(numeroTurno);
                        historicoTurno.setPersonagem(oponente);
                        historicoTurno.setAtaqueOponente(ataqueOponente);
                        historicoTurno.setDefesaPlayer(defesaPlayer);
                        historicoTurno.setDanoCausadoOponente(danoCausadoOponente);
                        historicoTurno.setVencedorTurno(oponente.getClasse());
                        historico.add(historicoTurno);

                    } else {
                        historicoTurno.setVencedorTurno("empate");
                    }
                }

                if (hpAtualOponente <= 0) {
                    vitoriaPlayer = true;
                    out.println("Vencedor Player");
                    resultadoBatalha.setVencedor("Player Venceu Batalha");
                }

                if (hpAtualPlayer <= 0) {
                    vitoriaOponente = true;
                    out.println("Vencedor Oponente");
                    resultadoBatalha.setVencedor("Oponente Venceu Batalha");
                }
                numeroTurno++;
                playerComecaProximoTurno = !playerComecaProximoTurno;

        }
        resultadoBatalha.setHistoricoBatalhaList(historico);
        resultadoBatalhaService.save(resultadoBatalha);


        historicoBatalha.setResultadoBatalha(resultadoBatalha);
        historicoBatalhaService.salvarHistoricoBatalha(historico);



        CampoBatalha campoBatalha = new CampoBatalha();
        campoBatalha.setHistoricoBatalha(historicoBatalha);

        return campoBatalha;

    }
}



