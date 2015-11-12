/*
 * Work of Charles Waite.
 * Plymouth University.
 */
package cinephiles;

import cinephiles.data.interfaces.IUserList;
import cinephiles.data.model.User;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    
    private final String SQL_CREATE_USER = "INSERT INTO Users ("
            + "USER_ID, USER_FORENAME, USER_SURNAME, USER_DOB, "
            + "USER_EMAIL, USER_PASSWORD, USER_JOIN_DATE) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    
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
    
    @Override
    public User createUser(User user) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_USER,
                primaryKeys)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getForename());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setDate(4,
                    new java.sql.Date(user.getDateOfBirth().getTime()));
            preparedStatement.setString(5, user.getEmailAddress());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setDate(7, 
                    new java.sql.Date(user.getJoinDate().getTime()));
            
            
            
            preparedStatement.executeUpdate();

//            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                user.setId(generatedKeys.getInt(1));
//            }
        } catch (SQLException ex) {
            Logger.getLogger(IUserListDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
}
