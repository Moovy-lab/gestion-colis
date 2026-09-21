package sahelys.gestioncolis.back.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sahelys.gestioncolis.back.dto.ExpeditionCreationDto;
import sahelys.gestioncolis.back.dto.ExpeditionDto;
import sahelys.gestioncolis.back.service.ExpeditionService;

import java.net.URI;
import java.util.List;

/**
 * Cette classe implémente les points d'entrée REST disponibles pour les expéditions.
 *
 * @author PC2
 */

@RestController
@RequestMapping("/expeditions")
public class ExpeditionControllerImpl implements ExpeditionController {

    private final ExpeditionService expeditionService;

    public ExpeditionControllerImpl(ExpeditionService expeditionService) {
        this.expeditionService = expeditionService;
    }

    @PostMapping
    @Override
    public ResponseEntity<Void> creerExpedition(@Valid @RequestBody ExpeditionCreationDto dto) {
        ExpeditionDto expeditionCreated = expeditionService.creerExpedition(dto);

        URI newResourceLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(expeditionCreated.getIdExpedition())
                .toUri();

        return ResponseEntity.created(newResourceLocation).build();
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ExpeditionDto> findById(@PathVariable("id") Long idExpedition) {
        return ResponseEntity.ok().body(expeditionService.findById(idExpedition));
    }

    @GetMapping
    @Override
    public ResponseEntity<List<ExpeditionDto>> recupererListeDesExpeditions() {
        return ResponseEntity.ok().body(expeditionService.recupererListeDesExpeditions());
    }
}
