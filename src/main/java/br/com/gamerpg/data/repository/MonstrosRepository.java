package br.com.gamerpg.data.repository;

import br.com.gamerpg.data.model.Monstros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MonstrosRepository extends JpaRepository<Monstros, Long> {

    @Query("SELECT m FROM Monstros m WHERE m.classMontro = :nome")
    Optional<Monstros> findByNome(@Param("nome") String nome);


    Monstros findByClassMontro(String nome);
}
