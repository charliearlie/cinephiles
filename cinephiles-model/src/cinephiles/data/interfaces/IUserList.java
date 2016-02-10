/*
 * Work of Charles Waite.
 * Plymouth University.
 */
package cinephiles.data.interfaces;

import cinephiles.data.model.User;
import java.util.ArrayList;

/**
 *
 * @author charliearlie
 */
public interface IUserList {
    
    public ArrayList<User> getAllUsers();
    public User createUser(User user);
    public User getUserById(int id);
    public User getUserByEmail(String email);
    public void deactivateUser(User user);
    public User updateUser(User user);
    public boolean deleteUser(User user);
   
    
    
    
}
