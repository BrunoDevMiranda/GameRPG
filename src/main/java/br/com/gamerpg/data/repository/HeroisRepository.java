package br.com.gamerpg.data.repository;

import br.com.gamerpg.data.model.Herois;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroisRepository extends JpaRepository<Herois, Long> {
}
