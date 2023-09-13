package br.com.gamerpg.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoBatalha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vencedor;
    private String quemIniciou;

    @OneToMany(mappedBy = "resultadoBatalha", cascade = CascadeType.ALL)
    private List<HistoricoBatalha> historicoBatalhaList = new ArrayList<>();

}
