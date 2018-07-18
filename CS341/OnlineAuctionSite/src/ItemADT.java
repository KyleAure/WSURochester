/**
 * Interface for Item Class to define function of methods.
 * 
 * @author Kyle Aure and Johnny Tran
 * @version 1.0
 */
public interface ItemADT{
    //###Getters###
    /**
     * Get this item's name.
     * 
     * @return String name of this item.
     */
    public String getName();
    
    /**
     * Get this item's description.
     * 
     * @return String description of this item.
     */
    public String getDescription();
    
    /**
     * Get this item's highest bid.
     * 
     * @return Double highest bid on this item.
     */
    public double getHighBid();
    
    /**
     * Get this item's buy now price.
     * 
     * @return Double buy now price for this item.
     */
    public double getBuyNowPrice();
    
    /**
     * Get this item's location.
     * 
     * @return String location of this item.
     */
    public String getLocation();
    
    /**
     * Get this item's date bidding is complete.
     * 
     * @return String date bidding complete for this item.
     */
    public String getDateBiddingComplete();
    
    //###Setters###
    /**
     * Set or change this item's name.
     * 
     * @param name String name of this item.
     */
    public void setName(String name);

    /**
     * Set or change this item's description.
     * 
     * @param description String description of this item.
     */
    public void setDescription(String description);

    /**
     * Set or change this item's highest bid
     * 
     * @param highBid double highest bid.
     */
    public void setHighBid(double highBid);

    /**
     * Set or change this item's buy now price.
     * 
     * @param buyNowPrice double buy now price.
     */
    public void setBuyNowPrice(double buyNowPrice);

    /**
     * Set or change this item's location.
     * 
     * @param location String location of object.
     */
    public void setLocation(String location);

    /**
     * Set or change this item's bidding date completed.
     * 
     * @param dateBiddingComplete String date bidding complete
     */
    public void setDateBiddingComplete(String dateBiddingComplete);

}