package sahelys.gestioncolis.back.exception;

/**
 * Cette exception est levée lorsqu'une règle métier n'est pas respectée.
 *
 * @author PC2
 */

public class ChampNotValidateException extends RuntimeException {
    public ChampNotValidateException(String message) {
        super(message);
    }
}
