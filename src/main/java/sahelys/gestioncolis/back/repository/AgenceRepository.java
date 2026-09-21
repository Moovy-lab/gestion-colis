package sahelys.gestioncolis.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sahelys.gestioncolis.back.model.Agence;

/**
 * Cette interface permet d'accéder aux données des agences en base.
 *
 * @author PC2
 */

public interface AgenceRepository extends JpaRepository<Agence, Integer> {
}
