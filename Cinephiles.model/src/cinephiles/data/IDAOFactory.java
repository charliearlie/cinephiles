/*
 * Work of Charles Waite.
 * Plymouth University.
 */
package cinephiles.data;

import cinephiles.data.interfaces.IUserList;

/**
 *
 * @author charliearlie
 */
public interface IDAOFactory {
    public IUserList getUserList();
    
    public boolean getConnectionStatus();
    
}
