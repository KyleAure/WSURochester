package Admin;

import Movie.Movie;

/**
 * Implement to create a PurpleBox Administrator class.
 * 
 * @author Fantastic4
 * @version 1.0
 */
public interface PurpleBoxAdminInterface {
    /**
     * Add new movie to Inventory.
     * 
     * @require Movie item must be initialized and have all it's data members filled.
     * @ensure Movie is added to ArrayList inventory.
     * 
     * @param movie object of class Movie.
     */ 
    public void addToInventory(Movie movie);
    
    /**
     * Remove movie from Inventory.
     * 
     * @require Movie item must be initialized and must be in the ArrayList inventory.
     * @ensure Movie object is removed from inventory and erased.
     * 
     * @param movie object of class Movie that should be removed from inventory.
     * @return movie this movie object is returned. 
     * @throws Exception Movie object does not exist.
     */
    public Movie removeFromInventory(Movie movie) throws Exception;
    
    /**
     * Set or change price of all movies of a specific type.
     * 
     * User is prompted to change price. 
     * 
     * @ensure Price for all movies of specific type are changed.
     * 
     * @param type boolean True - BluRay. False - DVD.
     * @return double old price.
     * @throws Exception invalid data type.
     */ 
    public double changePrice(boolean type) throws Exception ;
    
    /**
     * Get price of movie based on type.
     * 
     * @require Price has been set for each type of movie.
     * @ensure Current price is returned
     * @param type boolean True - BluRay. False - DVD.
     * @return double current price.
     */
    public double getPrice(boolean type);
    
    /**
     * Add new PromoCode.
     * 
     * @ensure Promotional code is added to list.
     * 
     * @param newCode String new promotional code added.
     */ 
    public void addPromoCode(String newCode);
    
    /**
     * Enable unit for use.
     * 
     * @ensure Purple box unit is enabled.
     * @return boolean True - Unit Enabled. False - Unit Disabled.
     */ 
    public boolean enableUnit();
    
    /**
     * Disable unit for use.
     * 
     * @ensure Purple box unit is disabled.
     * @return boolean True - Unit Enabled. False - Unit Disabled.
     */
    public boolean disableUnit();
    
    /**
     * Return status of unit.  
     * 
     * @ensure Purple box unit has been enabled or disabled.
     * @return boolean True - Unit Enabled.  False - Unit Disabled.
     */
    public boolean isEnabled();
}
