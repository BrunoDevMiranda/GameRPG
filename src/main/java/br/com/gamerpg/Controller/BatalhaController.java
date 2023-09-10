package br.com.gamerpg.Controller;

import br.com.gamerpg.data.model.CampoBatalha;
import br.com.gamerpg.data.service.BatalhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batalha")
public class BatalhaController {

    @Autowired
    private BatalhaService batalhaService;


}
