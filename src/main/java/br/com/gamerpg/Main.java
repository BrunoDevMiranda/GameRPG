package br.com.gamerpg;

import br.com.gamerpg.data.model.Personagem;

import static br.com.gamerpg.data.model.TipoDados.D6;
import static br.com.gamerpg.data.model.TipoDados.D8;

public class Main {
    public static void main(String[] args) {
        Personagem player = new Personagem();
        Personagem monstro = new Personagem();


        boolean playerWin = false;
        boolean oponenteWin = false;
        int rodada = 1;
        player.setVida(21);
        player.setForca(10);
        player.setAgilidade(5);
        player.setTipoDados(D8);
        monstro.setVida(42);
        monstro.setAgilidade(2);
        monstro.setForca(7);
        monstro.setTipoDados(D6);

        int hpAtualPlayer = player.getVida();
        int hpAtualMonstro = monstro.getVida();


        // Imprime os atributos iniciais

        System.out.println("Atributos iniciais:");
        System.out.println("Player:");
        System.out.println("HP: " + hpAtualPlayer);
        System.out.println("Força: " + player.getForca());
        System.out.println("Agilidade: " + player.getAgilidade());
        System.out.println("*************************************");
        System.out.println("Monstro:");
        System.out.println("HP: " + hpAtualMonstro);
        System.out.println("Força: " + monstro.getForca());
        System.out.println("Agilidade: " + monstro.getAgilidade());

        while (!playerWin && !oponenteWin) {
            System.out.println("---------------------------------------RODADA" + rodada + "------------------------------------------------------------------");

            System.out.println("-----Heroi-----");
            // Calcula o dano causado
            int diceHeroi = player.rolarDado(2);
            int danoPlayer = diceHeroi + player.getForca() + player.getAgilidade();
            System.out.println("Força: " + player.getForca());
            System.out.println("Agilidade: " + player.getAgilidade());
            System.out.println("Dados: " + diceHeroi);
            System.out.println("Total: " + danoPlayer);

            System.out.println("----Monstro----");
            int diceMon = monstro.rolarDado(3);
            int danoMonstro = diceMon + monstro.getForca() + monstro.getAgilidade();
            System.out.println("Força: "+ monstro.getForca());
            System.out.println("Agilidade:  " + monstro.getAgilidade());
            System.out.println(" Dados: "+ diceMon);
            System.out.println("Total: "+ danoMonstro);

            // Verifica quem inicia o ataque
            boolean iniciaAtaque = (danoPlayer + player.rolarDado12()) > (danoMonstro + monstro.rolarDado12());

            // Atualiza o HP do personagem
            if (iniciaAtaque) {
                int danoRecebidoMonstro = danoPlayer - danoMonstro;
                hpAtualMonstro -= danoRecebidoMonstro;
                System.out.println("Luta:");
                System.out.println("Player Ataca:");
                System.out.println("Poder total: " + danoPlayer);
                System.out.println("Dano Monstro recebeu foi : " + danoRecebidoMonstro);
                System.out.println("HP atual do monstro: " + hpAtualMonstro);
            } else {
                int danoRecebidoPlayer = danoMonstro - danoPlayer;
                hpAtualPlayer -= danoRecebidoPlayer;

                System.out.println("Luta:");
                System.out.println("Monstro Ataca:");
                System.out.println("Poder total: " + danoMonstro);
                System.out.println("Dano Heroi recebeu foi : " + danoRecebidoPlayer);
                System.out.println("HP atual do Heroi: " + hpAtualPlayer);
            }

            ;

            // Verifica se o personagem morreu
            if (hpAtualMonstro <= 0) {
                playerWin = true;
                System.out.println("Fim Da Rodada " + rodada + ": Vencedor foi o Player");
                break;
            }

            if (hpAtualPlayer <= 0) {
                oponenteWin = true;
                System.out.println("Fim Da Rodada " + rodada + ": Vencedor foi o Oponente");
                break;
            }
            rodada++;
        }


    }
}
