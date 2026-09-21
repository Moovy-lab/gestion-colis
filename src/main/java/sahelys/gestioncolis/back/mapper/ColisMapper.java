package sahelys.gestioncolis.back.mapper;


import org.mapstruct.Mapper;
import sahelys.gestioncolis.back.dto.ColisDto;
import sahelys.gestioncolis.back.model.Colis;

/**
 * Cette classe convertit les données entre l'entité Colis et son DTO.
 *
 * @author PC2
 */

@Mapper(componentModel = "spring")
public interface ColisMapper {
    public Colis toEntity(ColisDto dto);

    public ColisDto toDto(Colis colis);
}
