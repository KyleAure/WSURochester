
import java.util.*;

/**
 * This class implements SellerADT and extends User class.
 * 
 * In addition this class defines required fields 
 * and defines methods for adding/removing items from the sell list.
 * 
 * @author Kyle Aure and Johnny Tran
 * @version 1.0
 */
public class Seller extends User implements SellerADT {

    //Date Members
    private ArrayList<Item> sellList;

    //constructor
    /**
     * Default constructor for a seller object.
     * 
     * This constructor does not define any of the data fields.
     * This constructor should be used to initialize a seller object.
     */
    public Seller() {
    }
    
    /**
     * Constructor that accepts a sell list and an existing user.
     * 
     * This constructor should be used to turn an existing user into a seller.
     * 
     * @param sellList ArrayList of items to sell.
     * @param user instance of user class.
     */
    public Seller(ArrayList<Item> sellList, User user){
        super(user.getUsername(), user.getFirstName(), user.getLastName(), user.getRecentPurchases());
        this.sellList = sellList;
    }
    
    /**
     * Constructor that accepts a sell list and all data members of a user.
     * 
     * This constructor should be used to create a new seller.
     * 
     * @param sellList ArrayList of this seller's available items to sell.
     * @param username String this seller's defined username.
     * @param firstName String this seller's first name.
     * @param lastName String this seller's last name.
     * @param recentPurchases ArrayList of this seller's recentPurchases
     */
    public Seller(ArrayList<Item> sellList, String username, String firstName, String lastName, ArrayList<Item> recentPurchases) {
        super(username, firstName, lastName, recentPurchases);
        this.sellList = sellList;
    }

    //setters and getters 
    @Override
    public ArrayList<Item> getSellList() {
        return sellList;
    }

    @Override
    public void setSellList(ArrayList<Item> sellList) {
        this.sellList = sellList;
    }

    @Override
    public boolean addItemToSell(Item item) {
        if (this.sellList.size() < 20) { //check size of sell list
            for (int i=0; i<this.recentPurchases.size(); i++) { //iterate through RecentPurchases
                if (this.recentPurchases.get(i).compareTo(item) == 0) {
                    this.sellList.add(this.recentPurchases.remove(i));
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean removeItemToSell(Item item) {
        return this.sellList.remove(item);
    }
}
