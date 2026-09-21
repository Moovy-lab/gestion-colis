package sahelys.gestioncolis.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import sahelys.gestioncolis.back.dto.ClientDto;

import java.util.List;

/**
 * Cette interface définit les points d'entrée REST disponibles pour les clients.
 *
 * @author PC2
 */

public interface ClientController {

    public ResponseEntity<Void> ajouter(ClientDto clientDto);

    ClientDto modifier(Long idClient, ClientDto clientDto);

    ResponseEntity<List<ClientDto>> recupererListeDesClients();

    ResponseEntity<ClientDto> findById(@PathVariable("id") Long idClient);
}
