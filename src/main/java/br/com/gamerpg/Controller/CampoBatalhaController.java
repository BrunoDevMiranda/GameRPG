package br.com.gamerpg.Controller;

import br.com.gamerpg.data.service.CampoBatalha;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/batalha")
public class CampoBatalhaController {
    private final CampoBatalha batalhaService;

    @GetMapping("/realizar")
    public ResponseEntity<CampoBatalha> realizarBatalha() throws DocumentException, IOException {
         batalhaService.realizarBatalha();
         return new ResponseEntity<>(HttpStatus.OK);
    }


}

