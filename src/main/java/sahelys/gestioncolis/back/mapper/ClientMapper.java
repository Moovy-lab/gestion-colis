package sahelys.gestioncolis.back.mapper;


import org.mapstruct.Mapper;
import sahelys.gestioncolis.back.dto.ClientDto;
import sahelys.gestioncolis.back.model.Client;

/**
 * Cette classe convertit les données entre l'entité Client et son DTO.
 *
 * @author PC2
 */

@Mapper(componentModel = "spring")
public interface ClientMapper {
    public Client toEntity(ClientDto dto);

    public ClientDto toDto(Client client);

}
