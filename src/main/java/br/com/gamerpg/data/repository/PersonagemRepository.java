package br.com.gamerpg.data.repository;

import br.com.gamerpg.data.model.Personagem;
import br.com.gamerpg.data.model.TipoPersonagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
    Optional<Personagem> findByClasseAndTipo(String classe, TipoPersonagem tipo);

    List<Personagem> findByTipo(TipoPersonagem tipoPersonagem);
}
