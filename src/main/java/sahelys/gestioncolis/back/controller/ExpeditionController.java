package sahelys.gestioncolis.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import sahelys.gestioncolis.back.dto.ExpeditionCreationDto;
import sahelys.gestioncolis.back.dto.ExpeditionDto;

import java.util.List;

/**
 * Cette interface définit les points d'entrée REST disponibles pour les expéditions.
 *
 * @author PC2
 */

public interface ExpeditionController {

    ResponseEntity<Void> creerExpedition(ExpeditionCreationDto dto);

    ResponseEntity<ExpeditionDto> findById(@PathVariable("id") Long idExpedition);

    ResponseEntity<List<ExpeditionDto>> recupererListeDesExpeditions();
}
