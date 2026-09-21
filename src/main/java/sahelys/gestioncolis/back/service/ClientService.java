package sahelys.gestioncolis.back.service;

import jakarta.validation.Valid;
import sahelys.gestioncolis.back.dto.ClientDto;

import java.util.List;

/**
 * Cette interface définit les opérations métier disponibles pour les clients.
 *
 * @author PC2
 */

public interface ClientService {

    ClientDto ajouter(@Valid ClientDto clientDto);

    ClientDto modifier(ClientDto clientDto);

    List<ClientDto> recupererListeDesClients();

    ClientDto findById(Long idClient);
}
