package sahelys.gestioncolis.back.controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sahelys.gestioncolis.back.dto.ClientDto;
import sahelys.gestioncolis.back.service.ClientService;

import java.net.URI;
import java.util.List;

/**
 * Cette classe implémente les points d'entrée REST disponibles pour les clients.
 *
 * @author PC2
 */

@RestController
public class ClientControllerImpl implements ClientController {

    private final ClientService clientService;

    public ClientControllerImpl(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/clients")
    @Override
    public ResponseEntity<Void> ajouter(@Valid @RequestBody ClientDto clientDto) {
        ClientDto clientCreated = clientService.ajouter(clientDto);

        URI newResourceLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientCreated.getIdClient())
                .toUri();

        return ResponseEntity.created(newResourceLocation).build();
    }

    @PutMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public ClientDto modifier(@PathVariable("id") Long idClient, @RequestBody ClientDto clientDto) {
        return clientService.modifier(clientDto);
    }

    @GetMapping("/clients")
    @Override
    public ResponseEntity<List<ClientDto>> recupererListeDesClients() {
        List<ClientDto> clientTrouves = clientService.recupererListeDesClients();

        return ResponseEntity.ok().body(clientTrouves);
    }

    @GetMapping("/clients/{id}")
    @Override
    public ResponseEntity<ClientDto> findById(@PathVariable("id") Long idClient){
        ClientDto clientTrouve = clientService.findById(idClient);
        return ResponseEntity.ok().body(clientTrouve);
    }
}
