package br.com.gamerpg.Controller;

import br.com.gamerpg.data.model.Personagem;
import br.com.gamerpg.data.service.PersonagemService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.System.out;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jogo")
public class JogoController {

    private final PersonagemService service;
    private final HttpSession httpSession;

    @GetMapping("/escolher-personagem")
    public ResponseEntity<String> escolherPersonagem(@RequestParam("categoria") String categoria,
                                                     @RequestParam("classe") String classe) {

        Personagem personagemEscolhido = null;

        if ("heroi".equalsIgnoreCase(categoria) || "monstro".equalsIgnoreCase(categoria)) {
            personagemEscolhido = service.escolherPorNomeETipo(classe, categoria);
            out.println("Personagem Escolhido foi" + personagemEscolhido);
        }

        if (personagemEscolhido != null) {
            httpSession.setAttribute("personagemEscolhido", personagemEscolhido);
            return ResponseEntity.ok("Você escolheu um personagem: " + personagemEscolhido.getClasse());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}




