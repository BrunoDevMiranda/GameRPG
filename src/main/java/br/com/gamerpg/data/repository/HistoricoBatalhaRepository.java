package br.com.gamerpg.data.repository;



import br.com.gamerpg.data.model.HistoricoBatalha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoBatalhaRepository extends JpaRepository<HistoricoBatalha, Long> {
    // Métodos de consulta personalizados, se necessário
}
