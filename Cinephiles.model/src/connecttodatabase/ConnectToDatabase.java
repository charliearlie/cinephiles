/*
 * Work of Charles Waite.
 * Plymouth University.
 */
package connecttodatabase;
import cinephiles.DAOFactoryDatabase;
import cinephiles.data.DAOFactory;
import data.interfaces.IUserList;
import data.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
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
    }
    
}
