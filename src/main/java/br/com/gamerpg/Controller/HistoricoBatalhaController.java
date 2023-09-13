package br.com.gamerpg.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.gamerpg.data.model.HistoricoBatalha;
import br.com.gamerpg.data.service.HistoricoBatalhaService;

import java.util.List;

@RestController
@RequestMapping("/historico-batalha")
public class HistoricoBatalhaController {

    private final HistoricoBatalhaService historicoBatalhaService;

    @Autowired
    public HistoricoBatalhaController(HistoricoBatalhaService historicoBatalhaService) {
        this.historicoBatalhaService = historicoBatalhaService;
    }

    @GetMapping
    public ResponseEntity<List<HistoricoBatalha>> listarHistoricoBatalha() {
        List<HistoricoBatalha> historicoBatalha = historicoBatalhaService.listarHistoricoBatalha();
        return ResponseEntity.ok(historicoBatalha);
    }

    @PostMapping
    public ResponseEntity<List<HistoricoBatalha>> criarHistoricoBatalha(@RequestBody List<HistoricoBatalha> historicos) {
        List<HistoricoBatalha> novosHistoricos = historicoBatalhaService.salvarHistoricoBatalha(historicos);
        return ResponseEntity.status(HttpStatus.CREATED).body(novosHistoricos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoricoBatalha> getHistoricoBatalhaById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        HistoricoBatalha historicoBatalha = historicoBatalhaService.findById(id);
        return ResponseEntity.ok(historicoBatalha);
    }


}
