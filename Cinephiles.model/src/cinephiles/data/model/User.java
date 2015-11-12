/*
 * Work of Charles Waite.
 * Plymouth University.
 */
package cinephiles.data.model;

import cinephiles.security.PasswordHandler;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charliearlie
 */
public class User {
    private int id = -1;
    private String forename = "UNKNOWN";
    private String surname = "UNKNOWN";
    
    private transient String password = ""; //transient to prevent password being exposed via REST API
    
    private Date dateOfBirth = new Date();
    private String emailAddress = "UNKNOWN";
    private Date joinDate = new Date();

    
    
    public User() {
        
    }
    
    public User(int id, String forename, String surname, String password, Date dateOfBirth, String emailAddress, 
            Date joinDate) {
        this.id = id;
        this.forename = forename;
        this.surname = surname;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
        this.joinDate = joinDate;
    }
    
    public String getFullName() {
        return this.forename + " " + this.surname;
    }

    public int getId() {
        return id;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public Date getJoinDate() {
        return joinDate;
    }
    
     public final boolean checkPassword(String plainTextPassword) {
        //TODO: Add encrypted password checking
        PasswordHandler passwordHandler = new PasswordHandler();
        return passwordHandler.checkPassword(this.password, plainTextPassword);
    }

    public final String getPassword() {
        return this.password;
    }

    public final void setPassword(String password) {
        //TODO: Encrypt password on setting a password
        PasswordHandler passwordHandler = new PasswordHandler();
        try {
            this.password = passwordHandler.hashPasswordWithRandomSalt(password);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
