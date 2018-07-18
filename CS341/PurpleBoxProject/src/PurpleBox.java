
import Admin.PurpleBoxAdminInterface;
import Movie.Movie;
import User.*;
import java.text.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 * Implements all abstract methods for the Admin and User interfaces.
 *
 * @author Kyle Aure and Johnny Tran
 * @version 1.0
 */
public class PurpleBox implements PurpleBoxAdminInterface, PurpleBoxUserInterface {

    private final ArrayList<Movie> inventory;
    private final ArrayList<String> promoList;
    private final ArrayList<Movie> cart;
    private final ArrayList<Movie> checkedOut;
    private double dvdPrice;
    private double bluPrice;
    private boolean userEnabled;

    //Default constructor
    public PurpleBox() {
        this.inventory = new ArrayList<>();
        this.cart = new ArrayList<>();
        this.promoList = new ArrayList<>();
        this.checkedOut = new ArrayList<>();
        this.dvdPrice = 0.0;
        this.bluPrice = 0.0;
        this.userEnabled = false; 
    }

    //Overloaded Constrctor used to ensure userGUI can be enabled.
    public PurpleBox(boolean enabled) {
        this.inventory = new ArrayList<>();
        this.cart = new ArrayList<>();
        this.promoList = new ArrayList<>();
        this.checkedOut = new ArrayList<>();
        this.dvdPrice = 0.0;
        this.bluPrice = 0.0;
        this.userEnabled = enabled;
    }

    @Override
    public void addToInventory(Movie movie) {
        //COMPLETED
        boolean result = false; //assume search does not find anything.

        //compare every movie in inventory against movie we are trying to add. 
        for (Movie m : inventory) {
            if (m.getName().compareToIgnoreCase(movie.getName()) == 0) { //Movie already in inventory
                try {
                    m.setQuantity(movie.getQuantity() + m.getQuantity()); //Exception: increment quanity.
                    result = true; //notify remaining code that we found a match.
                    break; //end for loop.
                } catch (Exception e) {
                    System.out.println("Invalid data type or quantity < 0.");
                }
            }//end if
        }//end for

        if (!result) { //if we did not find an existing movie.
            inventory.add(movie); //add it to inventory.
            try {
                //set the quanity of the movie we just added to inventory to 1. 
                inventory.get(inventory.size() - 1).setQuantity(movie.getQuantity());
            } catch (Exception e) {
                System.out.println("Invalid data type or quantity < 0.");
            }
        }
    }

    @Override
    public Movie removeFromInventory(Movie movie) throws Exception {
        //COMPLETED
        boolean result = false; //Assume search does not find anything.
        Movie temp = null; //will be returned once removed. 

        for (Movie m : inventory) {
            if (m.getName().compareToIgnoreCase(movie.getName()) == 0) { //Movie in inventory
                temp = m; //catch the movie before it is removed. 
                inventory.remove(m); 
                result = true; //notify remaining code that we found a match. 
                break; //break out of for loop.
            }
        }

        if (result) {
            return temp; //return old movie.
        } else {
            throw new Exception("Movie not in inventory."); //notify user it was not found. 
        }
    }

    @Override
    public double changePrice(boolean type) throws Exception {
        String movieType;
        
        if(type){
            movieType = "Blu-Rays";
        }else{
            movieType = "DVDs";
        }    
        
        double answer = Double.parseDouble(JOptionPane.showInputDialog("Please enter new price for " + movieType));
        double oldPrice;
        
        if (type) {
            oldPrice = this.bluPrice;
            this.bluPrice = answer;
        } else {
            oldPrice = this.dvdPrice;
            this.dvdPrice = answer;
        }

        return oldPrice;
    }

    @Override
    public double getPrice(boolean type) {
        //COMPLETED
        if (type) {
            return bluPrice;
        } else {
            return dvdPrice;
        }
    }

    @Override
    public void addPromoCode(String newCode) {
        boolean result = false; //assume we do not find it
        
        for(String s : promoList){
            if(s.compareToIgnoreCase(newCode) == 0){
                result = true;
                break;
            }
        }
        
        if(!result){
            promoList.add(newCode);
        }
        
    }

    @Override
    public boolean enableUnit() {
        //COMPLETED
        this.userEnabled = true;
        return userEnabled;
    }

    @Override
    public boolean disableUnit() {
        //COMPLETED
        this.userEnabled = false;
        return userEnabled;
    }

    @Override
    public boolean isEnabled() {
        //COMPLETED
        return userEnabled;
    }

