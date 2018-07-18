package User;

import Movie.Movie;
import java.util.ArrayList;

/**
 * Implement to create a PurpleBox Administrator class.
 * 
 * @author Fantastic4
 * @version 1.0
 */
public interface PurpleBoxUserInterface {
    /**
     * Search and return a list of movie objects by attributes of those movies.
     * 
     * Keywords: All Movies, Title, Genre, Blu-Ray, DVD, New Releases
     * Additional Queries: #Title, #Genre
     * 
     * @require User input that matches Keywords.
     * @ensure An ArrayList with all movie objects based on Keywords and Additional Queries.
     * 
     * @param keywords String in form "Keyword#AdditionalQuery"
     * @return ArrayList of Movie Objects
     */
    public ArrayList<Movie> search(String keywords);
    
    /**
     * Add a movie to this session's movie cart.
     * 
     * @require No requirement since exception will be thrown.
     * @ensure Movie param will be added to cart.
     * 
     * @param movie instance of Movie
     * @throws MovieUnavailableException Movie is unavailable  
     */
    public void addMovieToCart(Movie movie) throws MovieUnavailableException;
    
    /**
     * Remove a movie from this session's movie cart.
     * 
     * @require No requirement since exception will be thrown.
     * @ensure Movie param will be removed from cart.
     * 
     * @param movie instance of Movie
     * @throws MovieUnavailableException Movie is not in cart
     */
    public void removeMovieFromCart(Movie movie) throws MovieUnavailableException;
    
    /**
     * Returns current list of movies in cart.
     * 
     * @return ArrayList elements of type movie
     * @throws EmptyCartException if cart is empty
     */
    public ArrayList<Movie> getCart() throws EmptyCartException;
    
    /**
     * @require No requirement since exception will be thrown.
     * @ensure Cart will be empty.
     * 
     * @throws EmptyCartException Cart is empty.
     */
    public void removeAllFromCart() throws EmptyCartException;
   
    
    /**
     * @require No requirement since exception will be thrown.
     * @ensure All movies in cart are checked out to this user.
     * 
     * @throws EmptyCartException Cart is empty
     */
    public void checkOutMovies() throws EmptyCartException;
    
    /**
     * @require No requirement admin will handle cases where
     *          A movie not in inventory is returned.
     * @ensure All movies are returned and quantity is incremented.
     * 
     */
    public void returnMovies();
    
    /** 
     * @require User input a promotional code.
     * @ensure If promotional code is valid it will be applied.
     * 
     * @param promoCode String promotional code.
     * @throws InvalidPromoCodeException promotional code is invalid
     */
    public void applyPromoCode(String promoCode) throws InvalidPromoCodeException;
    
}
