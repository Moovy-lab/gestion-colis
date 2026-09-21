package sahelys.gestioncolis.back.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import sahelys.gestioncolis.back.enums.StatutColis;

/**
 * Cette classe contient les données nécessaires pour faire évoluer le statut d'un colis.
 *
 * @author PC2
 */

@Data
@NoArgsConstructor
public class ChangerStatutDto {

    @NotNull(message = "Le statut est obligatoire")
    private StatutColis statut;

    @NotBlank(message = "Le commentaire est obligatoire")
    @Size(max = 255, message = "Le nombre de caractère du commentaire ne doit pas dépasser 255")
    private String commentaire;
}
