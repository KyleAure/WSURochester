/**
 * This class implements ItemADT and Comparable. 
 * 
 * In addition this class defines required fields 
 * and defines methods for getting/setting this class' fields.
 * 
 * @author Kyle Aure and Johnny Tran
 * @version 1.0
 */
public class Item implements ItemADT, Comparable<Item>{
    //data members
    private String name;
    private String description;
    private double highBid;
    private double buyNowPrice;
    private String location;
    private String dateBiddingComplete;
  
    //constructors
    /**
     * Default constructor used for a seller object
     */
    public Item() {
    }
    
    /**
     * Constructor that accepts all data members for an item
     * 
     * @param name this item's name
     * @param description this item's description
     * @param highBid this item's highest bid
     * @param buyNowPrice this item's buy now price
     * @param location this item's location
     * @param dateBiddingComplete this item's date bidding is complete
     */
    public Item(String name, String description, double highBid, double buyNowPrice, String location, String dateBiddingComplete) {
        this.name = name;
        this.description = description;
        this.highBid = highBid;
        this.buyNowPrice = buyNowPrice;
        this.location = location;
        this.dateBiddingComplete = dateBiddingComplete;
    }
    
    //setters and getters
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public double getHighBid() {
        return highBid;
    }

    @Override
    public void setHighBid(double highBid) {
        this.highBid = highBid;
    }

    @Override
    public double getBuyNowPrice() {
        return buyNowPrice;
    }

    @Override
    public void setBuyNowPrice(double buyNowPrice) {
        this.buyNowPrice = buyNowPrice;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String getDateBiddingComplete() {
        return dateBiddingComplete;
    }

    @Override
    public void setDateBiddingComplete(String dateBiddingComplete) {
        this.dateBiddingComplete = dateBiddingComplete;
    }

    @Override
    public String toString() {
        return String.format("\nItem Name: %s\n"
                + "Item Description: %s\n"
                + "Highest Bid: $%-8.2f\n"
                + "Buy Now Price: $%-8.2f\n"
                + "Location: %s\n"
                + "Date Bidding Complete: %s\n", name, description, highBid, buyNowPrice, location, dateBiddingComplete);
    }

    @Override
    public int compareTo(Item o) {
        return this.getName().compareTo(o.getName());
    }
}