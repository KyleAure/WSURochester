package Movie;

/**
 * Implement to create a movie class that will allow instances to be created.
 * 
 * @author Fantastic4
 * @version 1.0
 */
public interface movieADT{
    
    /**
     * Get name of this movie.
     * 
     * @require Movie object is initialized and has its name variable filled.
     * @ensure Movie name is returned.
     *
     * @return String this movie's name. 
     */ 
    public String getName();
    
    /**
     * Get type of this movie.
     * 
     * @require Movie object is initialized and has its type variable filled.
     * @ensure Movie type is returned.
     * 
     * @return Boolean True = BlueRay. False = DVD.
     */ 
    public Boolean getType();
    
    /**
     * Get genre of this movie.
     * 
     * @require Movie object is initialized and has its genre variable filled.
     * @ensure Movie genre is returned.
     * 
     * @return String Movie genre.
     */ 
    public String getGenre();
    
    /**
     * Get release date of this movie.
     * 
     * @require Movie object is initialized and has its release date variable filled.
     * @ensure Movie release date is returned.
     * 
     * @return String Movie release date.
     */
    public String getReleaseDate();
    
    /**
     * Get availability of this movie.
     * 
     * @require Movie object is initialized and has its availability variable filled.
     * @ensure Movie availability is returned.
     * 
     * @return Boolean True = Available, False = Unavailable.
     */ 
    public Boolean getAvailability();
    
    /**
     * Set or change this movie's availability.
     * 
     * @require Movie object is initialized.
     * @ensure Movie availability variable is set to a new value.
     * 
     * @param truth boolean True = Available, False = Unavailable 
     * @throws Exception invalid data type.
     */
    public void setAvailability(boolean truth) throws Exception;
    
    /**
     * Get number of copies available for this movie.
     * 
     * @require Movie object is initialized and has its quantity variable filled.
     * @ensure Movie quantity is returned.
     * 
     * @return int Movie quantity.
     */
    public int getQuantity();
    
    /**
     * Set or change number of copies available for this movie.
     * 
     * @require Movie object is initialized.
     * @ensure Movie quantity variable is set to a new value.
     * 
     * @param quantity int number of copies.
     * @throws Exception invalid data type.
     */
    public void setQuantity(int quantity) throws Exception;
    
    /**
     * Converts movie to row to be displayed as a JTable Model in GUI.
     * 
     * 0(SearchResults) - Name, Genre, ReleaseDate, Type, Price, Quantity
     * 1(ShoppingCart) - Name, Genre, ReleaseDate, Type, Price
     * 2(Orders) - Name, Genre, ReleaseDate, Type
     *
     * @param i Choice of how you want results formatted
     * @return row in form of an array of objects.
     */
    public Object[] toRow(int i);
}
