package br.com.gamerpg.data.service;

import br.com.gamerpg.data.model.Monstros;
import br.com.gamerpg.data.repository.MonstrosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MonstrosService {
    private final MonstrosRepository repository;

    public Monstros escolherMonstroPorNome(String nome) {
        Optional<Monstros> monstroOptional = repository.findByNome(nome);
        return monstroOptional.orElse(null);
    }
    public Monstros encontrarMonstroPorNome(String nome) {
        return repository.findByClassMontro(nome);
    }



    @Transactional
    public Monstros findByID(Long id) {
        Optional<Monstros> monstros = repository.findById(id);
        return monstros.get();
    }

    @Transactional
    public List<Monstros> getAll() {
        return repository.findAll();
    }

    @Transactional
    public Monstros save(Monstros monstros) {
        return repository.save(monstros);
    }

    @Transactional
    public Monstros update(Monstros monstros) {
        Optional<Monstros> existingMonstro = repository.findById(monstros.getId());

        if (existingMonstro.isPresent()) {
            Monstros updatedMonstro = existingMonstro.get();
            // Atualize os campos do objeto updatedMonstro com os valores de monstros
            updatedMonstro.setClassMontro(monstros.getClassMontro());
            updatedMonstro.setForca(monstros.getForca());
            updatedMonstro.setDefesa(monstros.getDefesa());
            updatedMonstro.setAgilidade(monstros.getAgilidade());
            updatedMonstro.setVida(monstros.getVida());
            updatedMonstro.setQuantidadeDados(monstros.getQuantidadeDados());
            updatedMonstro.setTipoDados(monstros.getTipoDados());
            // Continue atualizando os outros campos conforme necessário

            // Salve o objeto atualizado de volta no banco de dados
            return repository.save(updatedMonstro);
        } else {
            // Lida com o caso em que o monstro não foi encontrado
            // Pode lançar uma exceção ou realizar outra ação apropriada
            return null; // Ou outra coisa apropriada para seu caso
        }


    }



}
