/*
 * Work of Charles Waite.
 * Plymouth University.
 */
package cinephiles.data;

import cinephiles.data.interfaces.IMediaList;
import cinephiles.data.interfaces.IUserList;

/**
 *
 * @author charliearlie
 */
public interface IDAOFactory {
    public IUserList getUserList();
    public IMediaList getMediaList();
    
    public boolean getConnectionStatus();
    
}
