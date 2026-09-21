package sahelys.gestioncolis.back.mapper;


import org.mapstruct.Mapper;
import sahelys.gestioncolis.back.dto.ExpeditionDto;
import sahelys.gestioncolis.back.model.Expedition;

/**
 * Cette classe convertit les données entre l'entité Expedition et son DTO,
 * en s'appuyant sur les mappers des objets liés (client, destinataire, agence, colis).
 *
 * @author PC2
 */

@Mapper(componentModel = "spring", uses = {ClientMapper.class, DestinataireMapper.class, AgenceMapper.class, ColisMapper.class})
public interface ExpeditionMapper {
    ExpeditionDto toDto(Expedition expedition);
}
