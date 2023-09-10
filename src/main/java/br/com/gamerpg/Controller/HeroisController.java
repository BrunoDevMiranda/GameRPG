package br.com.gamerpg.Controller;

import br.com.gamerpg.data.model.Herois;
import br.com.gamerpg.data.service.HeroisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/herois")
public class HeroisController {

    private final HeroisService heroiService;

    public HeroisController(HeroisService heroiService) {
        this.heroiService = heroiService;
    }



    @GetMapping("/{id}")
    public ResponseEntity<Herois> getHeroiById(@PathVariable("id") Long id) {
        Herois heroi = heroiService.findByID(id);
        if (heroi != null) {
            return ResponseEntity.ok(heroi);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Herois>> getAllHerois() {
        List<Herois> herois = heroiService.getAll();
        return ResponseEntity.ok(herois);
    }

    @PostMapping
    public ResponseEntity<Herois> createHeroi(@RequestBody Herois heroi) {
        Herois savedHeroi = heroiService.save(heroi);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedHeroi);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Herois> updateHeroi(@PathVariable Long id, @RequestBody Herois heroi) {
        heroi.setId(id);
        Herois updatedHeroi = heroiService.update(heroi);
        if (updatedHeroi != null) {
            return ResponseEntity.ok(updatedHeroi);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteHeroi(@PathVariable Long id) {
       heroiService.delete(id);
    }
}