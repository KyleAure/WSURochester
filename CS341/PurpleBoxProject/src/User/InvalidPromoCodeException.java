package User;

/**
 * Thrown when an Invalid Promotional Code is entered.
 *
 * @author Fantastic4
 * @version 1.0
 */
public class InvalidPromoCodeException extends Exception{

    public InvalidPromoCodeException(String message) {
        super(message);
    }
}
