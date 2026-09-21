package sahelys.gestioncolis.back.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import sahelys.gestioncolis.back.enums.StatutColis;

import java.time.LocalDateTime;

/**
 * Cette classe représente une entrée de l'historique de suivi d'un colis.
 *
 * @author PC2
 */

@Data
@NoArgsConstructor
public class SuiviColisDto {

    private Long idSuivi;

    @NotNull(message = "Le statut est obligatoire")
    private StatutColis statut;

    @NotNull(message = "La date est obligatoire")
    private LocalDateTime dateStatut;

    @NotBlank(message = "Le commentaire est obligatoire")
    @Size(max = 255, message = "Le nombre de caractère du commentaire ne doit pas dépasser 255")
    private String commentaire;
}
