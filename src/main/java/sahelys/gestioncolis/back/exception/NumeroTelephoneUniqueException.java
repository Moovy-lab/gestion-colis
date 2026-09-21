package sahelys.gestioncolis.back.exception;

/**
 * Cette exception est levée lorsqu'un numéro de téléphone est déjà utilisé.
 *
 * @author PC2
 */

public class NumeroTelephoneUniqueException extends RuntimeException {
    public NumeroTelephoneUniqueException(String message) {
        super(message);
    }
}
