
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Client runs by default to ask user which system they want to engage. Client
 * also initializes an instance of PurpleBox and passes it to each GUI.
 *
 * @author Kyle Aure and Johnny Tran
 * @version 1.0
 */
public class Client {

    public static void main(String[] args) {
        //initialize an instance of PurpleBox
        PurpleBox session = new PurpleBox(true); //TODO change back to default constructor > AdminGUI

        //initialize instances of each GUI
        UserGUI userGUI = new UserGUI();
        userGUI.setSession(session);

        //TODO make sure this will work with other teams GUI
        //AdminGUI adminGUI = new AdminGUI();
        //adminGUI.setSession(session);
        //Current session status
        boolean sessionStatus = true;

        while (sessionStatus) {
            int answer = -1; //assume we need to wait

            //if we are currently looking at another GUI keep answer at -1
            //else display options to user (see method).
            if (userGUI.isVisible()) { //|| AdminGUI.isVisible()
                answer = -1;
            } else {
                answer = displayOptions();
            }

            switch (answer) {
                case -1: //try again
                    break;
                case 0: //set admin GUI to visible. An instance was already created.
                    //TODO add admin controls
                    JOptionPane.showMessageDialog(null, "Not Supported Yet.");
                    break;
                case 1: //set user GUI to visible. An instance was already created.
                    //TODO if we ever combine programs then this will be necessary.
                    //if (!session.isEnabled()) {
                    //    JOptionPane.showMessageDialog(null, "User session is not enabled.");
                    //    answer = -1;
                    //} else {
                        userGUI.setVisible(true);
                    //}
                    break;
                case 2: //exit - break out of while loop.
                    sessionStatus = false;
                    break;
                default: //try again
                    break;
            }//end switch

            try { //wait a bit to give other threads a chance to catch up.
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }

        }//end loop

        System.out.println("Thank you for using this program.");
        System.exit(0);

    }//end main

    public static int displayOptions() {
        Object[] options = {"Admin", "User", "Exit"};
        String message = "Please choose which inferface you would like to use.";
        String title = "Program Options";
        int result;

        result = JOptionPane.showOptionDialog(null, //Parent Component
                message, //Message
                title, //Title
                JOptionPane.DEFAULT_OPTION, //Options Type
                JOptionPane.INFORMATION_MESSAGE, //Message Type
                null, //Icon 
                options, //Option List
                options[2]); //Defult Option

        return result;
    }//end DisplayOptions
}//end class

