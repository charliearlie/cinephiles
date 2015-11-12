/*
 * Work of Charles Waite.
 * Plymouth University.
 */
package connecttodatabase;
import cinephiles.DAOFactoryDatabase;
import cinephiles.data.DAOFactory;
import cinephiles.data.interfaces.IUserList;
import cinephiles.data.model.User;
//import cinephiles.data.model.User;
//import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
/**
 *
 * @author charliearlie
 */
public class ConnectToDatabase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       DAOFactory factory = new DAOFactoryDatabase();
       IUserList userList = factory.getUserList();
       
       ArrayList<User> users;
       
       users = userList.getAllUsers();
       
       for(int i = 0; i < users.size(); i++) {
           System.out.println(users.get(i).getFullName());
       }
       
       String[] alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","x","y","z" };
       Random rnd = new Random();
       String email = alphabet[rnd.nextInt(26)] + alphabet[rnd.nextInt(26)] + alphabet[rnd.nextInt(26)] + alphabet[rnd.nextInt(26)] +
               alphabet[rnd.nextInt(26)] + alphabet[rnd.nextInt(26)] + "@gmail.com";
       Date dob = new Date(1991,07,05);
       Date join = new Date();
       User dave = new User(users.size() + 1, "Dave", "Daniels", "password123", dob, email, join);
       dave.setPassword("password123");
       userList.createUser(dave);
    }
    
}
