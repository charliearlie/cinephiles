/*
 * Work of Charles Waite.
 * Plymouth University.
 */
package connecttodatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charliearlie
 */
public class UserList {
    
    private final String SQL_GET_ALL_USERS = "SELECT * FROM Users";
    
    private Connection connection;

    public UserList(Connection connection) {
        this.connection = connection;
    }

    UserList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    public ArrayList<String> getAllMembers() {
        ArrayList<String> userList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_USERS);

            while (resultSet.next()) {
                String forename = resultSet.getString("USER_FORENAME");
                String surname = resultSet.getString("USER_SURNAME");
                String email = resultSet.getString("USER_EMAIL");

                userList.add(forename + " " + surname + " email: " + email);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MovieList.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userList;
    }
}


