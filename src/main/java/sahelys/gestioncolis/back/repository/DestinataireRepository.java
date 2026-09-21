package sahelys.gestioncolis.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sahelys.gestioncolis.back.model.Destinataire;

import java.util.Optional;

/**
 * Cette interface permet d'accéder aux données des destinataires en base.
 *
 * @author PC2
 */

public interface DestinataireRepository extends JpaRepository<Destinataire, Long> {
    Optional<Destinataire> findByTelephone(String telephone);
}
