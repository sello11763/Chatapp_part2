/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package assignment.chatapp_part2;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author rasek
 */
public class LoginTest {
     
     Registration registeredUser;
     
 @Test
    public void setUp() {
        // Create Registration object directly - this stores the values into the fields
        registeredUser = new Registration(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        // Print to confirm registeredUser is not null when tests run
        System.out.println("setUp - registeredUser is: " + registeredUser);
        System.out.println("setUp - username is: " + registeredUser.username);
    }


    @Test
    public void testLoginUser_loginSuccessful() {
        Login loginTest = new Login(registeredUser, "kyl_1", "Ch&&sec@ke99!");
        assertTrue(loginTest.loginUser());
    }

    @Test
    public void testLoginUser_wrongPassword_returnsFalse() {
        Login loginTest = new Login(registeredUser, "kyl_1", "wrongpassword");
        assertFalse(loginTest.loginUser());
    }

    @Test
    public void testLoginUser_wrongUsername_returnsFalse() {
        Login loginTest = new Login(registeredUser, "wrong", "Ch&&sec@ke99!");
        assertFalse(loginTest.loginUser());
    }

    @Test
    public void testLoginUser_wrongUsernameAndPassword_returnsFalse() {
        Login loginTest = new Login(registeredUser, "wrong", "wrongpassword");
        assertFalse(loginTest.loginUser());
    }

    @Test
    public void testReturnLoginStatus_successful() {
        Login loginTest = new Login(registeredUser, "kyl_1", "Ch&&sec@ke99!");
        String expected = "Welcome Kyle, Smith it is great to see you again.";
        assertEquals(expected, loginTest.returnLoginStatus());
    }

    @Test
    public void testReturnLoginStatus_wrongPassword_returnsFailMessage() {
        Login loginTest = new Login(registeredUser, "kyl_1", "wrongpassword");
        String expected = "Username or password incorrect, please try again.";
        assertEquals(expected, loginTest.returnLoginStatus());
    }

    @Test
    public void testReturnLoginStatus_wrongUsername_returnsFailMessage() {
        Login loginTest = new Login(registeredUser, "wrong", "Ch&&sec@ke99!");
        String expected = "Username or password incorrect, please try again.";
        assertEquals(expected, loginTest.returnLoginStatus());
    }
    
}