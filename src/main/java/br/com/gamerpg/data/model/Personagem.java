package br.com.gamerpg.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;

import static br.com.gamerpg.data.model.TipoDados.D12;
import static jakarta.persistence.GenerationType.IDENTITY;
import static java.lang.System.*;

@Entity
@Table(name = "tb_personagem")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Personagem {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column
    private String classe;
    @Enumerated(EnumType.STRING)
    private TipoPersonagem tipo;
    @Column
    private int vida;
    @Column
    private int forca;
    @Column
    private int defesa;
    @Column
    private int agilidade;
    @Column
    private int quantidadeDados;
    @Enumerated(EnumType.STRING)
    private TipoDados tipoDados; // D12 , D8 , D6 ,D4


    public int rolarDado(int quantidadeRolagens) {
        Random random = new Random();
        int resultadoDados = 0; // Inicialize o resultado como 0
//        System.out.println("Tipo de dado a ser jogado: " + tipoDados);

        for (int i = 0; i < quantidadeRolagens; i++) {
            int valorRolagem = random.nextInt(getTipoDados().getValorDado()) + 1; // Rolagem individual
//            System.out.println("Resultado do Dado " + (i + 1) + ": " + valorRolagem);
            resultadoDados += valorRolagem; // Some o resultado de cada rolagem
        }
        out.println("Resultado da Soma das Rolagens: " + resultadoDados);
        return resultadoDados;
    }
    public int rolarDado12(){
        Random random = new Random();
        int resultadoDados;
        resultadoDados = random.nextInt(D12.getValorDado() +1);
        return resultadoDados;
    }
    public int getQuantidadeDados() {
        return quantidadeDados;
    }

    public void setQuantidadeDados(int quantidadeDados) {
        this.quantidadeDados = quantidadeDados;
    }
}
