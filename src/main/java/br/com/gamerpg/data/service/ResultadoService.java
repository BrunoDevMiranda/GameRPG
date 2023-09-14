package br.com.gamerpg.data.service;

import br.com.gamerpg.data.model.HistoricoBatalha;
import br.com.gamerpg.data.model.ResultadoBatalha;
import br.com.gamerpg.data.repository.ResultadoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ResultadoService {
    @Autowired
    HistoricoBatalhaService historicoBatalhaService;

    private final ResultadoRepository repository; // Remova a anotação @Autowired, pois o Lombok já injeta a dependência pelo construtor

    public List<ResultadoBatalha> getAll() {
        return repository.findAll();
    }

    @Transactional
    public ResultadoBatalha save(ResultadoBatalha resultadoBatalha , List<HistoricoBatalha> historicoBatalha) {
        List<HistoricoBatalha> historicoBatalhaList = new ArrayList<>();
        historicoBatalhaList = historicoBatalha;
        resultadoBatalha.setHistoricoBatalhaList(historicoBatalhaList);
        historicoBatalhaService.save(historicoBatalhaList);
        return repository.save(resultadoBatalha);
    }

    @Transactional
    public ResultadoBatalha findById(Long id) {
        Optional<ResultadoBatalha> resultadoBatalha = repository.findById(id);
        return resultadoBatalha.orElse(null);
    }

}



