package br.com.gamerpg.data.service;

import br.com.gamerpg.data.model.HistoricoBatalha;
import br.com.gamerpg.data.model.Personagem;
import br.com.gamerpg.data.model.ResultadoBatalha;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static br.com.gamerpg.pdf.FontePDF.CABECALHO;
import static br.com.gamerpg.pdf.FontePDF.TITULO;
import static java.lang.System.out;

@Service
@RequiredArgsConstructor
public class CampoBatalha {
    private final PersonagemService personagemService;
    private final HistoricoBatalhaService historicoBatalhaService;
    private final ResultadoService resultadoBatalhaService;
    private Document documentoPDF;
    ResultadoBatalha resultadoBatalha = new ResultadoBatalha();
    List<HistoricoBatalha> historico = new ArrayList<>();

    private Personagem personagemEscolhido;
    private Personagem oponente;



    public void realizarBatalha() throws DocumentException, IOException {
        personagemEscolhido = personagemService.personagemEscolhido();
        oponente = personagemService.buscarAleatorio();
        String quemInicia = iniciativa() ? "Player" : "Oponente";
        resultadoBatalha.setQuemIniciou(quemInicia);
        luta();
        resultadoBatalha.setHistoricoBatalhaList(historico);
        resultadoBatalhaService.save(resultadoBatalha , historico);
        geradorPDFBatalha();
        gerarCabecalho("RPG de Mesa");
        gerarResultado(resultadoBatalha.getVencedor());
        gerarHistorico();
        fecharDocumento();
            }
    private void luta(){
        int numeroTurno = 1;

        int hpAtualPlayer = personagemEscolhido.getVida();
        int hpAtualOponente = oponente.getVida();
        boolean vitoriaPlayer = false;
        boolean vitoriaOponente = false;

        boolean playerComecaProximoTurno = iniciativa();

        boolean iniciativaDefinida = false;
        while (!vitoriaPlayer && !vitoriaOponente) {
            HistoricoBatalha historicoTurno = new HistoricoBatalha();

            int ataqueOponente;
            int defesaOponente;
            int danoCausadoOponente;
            int diceOponente = oponente.rolarDado12();

            int ataquePlayer;
            int defesaPlayer;
            int danoCausadoPlayer;
            int dicePlayer = personagemEscolhido.rolarDado12();

            if (playerComecaProximoTurno) {

                ataquePlayer = personagemEscolhido.getForca() + personagemEscolhido.getAgilidade() + dicePlayer;
                defesaOponente = oponente.getDefesa() + oponente.getAgilidade() + diceOponente;
                out.println("Força" + personagemEscolhido.getForca());

                if (ataquePlayer > defesaOponente) {
                    danoCausadoPlayer = personagemEscolhido.getForca() + personagemEscolhido.rolarDado(personagemEscolhido.getQuantidadeDados());

                    int danoRecebidoOponente = danoCausadoPlayer;
                    hpAtualOponente -= danoRecebidoOponente;
                    out.println("Turno " + numeroTurno + " - Atacante: Player - Dano: " + danoCausadoPlayer +
                            " - Defensor: Oponente - Redução do HP: " + danoRecebidoOponente);

                    historicoTurno.setNumeroTurno(numeroTurno);
                    historicoTurno.setAtaquePlayer(ataquePlayer);
                    historicoTurno.setDefesaOponente(defesaOponente);
                    historicoTurno.setDanoCausadoPlayer(danoCausadoPlayer);
                    historicoTurno.setVencedorTurno(personagemEscolhido.getClasse());


                } else {
                    historicoTurno.setNumeroTurno(numeroTurno);
                    historicoTurno.setVencedorTurno("empate");
                }
                historico.add(historicoTurno);

            } else {

                ataqueOponente = oponente.getForca() + oponente.getAgilidade() + diceOponente;
                defesaPlayer = personagemEscolhido.getDefesa() + personagemEscolhido.getAgilidade() + dicePlayer;

                if (ataqueOponente > defesaPlayer) {
                    danoCausadoOponente =  oponente.getForca()+ oponente.rolarDado(oponente.getQuantidadeDados());

                    out.println("Força m" + oponente.getForca());
                    int danoRecebidoPlayer = danoCausadoOponente;
                    hpAtualPlayer -= danoRecebidoPlayer;
                    out.println("Turno " + numeroTurno + " - Atacante: Oponente - Dano: " + danoCausadoOponente +
                            " - Defensor: Player - Redução do HP: " + danoRecebidoPlayer);

                    historicoTurno.setNumeroTurno(numeroTurno);
                    historicoTurno.setAtaqueOponente(ataqueOponente);
                    historicoTurno.setDefesaPlayer(defesaPlayer);
                    historicoTurno.setDanoCausadoOponente(danoCausadoOponente);
                    historicoTurno.setVencedorTurno(oponente.getClasse());
                } else {
                    historicoTurno.setNumeroTurno(numeroTurno);
                    historicoTurno.setVencedorTurno("empate");
                }
                historico.add(historicoTurno);
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
    }
    private boolean iniciativa() {
        int resultadoDadoPlayer = personagemEscolhido.rodarDado20();
        out.println("Dado do Player foi: " + resultadoDadoPlayer);

        int resultadoDadoOponente = oponente.rodarDado20();
        out.println("Dado do Oponente foi: " + resultadoDadoOponente);

        while (resultadoDadoPlayer == resultadoDadoOponente) {
            resultadoDadoPlayer = personagemEscolhido.rodarDado20();
            out.println("Dado do Player foi: " + resultadoDadoPlayer);

            resultadoDadoOponente = oponente.rodarDado20();
            out.println("Dado do Oponente foi: " + resultadoDadoOponente);
        }
        // Compare os resultados dos dados e retorne true se o jogador deve começar ou false caso contrário.
        return resultadoDadoPlayer > resultadoDadoOponente;
    }

         public void geradorPDFBatalha() throws DocumentException, IOException {
            documentoPDF = new Document();
             String nomeAleatorio = UUID.randomUUID().toString(); // Gera um nome aleatório
             String nomeDoArquivo = "relatorios/" + nomeAleatorio + ".pdf"; // Caminho completo para a pasta "pdfs" e nome aleatório
            PdfWriter.getInstance(documentoPDF, new FileOutputStream(nomeDoArquivo));
            documentoPDF.open();
        }

        public void gerarCabecalho(String cabecalho) throws DocumentException {
            Paragraph titulo = new Paragraph(cabecalho, TITULO.getFonte());
            titulo.setAlignment(Element.ALIGN_CENTER);
            documentoPDF.add(titulo);
//            documentoPDF.add(new Paragraph("-------------------------------", CABECALHO.getFonte()));
        }

        public void gerarResultado(String resultado) throws DocumentException {
            Paragraph resultadoParagraph = new Paragraph(resultado, CABECALHO.getFonte());
            resultadoParagraph.setAlignment(Element.ALIGN_CENTER);
            documentoPDF.add(resultadoParagraph);
//            documentoPDF.add(new Paragraph("-------------------------------", CABECALHO.getFonte()));
        }

        public void gerarHistorico() throws DocumentException {
            PdfPTable tabelaHistorico = new PdfPTable(5);
            tabelaHistorico.setWidthPercentage(100);
            tabelaHistorico.setSpacingBefore(10f);
            tabelaHistorico.setSpacingAfter(10f);

            PdfPCell colunaTurno = new PdfPCell(new Phrase("Turno", CABECALHO.getFonte()));
            PdfPCell colunaAtaquePlayer = new PdfPCell(new Phrase("Ataque Player", CABECALHO.getFonte()));
            PdfPCell colunaDefesaOponente = new PdfPCell(new Phrase("Defesa Oponente", CABECALHO.getFonte()));
            PdfPCell colunaDanoCausadoPlayer = new PdfPCell(new Phrase("Dano Causado Player", CABECALHO.getFonte()));
            PdfPCell colunaVencedorTurno = new PdfPCell(new Phrase("Vencedor Turno", CABECALHO.getFonte()));

            tabelaHistorico.addCell(colunaTurno);
            tabelaHistorico.addCell(colunaAtaquePlayer);
            tabelaHistorico.addCell(colunaDefesaOponente);
            tabelaHistorico.addCell(colunaDanoCausadoPlayer);
            tabelaHistorico.addCell(colunaVencedorTurno);

            for (HistoricoBatalha turno : historico) {
                tabelaHistorico.addCell(String.valueOf(turno.getNumeroTurno()));
                tabelaHistorico.addCell(String.valueOf(turno.getAtaquePlayer()));
                tabelaHistorico.addCell(String.valueOf(turno.getDefesaOponente()));
                tabelaHistorico.addCell(String.valueOf(turno.getDanoCausadoPlayer()));
                tabelaHistorico.addCell(turno.getVencedorTurno());
            }

            documentoPDF.add(tabelaHistorico);
    }

        public void fecharDocumento() {
            if (documentoPDF != null && documentoPDF.isOpen()) {
                documentoPDF.close();
            }
        }


    }