    @Override
    public ArrayList<Movie> search(String keywords) {
        String[] query = keywords.split("#"); //query[0] - Attribute & query[1] - user defined string
        ArrayList<Movie> result = new ArrayList<>();
        
        if(query[0].compareTo("Title") == 0 && query.length == 1){
            query[0] = "All Movies";
        }
        if(query[0].compareTo("Genre") == 0 && query.length == 1){
            query[0] = "All Movies";
        }

        switch (query[0]) {
            case "All Movies": //return all
                for (Movie m : inventory){
                    result.add(m);
                }
                break;
            case "Title": //return those matching title. 
                for (Movie m : inventory) {
                    if (m.getName().compareToIgnoreCase(query[1].trim()) == 0) {
                        result.add(m);
                    }
                }
                break;
            case "Genre":
                for (Movie m : inventory) {
                    if (m.getGenre().compareToIgnoreCase(query[1].trim()) == 0) {
                        result.add(m);
                    }
                }
                break;
            case "Blu-Ray":
                for (Movie m : inventory) {
                    if (m.getType()) {
                        result.add(m);
                    }
                }
                break;
            case "DVD":
                for (Movie m : inventory) {
                    if (!m.getType()) {
                        result.add(m);
                    }
                }
                break;
            case "New Releases":
                //DateFormat to change String release date into Date object. 
                DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
                Date today = Calendar.getInstance().getTime(); //today's date.

                for (Movie m : inventory) {
                    try {
                        Date releaseDate = format.parse(m.getReleaseDate());
                        long diffInTime = today.getTime() - releaseDate.getTime();

                        if (diffInTime > 0 && diffInTime < 600000000) { //6*10^8 ms approx 1 week
                            result.add(m);
                        }
                    } catch (ParseException ex) {
                        System.out.println("Unsupported date format.");
                    }
                }
                break;
            default:
                break;

        }//end switch

        return result;

    }

    @Override
    public void addMovieToCart(Movie movie) throws MovieUnavailableException {
        boolean result = false; //assume search does not find anything.

        //compare every movie in inventory against movie we are trying to add. 
        for (Movie m : inventory) {
            if (m.getName().compareToIgnoreCase(movie.getName()) == 0) {
                try {
                    if (!m.getAvailability()) { //break out of try if movie unavaiable
                        throw new MovieUnavailableException("Movie unavailable.");
                    }
                    
                    m.setQuantity(m.getQuantity() - 1); //will throw exception if < 0
                    result = true; //we decremented the quantity of a movie that already exists.

                    cart.add(m); //finally add movie to cart.
                    break; //end for loop.
                } catch (MovieUnavailableException e) {
                    System.out.println("Movie is unavailable.");
                } catch (Exception ex) {
                    System.out.println("No more movie's in inventory");
                }
            }//end if
        }//end for

        if (!result) { //if we did not find movie in inventory
            throw new MovieUnavailableException("Move out of stock or unavailable.");
        }
    }

    @Override
    public void removeMovieFromCart(Movie movie) throws MovieUnavailableException {
        boolean result = false; //assume we do not find movie

        for (Movie m : cart) {
            if (m.getName().compareToIgnoreCase(movie.getName()) == 0) {
                try {
                    m.setQuantity(m.getQuantity()+1);
                } catch (Exception ex) {
                    System.out.println("Invalid data type");
                }
                cart.remove(m);
                result = true;
                break; //break out of for loop.
            }
        }

        if (!result) {
            throw new MovieUnavailableException("Movie not in inventory.");
        }

    }

    @Override
    public ArrayList<Movie> getCart() throws EmptyCartException {
        //COMPLETED
        if(cart.size() == 0){
            throw new EmptyCartException("Cart is empty.");
        }else{
            return cart;
        }
    }

    @Override
    public void removeAllFromCart() throws EmptyCartException {
        //COMPLETED
        if (cart.isEmpty()) {
            throw new EmptyCartException("Cart is Empty.");
        } else {
            for(Movie m : cart){
                try {
                    m.setQuantity(m.getQuantity() + 1);
                } catch (Exception ex) {
                    System.out.println("Incorrect data type");
                }
            }
            cart.removeAll(cart);
        }
    }

    @Override
    public void checkOutMovies() throws EmptyCartException {
        
        if (cart.isEmpty()) {
            throw new EmptyCartException("Cart is Empty.");
        } else {
            for (Movie m : cart) {
                checkedOut.add(m);
            }
            cart.removeAll(cart);
        }
    }

    @Override
    public void returnMovies() {
        if (checkedOut.isEmpty()) {
            return; //stop
        } else {
            for (Movie m : checkedOut) {
                try {
                    m.setQuantity(m.getQuantity() + 1);
                } catch (Exception ex) {
                    System.out.println("Invalid Data Type");
                }
            }
            checkedOut.removeAll(checkedOut);
        }
    }

    @Override
    public void applyPromoCode(String promoCode) throws InvalidPromoCodeException {
        boolean found = false; //assume promoCode not found.
        for (String p : promoList) {
            if (p.compareToIgnoreCase(promoCode) == 0) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new InvalidPromoCodeException("Code Not Found.");
        }
    }
}
