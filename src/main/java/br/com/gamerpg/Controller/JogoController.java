package br.com.gamerpg.Controller;

import br.com.gamerpg.data.model.Herois;
import br.com.gamerpg.data.model.Monstros;
import br.com.gamerpg.data.service.HeroisService;
import br.com.gamerpg.data.service.MonstrosService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jogo")
public class JogoController {

    private final HeroisService heroisService;
    private final MonstrosService monstrosService;
    private final HttpSession httpSession;



    @GetMapping("/escolher-personagem")
    public ResponseEntity<String> escolherPersonagem(
            @RequestParam("categoria") String categoria,
            @RequestParam("nome") String nome) {
        // Agora você pode acessar a sessão do usuário por meio de httpSession
        if ("heroi".equalsIgnoreCase(categoria)) {
            Herois heroiEscolhido = heroisService.escolherHeroiPorNome(nome);
            if (heroiEscolhido != null) {
                // Use a sessão para armazenar informações do usuário, se necessário
                httpSession.setAttribute("heroiEscolhido", heroiEscolhido);
                return ResponseEntity.ok("Você escolheu um herói: " + heroiEscolhido.getClassHero());
            } else {
                return ResponseEntity.notFound().build();
            }
        } else if ("monstro".equalsIgnoreCase(categoria)) {
            Monstros monstroEscolhido = monstrosService.escolherMonstroPorNome(nome);
            if (monstroEscolhido != null) {
                // Use a sessão para armazenar informações do usuário, se necessário
                httpSession.setAttribute("monstroEscolhido", monstroEscolhido);
                return ResponseEntity.ok("Você escolheu um monstro: " + monstroEscolhido.getClassMontro());
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().body("Escolha inválida.");
        }
    }



}
