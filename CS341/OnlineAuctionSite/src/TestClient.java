
import java.util.*;

/**
 * Test client for instances of our user, item, and seller classes.
 * 
 * Test methods to make sure they perform the way that they are specified. 
 * 
 * @author Kyle Aure and Johnny Tran
 * @version 1.0
 */
public class TestClient {
    public static void main(String[] args) {
        
        Scanner console = new Scanner(System.in);
                
        //Create some items to mess with
        Item item1 = new Item("Watch", "Gold", 125.00, 250.00, "Egypt", "10/17/2018");
        Item item2 = new Item("Mug", "Clay", 5.00, 3500.00, "Rochester", "04/17/2018");
        Item item3 = new Item("Laptop", "Mac", 3.00, 6.00, "Canada", "02/01/2018");
        Item item4 = new Item("Mouse", "Logitech", 4.00, 70.00, "Korea", "02/06/2018");
        
        //Create some lists to initialize users and sellers
        ArrayList<Item> recentPurchasesKyle = new ArrayList<>();
        recentPurchasesKyle.add(item1);
        recentPurchasesKyle.add(item2);
        
        ArrayList<Item> recentPurchasesJohnny = new ArrayList<>();
        recentPurchasesJohnny.add(item3);
        
        ArrayList<Item> sellList = new ArrayList<>();
        sellList.add(item4);
        
        //Create some users
        User kyle = new User("KyleAure", "Kyle", "Aure", recentPurchasesKyle);
        User johnny = new User("JDog", "Johnny", "Tran", recentPurchasesJohnny);
        
        //Test Bidding
        System.out.println("Item1 Info Before Bid: " + item1.toString());
        System.out.println("Johnny's bid for item1: ");
        double bid = console.nextDouble();
        
        while(!johnny.bid(item1,bid)){
            System.out.println("Unsuccessful bid try again: ");
            bid = console.nextDouble();
        }
         System.out.println("Successful Bid: " + item1.toString());
        
        //Create some sellers
        Seller kyleSellAccount = new Seller(new ArrayList<>(), kyle);
        Seller sao = new Seller(sellList, new User("Saov", "Sao", "Khin", new ArrayList<>()));
        
        //Move purchased items to sell list
        if(kyleSellAccount.addItemToSell(item1)){
            System.out.println("Item1 Successfully added to Kyle's SellList.\n");
        }
        if(kyleSellAccount.addItemToSell(item4)){
            System.out.println("Should not be successful since Kyle does not own Item4\n");
        }else{
            System.out.println("kyleSellAccount does not own item4\n");
        }

        //Johnny buy item from kyle
        System.out.println("Johnny buy's item 1 from Kyle.\n");
        johnny.purchase(item1, kyleSellAccount);
        
        System.out.println("Johnny's Purchased List" + johnny.getRecentPurchases().toString());
        System.out.println("Kyle's Sell List" + kyleSellAccount.getSellList().toString());

    }
}
