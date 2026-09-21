package sahelys.gestioncolis.back.error;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Cette classe représente le corps de réponse renvoyé lorsqu'une erreur survient.
 *
 * @author PC2
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime errorDate;
    private HttpStatus status;
    private String message; // message d’erreur
    private String debugMessage; //message utile pour comprendre l’erreur par le développeur
}
