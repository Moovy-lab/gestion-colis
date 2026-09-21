package sahelys.gestioncolis.back.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sahelys.gestioncolis.back.dto.AgenceDto;

import java.util.List;

/**
 * Cette interface définit les points d'entrée REST disponibles pour les agences.
 *
 * @author PC2
 */

public interface AgenceController {

    public ResponseEntity<Void> ajouter(AgenceDto agenceDto);

    AgenceDto modifier(Integer idAgence, AgenceDto agenceDto);

    ResponseEntity<List<AgenceDto>> recupererListeDesContacts();

    ResponseEntity<AgenceDto> findById(@PathVariable("id") Integer idAgence);
}
