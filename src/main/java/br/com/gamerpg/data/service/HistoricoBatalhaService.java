package br.com.gamerpg.data.service;


import br.com.gamerpg.data.model.HistoricoBatalha;
import br.com.gamerpg.data.repository.HistoricoBatalhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistoricoBatalhaService {

    private final HistoricoBatalhaRepository repository;

    public List<HistoricoBatalha> save(List<HistoricoBatalha> historicoBatalhaList) {
        // Salvar cada histórico de batalha individualmente
        List<HistoricoBatalha> historicosSalvos = new ArrayList<>();
        for (HistoricoBatalha historicoBatalha : historicoBatalhaList) {
            historicosSalvos.add(repository.save(historicoBatalha));
        }
        return historicosSalvos;
    }

    public List<HistoricoBatalha> findAll() {
        return repository.findAll();
    }

    public HistoricoBatalha findById(Long id)  {
        Optional<HistoricoBatalha>historicoBatalha = repository.findById(id);
        return historicoBatalha.get();
    }


    // Outros métodos de consulta ou operações relacionadas ao histórico de batalha
}

