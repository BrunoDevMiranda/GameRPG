package br.com.gamerpg.data.repository;

import br.com.gamerpg.data.model.ResultadoBatalha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoRepository extends JpaRepository<ResultadoBatalha, Long> {
}
