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
public abstract class DAOFactory implements IDAOFactory{
    protected IUserList userList;
    protected IMediaList mediaList;
    
    @Override
    public IUserList getUserList() {
        return userList;
    }
    
    @Override
    public IMediaList getMediaList() {
        return mediaList;
    }
    
}
