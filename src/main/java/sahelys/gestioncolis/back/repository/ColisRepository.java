package sahelys.gestioncolis.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sahelys.gestioncolis.back.model.Colis;

import java.util.Optional;

/**
 * Cette interface permet d'accéder aux données des colis en base.
 *
 * @author PC2
 */

public interface ColisRepository extends JpaRepository<Colis, Long> {
    boolean existsByNumeroSuivi(String numeroSuivi);

    Optional<Colis> findByNumeroSuivi(String numeroSuivi);
}
