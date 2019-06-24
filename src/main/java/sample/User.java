package sample;

public class User {


    private String username = "";
    private String password = "";

    //ridiculously simple username and pw requirements
    private static final String  REGEX_FOR_VALIDATION = "[A-Za-z0-9]{4,6}$";


    //regardless of how simple my passwords are, I don't want to be responsible for storing them in clear text, or having
    //them decrypted.
    //my solution is SHA-256 hashing them, and storing the hash. since this is not reversible, I have one less security concern
    //to validate password, I can compare hashed values.
    public User(String username, String password){
        this.username = username;
        this.password = PasswordHelper.getSecurePassword(password);
    }


    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }



    public static boolean isUserNameValid(String userName){
        return userName.matches(REGEX_FOR_VALIDATION);
    }

    public static boolean isPasswordValid(String password){
        return password.matches(REGEX_FOR_VALIDATION);
    }





}
