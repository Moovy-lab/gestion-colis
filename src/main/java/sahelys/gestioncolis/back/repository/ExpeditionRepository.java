package sahelys.gestioncolis.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sahelys.gestioncolis.back.model.Expedition;

import java.util.Optional;

/**
 * Cette interface permet d'accéder aux données des expéditions en base.
 *
 * @author PC2
 */

public interface ExpeditionRepository extends JpaRepository<Expedition, Long> {
    Optional<Expedition> findByColis_IdColis(Long idColis);
}
