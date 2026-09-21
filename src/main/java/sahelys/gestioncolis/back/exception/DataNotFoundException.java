package sahelys.gestioncolis.back.exception;

/**
 * Cette exception est levée lorsqu'une donnée demandée est introuvable.
 *
 * @author PC2
 */

public class DataNotFoundException extends RuntimeException
{
    public DataNotFoundException(String message)
    {
        super(message);
    }
}
