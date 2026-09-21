package sahelys.gestioncolis.back.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sahelys.gestioncolis.back.dto.SuiviColisDto;
import sahelys.gestioncolis.back.model.SuiviColis;

/**
 * Cette classe convertit les données entre l'entité SuiviColis et son DTO.
 *
 * @author PC2
 */

@Mapper(componentModel = "spring")
public interface SuiviColisMapper {
    @Mapping(target = "colis", ignore = true)
    SuiviColis toEntity(SuiviColisDto dto);

    SuiviColisDto toDto(SuiviColis suiviColis);
}
