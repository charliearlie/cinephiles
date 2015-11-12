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
    
    
    
}
