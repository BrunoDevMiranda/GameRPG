package br.com.gamerpg.Controller;

import br.com.gamerpg.data.model.ResultadoBatalha;
import br.com.gamerpg.data.service.ResultadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resultados")
public class ResultadoController {

    private final ResultadoService resultadoService;

    @Autowired
    public ResultadoController(ResultadoService resultadoService) {
        this.resultadoService = resultadoService;
    }

    @GetMapping
    public List<ResultadoBatalha> getAllResultados() {
        return resultadoService.getAll();
    }

    @GetMapping("/{id}")
    public ResultadoBatalha getResultadoById(@PathVariable Long id) {
        return resultadoService.findByID(id);
    }
}
