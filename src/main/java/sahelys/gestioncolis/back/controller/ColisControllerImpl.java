package sahelys.gestioncolis.back.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sahelys.gestioncolis.back.dto.ChangerStatutDto;
import sahelys.gestioncolis.back.dto.ColisDto;
import sahelys.gestioncolis.back.dto.ReceptionDto;
import sahelys.gestioncolis.back.dto.SuiviColisDto;
import sahelys.gestioncolis.back.enums.StatutColis;
import sahelys.gestioncolis.back.service.ColisService;
import sahelys.gestioncolis.back.service.SuiviColisService;

import java.util.List;

/**
 * Cette classe implémente les points d'entrée REST disponibles pour les colis,
 * notamment le suivi et la réception.
 *
 * @author PC2
 */

@RestController
@RequestMapping("/colis")
public class ColisControllerImpl implements ColisController {

    private final ColisService colisService;
    private final SuiviColisService suiviColisService;

    public ColisControllerImpl(ColisService colisService, SuiviColisService suiviColisService) {
        this.colisService = colisService;
        this.suiviColisService = suiviColisService;
    }

    @GetMapping
    @Override
    public ResponseEntity<List<ColisDto>> recupererListeDesColis() {
        return ResponseEntity.ok().body(colisService.recupererListeDesColis());
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ColisDto> findById(@PathVariable("id") Long idColis) {
        return ResponseEntity.ok().body(colisService.findById(idColis));
    }

    @GetMapping("/recherche")
    @Override
    public ResponseEntity<ColisDto> rechercherParNumeroSuivi(@RequestParam("numeroSuivi") String numeroSuivi) {
        return ResponseEntity.ok().body(colisService.rechercherParNumeroSuivi(numeroSuivi));
    }

    @GetMapping("/{id}/statut")
    @Override
    public ResponseEntity<StatutColis> recupererStatutActuel(@PathVariable("id") Long idColis) {
        return ResponseEntity.ok().body(colisService.recupererStatutActuel(idColis));
    }

    @GetMapping("/{id}/historique")
    @Override
    public ResponseEntity<List<SuiviColisDto>> recupererHistorique(@PathVariable("id") Long idColis) {
        colisService.findById(idColis);
        return ResponseEntity.ok().body(suiviColisService.recupererHistorique(idColis));
    }

    @PatchMapping("/{id}/statut")
    @Override
    public ResponseEntity<ColisDto> faireEvoluerStatut(@PathVariable("id") Long idColis, @Valid @RequestBody ChangerStatutDto dto) {
        return ResponseEntity.ok().body(colisService.faireEvoluerStatut(idColis, dto));
    }

    @PostMapping("/reception")
    @Override
    public ResponseEntity<ColisDto> reception(@Valid @RequestBody ReceptionDto dto) {
        return ResponseEntity.ok().body(colisService.reception(dto));
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> supprimer(@PathVariable("id") Long idColis) {
        colisService.supprimer(idColis);
        return ResponseEntity.noContent().build();
    }
}
