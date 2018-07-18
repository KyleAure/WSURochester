package User;

/**
 * Thrown when a movie is not found in a list, unavailable due to 
 * zero quantity, or made unavailable by an administrator.
 * 
 * @author Fantastic4
 * @version 1.0
 */
public class MovieUnavailableException extends Exception {
    public MovieUnavailableException(String message){
        super(message);
    }
}
