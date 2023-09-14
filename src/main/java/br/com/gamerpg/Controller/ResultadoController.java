package br.com.gamerpg.Controller;

import br.com.gamerpg.data.model.ResultadoBatalha;
import br.com.gamerpg.data.service.ResultadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resultados")
public class ResultadoController {

    private final ResultadoService resultadoService;


    @Autowired
    public ResultadoController(ResultadoService resultadoService) {
        this.resultadoService = resultadoService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultadoBatalha save(@RequestBody ResultadoBatalha resultadoBatalha) {
        return resultadoBatalha;
    }

    @GetMapping
    public List<ResultadoBatalha> getAllResultados() {
        return resultadoService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultadoBatalha> getResultadoById(@PathVariable Long id) {
        ResultadoBatalha resultadoBatalha = resultadoService.findById(id);

        if (resultadoBatalha != null) {
            return ResponseEntity.ok(resultadoBatalha);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
