package br.com.gamerpg.Controller;

import br.com.gamerpg.data.model.HistoricoBatalha;
import br.com.gamerpg.data.service.HistoricoBatalhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/historico-batalha")
public class HistoricoBatalhaController {

    private final HistoricoBatalhaService service;

    @Autowired
    public HistoricoBatalhaController(HistoricoBatalhaService historicoBatalhaService) {
        this.service = historicoBatalhaService;
    }

    @GetMapping
    public ResponseEntity<List<HistoricoBatalha>> findAll() {
        List<HistoricoBatalha> historicoBatalha = service.findAll();
        return ResponseEntity.ok(historicoBatalha);
    }



    @GetMapping("/{id}")
    public ResponseEntity<HistoricoBatalha> findById(@PathVariable Long id)  {
        HistoricoBatalha historicoBatalha = service.findById(id);
        return ResponseEntity.ok(historicoBatalha);
    }


}
