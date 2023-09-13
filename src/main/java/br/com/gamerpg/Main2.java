package br.com.gamerpg;

import java.util.Random;

public class Main2 {
    public static void main(String[] args) {
        Random random = new Random();
        int resultadoDadoPlayer = random.nextInt(20) + 1;
        System.out.println("Dado do Player foi :" + resultadoDadoPlayer);// Lança um dado de 20 lados para o Player
        int turno = 1;
        int hp1 = 10;
        int hp2 = 10;
        int resultadoDadoOponente = random.nextInt(20) + 1;
        System.out.println("Dado do Oponente foi :" + resultadoDadoOponente);//
        boolean vitoriaPlayer = false;
        boolean vitoriaOponente = false;
        boolean playerComecaProximoTurno = resultadoDadoPlayer >= resultadoDadoOponente;

        while (!vitoriaPlayer && !vitoriaOponente) {
            int numero = random.nextInt(20) + 1;

            if (playerComecaProximoTurno) {
                if (numero > 5) {
                    System.out.println("Estou dentro do 1");
                    hp1 -= 1;
                } else {
                    System.out.println("Nada Acontece1");
                }

            }
            else {
                if (numero < 15) {
                    hp2 -= 1;
                    System.out.println("estou no 2");
                } else {
                    System.out.println("Nada Acontece2");
                }
            }

                if (hp1 <= 0) {
                    System.out.println("Terminou1");
                    vitoriaPlayer = true;
                    break;
                }

                if (hp2 <= 0) {
                    System.out.println("Terminou2");
                    vitoriaOponente = true;
                    break;
                }
            turno++;
                playerComecaProximoTurno = !playerComecaProximoTurno;
            }

        }
    }

