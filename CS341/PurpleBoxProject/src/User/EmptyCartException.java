package User;

/**
 * Thrown when trying to empty cart when there are no items in a cart.
 * 
 * @author Fantastic4
 * @version 1.0
 */
public class EmptyCartException extends Exception{
    public EmptyCartException(String message){
        super(message);
    }
}
