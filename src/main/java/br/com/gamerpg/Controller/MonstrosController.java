package br.com.gamerpg.Controller;

import br.com.gamerpg.data.model.Monstros;
import br.com.gamerpg.data.service.MonstroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/monstros")
public class MonstrosController {

    private final MonstroService service;

    public MonstrosController(MonstroService service) {
        this.service = service;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Monstros> getMonstrosById(@PathVariable Long id) {
        Monstros Monstros = service.findByID(id);
        if (Monstros != null) {
            return ResponseEntity.ok(Monstros);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Monstros>> getAll() {
        List<Monstros> Monstross = service.getAll();
        return ResponseEntity.ok(Monstross);
    }

    @PostMapping
    public ResponseEntity<Monstros> create(@RequestBody Monstros Monstros) {
        Monstros savedMonstros = service.save(Monstros);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMonstros);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Monstros> update(@PathVariable Long id, @RequestBody Monstros Monstros) {
        Monstros.setId(id);
        Monstros updatedMonstros = service.update(Monstros);
        if (updatedMonstros != null) {
            return ResponseEntity.ok(updatedMonstros);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}