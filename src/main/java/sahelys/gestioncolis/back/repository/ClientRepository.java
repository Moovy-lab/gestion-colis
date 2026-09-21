package sahelys.gestioncolis.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sahelys.gestioncolis.back.model.Client;

/**
 * Cette interface permet d'accéder aux données des clients en base.
 *
 * @author PC2
 */

public interface ClientRepository extends JpaRepository<Client, Long> {
}
