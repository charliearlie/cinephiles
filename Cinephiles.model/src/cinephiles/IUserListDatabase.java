/*
 * Work of Charles Waite.
 * Plymouth University.
 */
package cinephiles;

import data.interfaces.IUserList;
import data.model.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charliearlie
 */
public class IUserListDatabase implements IUserList {
    
    private final String SQL_GET_ALL_USERS = "SELECT * FROM Users";
    
    private Connection connection;
    
    public IUserListDatabase(Connection connection) {
        this.connection = connection;
    }
    
    private final String primaryKeys[] = {"USER_ID"};
    
    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_USERS);
            
            while(resultSet.next()) {
                int id = resultSet.getInt("USER_ID");
                String forename = resultSet.getString("USER_FORENAME");
                String surname = resultSet.getString("USER_SURNAME");
                String password = resultSet.getString("USER_PASSWORD");
                Date dob = resultSet.getDate("USER_DOB");
                String email = resultSet.getString("USER_EMAIL");
                Date joinDate = resultSet.getDate("USER_JOIN_DATE");
                
                User newUser = new User(id, forename, surname, password, dob, email, joinDate);
                
                users.add(newUser);
                
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(IUserListDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return users;
    }
}
