package sahelys.gestioncolis.back.error;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sahelys.gestioncolis.back.exception.ChampNotValidateException;
import sahelys.gestioncolis.back.exception.DataNotFoundException;
import sahelys.gestioncolis.back.exception.NumeroTelephoneUniqueException;

import java.time.LocalDateTime;

/**
 * Cette classe intercepte les exceptions levées par les contrôleurs
 * et les transforme en réponses HTTP exploitables par le client.
 *
 * @author PC2
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        StringBuilder messageErreur = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error
                -> messageErreur.append(error.getDefaultMessage()).append("\n")
        );

        return ResponseEntity.badRequest().body(
                ErrorResponse.builder()
                        .message(messageErreur.toString())
                        .debugMessage(ex.getLocalizedMessage())
                        .errorDate(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> handleDataNotFound(DataNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ErrorResponse.builder()
                        .message(ex.getMessage())
                        .debugMessage(ex.getLocalizedMessage())
                        .errorDate(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND)
                        .build());
    }

    @ExceptionHandler(NumeroTelephoneUniqueException.class)
    public ResponseEntity<Object> handleNumeroTelephoneUnique(NumeroTelephoneUniqueException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ErrorResponse.builder()
                        .message(ex.getMessage())
                        .debugMessage(ex.getLocalizedMessage())
                        .errorDate(LocalDateTime.now())
                        .status(HttpStatus.CONFLICT)
                        .build());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ErrorResponse.builder()
                        .message("Une contrainte d'unicité a été violée (numéro de téléphone ou numéro de suivi déjà utilisé)")
                        .debugMessage(ex.getMostSpecificCause().getMessage())
                        .errorDate(LocalDateTime.now())
                        .status(HttpStatus.CONFLICT)
                        .build());
    }

    @ExceptionHandler(ChampNotValidateException.class)
    public ResponseEntity<Object> handleChampNotValidate(ChampNotValidateException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorResponse.builder()
                        .message(ex.getMessage())
                        .debugMessage(ex.getLocalizedMessage())
                        .errorDate(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());
    }
}
