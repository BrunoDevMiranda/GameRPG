package br.com.gamerpg.data.repository;

import br.com.gamerpg.data.model.Herois;
import br.com.gamerpg.data.model.Monstros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeroisRepository extends JpaRepository<Herois, Long> {

    @Query("SELECT m FROM Herois m WHERE m.classHero = :nome")
    Optional<Herois> findByNome(@Param("nome") String nome);

    Herois findByClassHero(String nome);
}
