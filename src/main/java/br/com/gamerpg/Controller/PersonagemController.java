package br.com.gamerpg.Controller;

import br.com.gamerpg.data.model.Personagem;
import br.com.gamerpg.data.service.PersonagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/personagem")
public class PersonagemController {

    private final PersonagemService service;


    @GetMapping("/{id}")
    public ResponseEntity<Personagem> getHeroiById(@PathVariable("id") Long id) {
        Personagem personagem = service.findByID(id);
        if (personagem != null) {
            return ResponseEntity.ok(personagem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Personagem>> getAllHerois() {
        List<Personagem> personagem = service.getAll();
        return ResponseEntity.ok(personagem);
    }

    @PostMapping
    public ResponseEntity<Personagem> create(@RequestBody Personagem personagem) {
        Personagem savedPersonagem = service.save(personagem);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPersonagem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personagem> update(@PathVariable Long id, @RequestBody Personagem personagem) {
        personagem.setId(id);
        Personagem updatePerson = service.update(personagem);
        if (updatePerson != null) {
            return ResponseEntity.ok(updatePerson);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}