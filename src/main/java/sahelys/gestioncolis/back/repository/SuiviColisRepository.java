package sahelys.gestioncolis.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sahelys.gestioncolis.back.model.SuiviColis;

import java.util.List;

/**
 * Cette interface permet d'accéder à l'historique de suivi des colis en base.
 *
 * @author PC2
 */

public interface SuiviColisRepository extends JpaRepository<SuiviColis, Long> {
    List<SuiviColis> findByColis_IdColisOrderByDateStatutAsc(Long idColis);

    void deleteByColis_IdColis(Long idColis);
}
