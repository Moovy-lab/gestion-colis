package sahelys.gestioncolis.back.mapper;


import org.mapstruct.Mapper;
import sahelys.gestioncolis.back.dto.DestinataireDto;
import sahelys.gestioncolis.back.model.Destinataire;

/**
 * Cette classe convertit les données entre l'entité Destinataire et son DTO.
 *
 * @author PC2
 */

@Mapper(componentModel = "spring")
public interface DestinataireMapper {
    public Destinataire toEntity(DestinataireDto dto);

    public DestinataireDto toDto(Destinataire destinataire);
}
