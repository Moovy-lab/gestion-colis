package sahelys.gestioncolis.back.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sahelys.gestioncolis.back.dto.AgenceDto;
import sahelys.gestioncolis.back.service.AgenceService;

import java.net.URI;
import java.util.List;


/**
 * Cette classe implémente les points d'entrée REST disponibles pour les agences.
 *
 * @author PC2
 */

@RestController
public class AgenceControllerImpl implements AgenceController {

    private final AgenceService agenceService;

    public AgenceControllerImpl(AgenceService agenceService) {
        this.agenceService = agenceService;
    }

    @PostMapping("/agences")
    @Override
    public ResponseEntity<Void> ajouter(@Valid @RequestBody AgenceDto agenceDto){
        AgenceDto agenceCreated = agenceService.ajouter(agenceDto);

        URI newResourceLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(agenceCreated.getIdAgence())
                .toUri();

        return ResponseEntity.created(newResourceLocation).build();
    }

    @PutMapping("/agences/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public AgenceDto modifier(@PathVariable("id") Integer idAgence, @RequestBody AgenceDto agenceDto) {
        return agenceService.modifier(agenceDto);
    }

    @GetMapping("/agences")
    @Override
    public ResponseEntity<List<AgenceDto>> recupererListeDesContacts() {
        List<AgenceDto> agenceTrouves = agenceService.recupererListeDesContacts();

        return ResponseEntity.ok().body(agenceTrouves);
    }

    @GetMapping("/agences/{id}")
    @Override
    public ResponseEntity<AgenceDto> findById(@PathVariable("id") Integer idAgence){
        AgenceDto agenceTrouve = agenceService.findById(idAgence);
        return ResponseEntity.ok().body(agenceTrouve);
    }
}
