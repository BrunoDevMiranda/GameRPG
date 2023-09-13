package br.com.gamerpg.Controller;

import br.com.gamerpg.data.model.CampoBatalha;
import br.com.gamerpg.data.service.BatalhaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/batalha")
public class BatalhaController {
    private final BatalhaService batalhaService;
    @GetMapping("/realizar")
    public ResponseEntity<CampoBatalha> realizarBatalha() {
        // Aqui você pode chamar o serviço de batalha para realizar uma batalha
        // Certifique-se de que o serviço retorne o resultado da batalha (CampoBatalha)
        CampoBatalha resultadoBatalha = batalhaService.realizarBatalha();

        if (resultadoBatalha != null) {
            return ResponseEntity.ok(resultadoBatalha);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

