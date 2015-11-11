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
public abstract class DAOFactory implements IDAOFactory{
    protected IUserList userList;
    
    @Override
    public IUserList getUserList() {
        return userList;
    }
    
}
