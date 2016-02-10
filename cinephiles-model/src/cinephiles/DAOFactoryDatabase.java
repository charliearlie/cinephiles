/*
 * Work of Charles Waite.
 * Plymouth University.
 */
package cinephiles;

import cinephiles.data.DAOFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author charliearlie
 */
public class DAOFactoryDatabase extends DAOFactory{
    private Connection connection;

    public DAOFactoryDatabase() throws RuntimeException {
        
        try {
            // Check if database driver is loaded
            Class.forName("com.mysql.jdbc.Driver");

            // Connect to the database
            connection = DriverManager.getConnection(
                    //"jdbc:mysql://rdsdatabase.cspj4gwk28vo.us-west-2.rds.amazonaws.com:3306/RDSdatabase",
                    "jdbc:mysql://restore.cspj4gwk28vo.us-west-2.rds.amazonaws.com:3306/RDSdatabase",
                    "root", "password1");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(
                    "Could not find MySQL jdbc driver"
                    + "\nCheck if it is added as a library");
        } catch (SQLException ex) {
            throw new RuntimeException(
                    "Failed to establish database connection"
                    + "\n" + ex.getMessage());
        }

        if (connection != null) {
            // Create DAOObjects
            userList = new IUserListDatabase(connection);
            mediaList = new IMediaListDatabase(connection);
        } else {
            throw new RuntimeException(
                    "Failed to instantiate class (unknown reason)"
                    + this.getClass().getName()
                    + ": couldn't connect to the database!");
        }
    }
    
    @Override
    public boolean getConnectionStatus() {
        return (connection != null);
    }
}
