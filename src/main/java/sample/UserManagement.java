package sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
instructions: Write a user management class that can add a user, authenticate, list users.
(Don't worry about persistence)

normally, I would ask far more questions regarding requirements before coding anything,
but as this is a 'see what you come up with' scenario, here goes.
*/


public class UserManagement {


    private static HashMap<String, User> registeredUsers;


    //no matter how many instances of UserManagement are created I want only a single instance of my "datastore object"
    static {
        registeredUsers = new HashMap<String, User>();
    }



    public boolean addUser(String username, String password){
        if(User.isUserNameValid(username) && User.isPasswordValid(password)) {
            User newUser = new User(username, password);
            if( addUser(newUser)){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }


    public boolean authenticateUser(String username, String password){
        if(registeredUsers.containsKey(username.trim())){
            password = PasswordHelper.getSecurePassword(password);
            String storedHash = registeredUsers.get(username).getPassword();
            if(storedHash.equals(password)){
                return true;
            }
        }
        return false;
    }


    public Set<String> listUsers(){
        Set<String> listOfUsers =  registeredUsers.keySet();
        return listOfUsers;

    }



    synchronized
    private boolean addUser(User user){
        if(!registeredUsers.containsKey(user.getUsername())){
            registeredUsers.put(user.getUsername(), user);
            return true;
        }

        return false;
    }




    protected int getRegisteredUsersSize(){
        return registeredUsers.size();

    }

}
