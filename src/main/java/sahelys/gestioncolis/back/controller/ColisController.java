package sahelys.gestioncolis.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import sahelys.gestioncolis.back.dto.ChangerStatutDto;
import sahelys.gestioncolis.back.dto.ColisDto;
import sahelys.gestioncolis.back.dto.ReceptionDto;
import sahelys.gestioncolis.back.dto.SuiviColisDto;
import sahelys.gestioncolis.back.enums.StatutColis;

import java.util.List;

/**
 * Cette interface définit les points d'entrée REST disponibles pour les colis,
 * notamment le suivi et la réception.
 *
 * @author PC2
 */

public interface ColisController {

    ResponseEntity<List<ColisDto>> recupererListeDesColis();

    ResponseEntity<ColisDto> findById(@PathVariable("id") Long idColis);

    ResponseEntity<ColisDto> rechercherParNumeroSuivi(@RequestParam("numeroSuivi") String numeroSuivi);

    ResponseEntity<StatutColis> recupererStatutActuel(@PathVariable("id") Long idColis);

    ResponseEntity<List<SuiviColisDto>> recupererHistorique(@PathVariable("id") Long idColis);

    ResponseEntity<ColisDto> faireEvoluerStatut(@PathVariable("id") Long idColis, ChangerStatutDto dto);

    ResponseEntity<ColisDto> reception(ReceptionDto dto);

    ResponseEntity<Void> supprimer(@PathVariable("id") Long idColis);
}
