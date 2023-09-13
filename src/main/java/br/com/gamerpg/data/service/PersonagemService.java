package br.com.gamerpg.data.service;

import br.com.gamerpg.data.model.Personagem;
import br.com.gamerpg.data.model.TipoPersonagem;
import br.com.gamerpg.data.repository.PersonagemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PersonagemService {
    private final PersonagemRepository repository ;

    public Personagem escolherPorNomeETipo(String classe, String categoria) {
        Optional<Personagem> personagemOptional ;

        if ("heroi".equalsIgnoreCase(categoria)) {
            personagemOptional = repository.findByClasseAndTipo(classe, TipoPersonagem.HEROI);
        } else if ("monstro".equalsIgnoreCase(categoria)) {
            personagemOptional = repository.findByClasseAndTipo(classe, TipoPersonagem.MONSTRO);
        }else {
            personagemOptional = Optional.empty(); // Inicializa com um Optional vazio
        }

        return personagemOptional.orElse(null);
    }
    @Transactional
    public Personagem findByID(Long id) {
        Optional<Personagem> personagem = repository.findById(id);
        return personagem.orElse(null);
    }

    @Transactional
    public List<Personagem> getAll() {
        return repository.findAll();
    }

    @Transactional
    public Personagem save(Personagem personagem) {
        return repository.save(personagem);
    }

    @Transactional
    public Personagem update(Personagem personagem) {
        Optional<Personagem> existingPersonagem = repository.findById(personagem.getId());

        if (existingPersonagem.isPresent()) {
            Personagem updatedPersonagem = existingPersonagem.get();


            updatedPersonagem.setClasse(personagem.getClasse());
            updatedPersonagem.setQuantidadeDados(personagem.getQuantidadeDados());
            updatedPersonagem.setForca(personagem.getForca());
            updatedPersonagem.setDefesa(personagem.getDefesa());
            updatedPersonagem.setAgilidade(personagem.getAgilidade());
            updatedPersonagem.setVida(personagem.getVida());
            updatedPersonagem.setTipoDados(personagem.getTipoDados());
            updatedPersonagem.setTipo(personagem.getTipo());

            return repository.save(updatedPersonagem);
        } else {
            return null;
        }
    }




    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);

    }


    public Personagem buscarAleatorio() {
        List<Personagem> personagems = repository.findAll();

        if (personagems.isEmpty()) {
            return null;
        }
        // Gere um índice aleatório para selecionar um monstro aleatório
        Random random = new Random();
        int indiceAleatorio = random.nextInt(personagems.size());

        // Retorne o monstro aleatório
        return personagems.get(indiceAleatorio);
    }

}

