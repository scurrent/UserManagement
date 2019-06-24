package sample;


import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import junit.framework.Assert;


import java.util.Set;


public class UserManagementTest {

    private static String username1 = "usera";
    private static String password1 = "passa";
    private static String username2 = "userb";
    private static String password2 = "passb";

    @BeforeClass
    public static void addSomeUsers(){
        UserManagement instance = new UserManagement();
        instance.addUser(username1, password1);
        instance.addUser(username2, password2);
        instance.addUser("userc", "passc");

        for(String user: instance.listUsers()){
            System.out.println(user);
        }




    }

    //can run after any test - these two should always have the same length
    @After
    public void testCountsShouldMatch(){
        UserManagement instance = new UserManagement();
        Assert.assertEquals(instance.listUsers().size(),  instance.getRegisteredUsersSize());

    }


    @Test
    public void testUsername() {
        Assert.assertFalse("too short", User.isUserNameValid("abc"));
        Assert.assertTrue("upper valid", User.isUserNameValid("ABCDEF"));
        Assert.assertTrue("lower valid", User.isUserNameValid("abcd"));
        Assert.assertFalse("non alpha", User.isUserNameValid("abc#"));
        Assert.assertFalse("too long", User.isUserNameValid("abcdefgh"));
    }

    @Test
    public void testPassword() {
        Assert.assertFalse("too short", User.isPasswordValid("abc"));
        Assert.assertTrue("upper", User.isPasswordValid("ABCDEF"));
        Assert.assertTrue("valid", User.isPasswordValid("abcd"));
        Assert.assertFalse("non alpha", User.isPasswordValid("abc#"));
        Assert.assertFalse("too long", User.isPasswordValid("abcdefg"));
    }


    @Test
    public void testPasswordHash() {
        Assert.assertNotNull("Test Not Null", PasswordHelper.getSecurePassword("abcd"));
        Assert.assertNotSame("Test its actually different", "abcd", PasswordHelper.getSecurePassword("abcd"));


    }


    @Test
    public void testAddAndAuthenticate() {

        String newUser= "newA";
        String newPassword= "newB";


        UserManagement instance = new UserManagement();
        Assert.assertTrue(instance.addUser(newUser, newPassword));

        Set<String> myList = instance.listUsers();
        Assert.assertTrue(myList.contains(newUser));

        Assert.assertTrue(instance.authenticateUser(newUser, newPassword));
    }

    @Test
    public void testAuthenticate(){

        UserManagement instance = new UserManagement();
        Assert.assertTrue("valid", instance.authenticateUser(username1, password1));
        Assert.assertFalse("invalid password", instance.authenticateUser(username1, "pass9"));
        Assert.assertFalse("invalid username", instance.authenticateUser("user9", password1));
        Assert.assertFalse("both valid, but not match", instance.authenticateUser(username1, password2));
        Assert.assertFalse("blank password", instance.authenticateUser(username1, ""));
        Assert.assertFalse("blank username", instance.authenticateUser("", password1));


    }

    @Test
    public void testDuplicateUsername(){

        UserManagement instance = new UserManagement();
        Assert.assertTrue(instance.addUser("duped", "dupedA"));
        //should not be able to add user with same username
        Assert.assertFalse(instance.addUser("duped", "dupedB"));



    }



}

