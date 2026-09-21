package sahelys.gestioncolis.back.mapper;


import org.mapstruct.Mapper;
import sahelys.gestioncolis.back.dto.AgenceDto;
import sahelys.gestioncolis.back.model.Agence;


/**
 * Cette classe convertit les données entre l'entité Agence et son DTO.
 *
 * @author PC2
 */

@Mapper(componentModel = "spring")
public interface AgenceMapper {
    public Agence toEntity(AgenceDto dto);

    public AgenceDto toDto(Agence agence);
}
