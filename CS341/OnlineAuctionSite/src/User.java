
import java.util.*;

/**
 * This class implements UserADT, defines required fields, and defines methods
 * to bid/purchase items.
 *
 * @author Kyle Aure and Johnny Tran
 * @version 1.0
 */
public class User implements UserADT {

    //data fields
    private String username;
    private String firstName;
    private String lastName;
    protected ArrayList<Item> recentPurchases;

    //constructors
    /**
     * Default constructor for a user object.
     *
     * This constructor does not define any of the data fields.
     */
    public User() {
    }

    /**
     * Constructor that accepts all data fields
     *
     * @param username String this user's defined username.
     * @param firstName String this user's first name.
     * @param lastName String this user's last name.
     * @param recentPurchases Queue this user's 10 most recently purchased
     * items.
     */
    public User(String username, String firstName, String lastName, ArrayList<Item> recentPurchases) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.recentPurchases = recentPurchases;
    }

    //setters and getters
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public ArrayList<Item> getRecentPurchases() {
        return recentPurchases;
    }

    @Override
    public void setRecentPurchases(ArrayList<Item> recentPurchases) {
        this.recentPurchases = recentPurchases;
    }

    @Override
    public String toString() {
        return String.format("%s, %s %s", username, firstName, lastName)
                + "\n" + recentPurchases.toString();
    }

    @Override
    public boolean bid(Item item, double bid) {
        if (bid >= item.getBuyNowPrice()) {//test if user could have purchased item 
            return false; //return false and do not finish the rest of this method.
        }
        if (bid > item.getHighBid()) { //test if bid is more current highest bid.
            item.setHighBid(bid);
            return true;
        }
        return false;
    }

    @Override
    public void purchase(Item item, Seller seller) {
        if (seller.getSellList().contains(item)) {//check if item on Seller's sell list.
            if (recentPurchases.size() == 10) { //test if recentPurchase is full. 
                recentPurchases.remove(0); //if so remove the oldest item (at index 0). 
            }
            recentPurchases.add(item); //add new item to this user's recentPurchase list.
            seller.removeItemToSell(item);
        }
    }
}
