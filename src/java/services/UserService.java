package services;

import models.User;
import java.util.List;
import dataaccess.UserDB;
import java.util.ArrayList;

/**
 * 
 * 
 * */

public class UserService {
    
    
    /**
     * This method calls the getUser() method in UserDB.
     * @param email - the user's email to identify them in the database.
     * @return user - the user that is requested.
     * @throws Exception - all exceptions that could be had.
     */
    public User get(String email) throws Exception {
        UserDB db = new UserDB();
        User user = db.getUser(email);
        return user;
    }

    /**
     * This method calls the getAll() method from UserDB.
     * @return userList - a list of users from the database.
     * @throws Exception - all exceptions that could be had.
     */
    public List<User> getAll() throws Exception {
        UserDB db = new UserDB();
        ArrayList<User> userList = (ArrayList<User>) db.getActive();
        ArrayList<User> activeUsers = new ArrayList<>();
        
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).isActive()) {
                activeUsers.add(userList.get(i));
            }
        }
        return activeUsers;
    }

    /**
     * @Author David and Ayden With leadership from Ember
     * @param user
     * @return
     * @throws Exception 
     */
    public int update(String email, String fname, String lname, String password) throws Exception {
        UserDB db = new UserDB();
        User user = new User(email, fname, lname, password, null);
        int i = db.update(user);
        return i;
    }

    /**
     * @Author David and Ayden With leadership from Ember
     * 
     * Method does not call delete function because business rule to logically delete
     * @param email the email to delete
     * @return the int from UserDb
     * @throws Exception - all exceptions that could be had.
     */
    public int delete(String email) throws Exception {
        UserDB db = new UserDB();
        User user = get(email);
        user.setActive(false);
        int i = db.update(user);
        return i;
    }

     /**
     * @Author David and Ayden With leadership from Ember
     * @param user the user to update
     * @return the int from UserDb
     * @throws Exception - all exceptions that could be had.
     */
    public int insert(String email, String fname, String lname, String password) throws Exception {
        UserDB db = new UserDB();
        User user = new User(email, fname, lname, password, null);
        int i = db.insert(user);
        return i;
    }

}
