
import java.util.*;

/**
 * Interface for User Class to define function of methods
 * 
 * @author Kyle Aure and Johnny Tran
 * @version 1.0
 */
public interface UserADT{
    //###Setters###
    /**
     * Set or change this user's username.
     * 
     * @param username string to represent this user's username.
     */
    public void setUsername(String username);
    
    /**
     * Set or change this user's first name.
     * 
     * @param firstName string to represent this user's first name.
     */
    public void setFirstName(String firstName);
    
    /**
     * Set or change this user's last name.
     * 
     * @param lastName string to represent this user's last name.
     */
    public void setLastName(String lastName);
    
    /**
     * Set this user's recent purchases queue.
     * 
     * @param recentPurchases queue of recent purchases.
     */
    public void setRecentPurchases(ArrayList<Item> recentPurchases);
    
    //###Getters###
    /**
     * Get this user's username.
     * 
     * @return String username.
     */
    public String getUsername();

    /**
     * Get this user's first name.
     * 
     * @return String username.
     */
    public String getFirstName();

    /**
     * Get this user's last name.
     * 
     * @return String last name.
     */
    public String getLastName();
    
    /**
     * Get this user's recent purchase queue.
     * 
     * @return Queue of items.
     */
    public ArrayList<Item> getRecentPurchases();
    
    //###Other Methods###    
    /**
     * Allows this user to bid on an item.
     * 
     * This method should me used to update the highest bid on an item by
     * having this user bid on the specific item.
     * 
     * @param item object of class Item to be bid on.
     * @param bid double value this user wants to bid.
     * @require Item object must be initialized with a starting bid 
     * @ensure bid &gt; item.getHighestBid() {@literal &&}
     *         bid &lt; item.getBuyNowPrice() {@literal &&}
     *         item.setHighestBid(bid) 
     * @return boolean True - bid was successful. 
     *         False - bid was unsuccessful because it was less than the current
     *         highest bid or it was more than the buy-now price.
     */
    public boolean bid(Item item, double bid);
    
    /**
     * Allows this user to purchase an item using the buy-now price.
     * 
     * This method should be used to purchase an item at a buy-now price.
     * The item will automatically be put in this users recently purchased list.
     * 
     * @param item object of class Item to be purchased.
     * @param seller Seller of item.
     * @require item object must be initialized and be on sale.
     * @ensure item is added to this users recent purchases
     *         and remove item from Seller's sell-list.
     */
    public void purchase(Item item, Seller seller);
    
}