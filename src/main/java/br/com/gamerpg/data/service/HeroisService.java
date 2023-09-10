package br.com.gamerpg.data.service;

import br.com.gamerpg.data.model.Herois;
import br.com.gamerpg.data.repository.HeroisRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HeroisService {
    private final HeroisRepository repository;


    @Transactional
    public Herois findByID(Long id) {
        Optional<Herois> Herois = repository.findById(id);
        return Herois.orElse(null);
    }

    @Transactional
    public List<Herois> getAll() {
        return repository.findAll();
    }

    @Transactional
    public Herois save(Herois Herois) {
        return repository.save(Herois);
    }

    @Transactional
    public Herois update(Herois herois) {
        Optional<Herois> existingHerois = repository.findById(herois.getId());

        if (existingHerois.isPresent()) {
            Herois updatedHerois = existingHerois.get();
            // Atualize os campos do objeto updatedHerois com os valores de Herois
            updatedHerois.setClassHero(herois.getClassHero());
            updatedHerois.setQuantidadeDados(herois.getQuantidadeDados());
            updatedHerois.setForca(herois.getForca());
            updatedHerois.setDefesa(herois.getDefesa());
            updatedHerois.setAgilidade(herois.getAgilidade());
            updatedHerois.setVida(herois.getVida());
            updatedHerois.setTipoDados(herois .getTipoDados());
            // Continue atualizando os outros campos conforme necessário

            // Salve o objeto atualizado de volta no banco de dados
            return repository.save(updatedHerois);
        } else {
            // Lida com o caso em que o Herois não foi encontrado
            // Pode lançar uma exceção ou realizar outra ação apropriada
            return null; // Ou outra coisa apropriada para seu caso
        }
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);

    }
}
