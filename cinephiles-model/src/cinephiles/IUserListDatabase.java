/*
 * Work of Charles Waite & Lorenzo Koundouris.
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
    
    private final String SQL_GET_ALL_USERS = "SELECT * FROM USER";
    
    private final String SQL_CREATE_USER = "INSERT INTO USER ("
            + "USER_ID, USER_FORENAME, USER_SURNAME, USER_DOB, "
            + "USER_EMAIL, USER_PASSWORD, USER_JOIN_DATE) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    private final String SQL_CREATE_USER_TEST = "INSERT INTO USER ("
            + "USER_FORENAME, USER_SURNAME, USER_DOB, "
            + "USER_EMAIL, USER_PASSWORD, USER_JOIN_DATE) "
            + "VALUES (?, ?, ?, ?, ?, ?)";
    
    private final String SQL_GET_USER_BY_ID = "SELECT * "
            + "FROM USER WHERE USER_ID = ?";
    
    private final String SQL_GET_USER_BY_EMAIL = "SELECT * FROM USER"
            + " WHERE USER_EMAIL = ?";
    
    private final String SQL_UPDATE_USER = "UPDATE USER "
            + "SET USER_FORENAME=?,USER_SURNAME=?,USER_DOB=?,USER_EMAIL=?,"
            + "USER_PASSWORD=? WHERE USER_ID=?";
    
    private final String SQL_DEACTIVATE_USER = "UPDATE USER "
            + "SET USER_ACTIVE=? WHERE USER_ID=?";
    
    private final String SQL_DELETE_USER ="DELETE FROM USER WHERE USER_ID=?";
    
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
                String isActive = resultSet.getString("USER_ACTIVE");
                
                User newUser = new User(id, forename, surname, password, dob, email, joinDate, isActive);
                
                users.add(newUser);
                
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(IUserListDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return users;
    }
    
    @Override
    public User createUser(User user) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_USER_TEST,
                primaryKeys)) {
            preparedStatement.setString(1, user.getForename());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setDate(3,
                    new java.sql.Date(user.getDateOfBirth().getTime()));
            preparedStatement.setString(4, user.getEmailAddress());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setDate(6, 
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

    @Override
    public User getUserById(int id) {
        User user = null;
        
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_USER_BY_ID)){
            preparedStatement.setInt(1, id);
            
            user = getUserUsingStatement(preparedStatement);
        }
        catch(SQLException ex){
            Logger.getLogger(IUserListDatabase.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return user;
    }
    
    @Override
    public User getUserByEmail(String email){
        User user = null;
        
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_USER_BY_EMAIL)){
            preparedStatement.setString(1, email);
            user = getUserUsingStatement(preparedStatement);
        }
        catch(SQLException ex){

            Logger.getLogger(IUserListDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public void deactivateUser(User user) {
        try(PreparedStatement preparedStatement = connection.prepareCall(SQL_DEACTIVATE_USER)) {
            preparedStatement.setString(1, "N");
            preparedStatement.setInt(2, user.getId());
            
            preparedStatement.executeUpdate();
            
        } catch(SQLException ex){

            Logger.getLogger(IUserListDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public User updateUser(User user) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER)) {
            preparedStatement.setString(1, user.getForename());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setDate(3, new java.sql.Date(user.getDateOfBirth().getTime()));
            preparedStatement.setString(4, user.getEmailAddress());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setInt(6, user.getId());
            
            preparedStatement.executeUpdate();
            
            
        } catch(SQLException ex) {
            Logger.getLogger(IUserListDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
    }
    
    /**
     * This method will only be usable by an administrator
     * @param user
     * @return a boolean telling us whether the deleting was successful
     */
    @Override
    public boolean deleteUser(User user) {
        boolean deleted = false;
        
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
            deleted = true;
        } catch (SQLException ex) {
            Logger.getLogger(IUserListDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return deleted;
    }
    
    private User getUserUsingStatement(PreparedStatement preparedStatement) throws SQLException {
        User user = null;
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if(resultSet.next()){
            int id = resultSet.getInt("USER_ID");
            String forename = resultSet.getString("USER_FORENAME");
            String surname = resultSet.getString("USER_SURNAME");
            String password = resultSet.getString("USER_PASSWORD");
            Date dob = resultSet.getDate("USER_DOB");
            String email = resultSet.getString("USER_EMAIL");
            Date joinDate = resultSet.getDate("USER_JOIN_DATE");
            String isActive = resultSet.getString("USER_ACTIVE");
            user = new User(id, forename, surname, password, dob, email, joinDate, isActive);
        }
        
        return user;
    }
    
}
