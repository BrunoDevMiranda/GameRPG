package br.com.gamerpg.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tb_hero")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Herois {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column
    private String classHero;
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
    private TipoDados tipoDados;
}
