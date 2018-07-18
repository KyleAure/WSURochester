
import java.util.ArrayList;

/**
 * Interface for User Class to define function of methods.
 * 
 * @author Kyle Aure and Johnny Tran
 * @version 1.0
 */
public interface SellerADT{
    
    //###Setters###
    /**
     * Set or change this seller's sell list.
     * 
     * @param sellList ArrayList of item objects.
     */
    public void setSellList(ArrayList<Item> sellList);
    
    //###Getters###
    /**
     * Get this seller's sell list.
     * 
     * @return ArrayList of items.
     */
    public ArrayList<Item> getSellList();
    
    //###Other Methods###
    /**
     * Moves an item from purchase list to sell list
     * 
     * @param item Item object that seller wants to put on sell list.
     * @require Item must be in seller's list of recently purchased items.
     * @ensure (this.getSellList().getSize() &le; 20) {@literal &}
     *       item is removed from recently purchased list {@literal &}
     *       item is added to this.getSellList().
     * @return boolean True - item is found. 
     *         False - sell list is full.
     */
    public boolean addItemToSell(Item item);
    
    /**
     * Removes an item from the to sell list
     * 
     * @param item Item object that needs to be removed from sell list.
     * @require item object must be in the sell list.
     * @ensure only first instance of item is removed from list.
     * @return boolean True - item is found. False - item is not in sell list.
     */
    public boolean removeItemToSell(Item item);
}