package br.com.gamerpg.data.service;



import br.com.gamerpg.data.model.HistoricoBatalha;
import br.com.gamerpg.data.repository.HistoricoBatalhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoricoBatalhaService {

    private final HistoricoBatalhaRepository repository;

    public List<HistoricoBatalha> salvarHistoricoBatalha(List<HistoricoBatalha> historicos) {
        return repository.saveAll(historicos);
    }

    public List<HistoricoBatalha> listarHistoricoBatalha() {
        return repository.findAll();
    }

    public HistoricoBatalha findById(Long id) throws ChangeSetPersister.NotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    // Outros métodos de consulta ou operações relacionadas ao histórico de batalha
}

