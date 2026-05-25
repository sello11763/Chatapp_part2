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
        registeredUser = new Registration("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");
    }


    @Test
    public void testLoginUser_loginSuccessful() {
        Login login = new Login(registeredUser, "kyl_1", "Ch&&sec@ke99!");
        assertTrue(login.loginUser());
    }

    @Test
    public void testLoginUser_wrongPassword_returnsFalse() {
        Login login = new Login(registeredUser, "kyl_1", "wrongpassword");
        assertFalse(login.loginUser());
    }

    @Test
    public void testLoginUser_wrongUsername_returnsFalse() {
        Login login = new Login(registeredUser, "wrong", "Ch&&sec@ke99!");
        assertFalse(login.loginUser());
    }


    @Test
    public void testReturnLoginStatus_successful() {
        Login login = new Login(registeredUser, "kyl_1", "Ch&&sec@ke99!");
        String expected = "Welcome Kyle, Smith it is great to see you again.";
        assertEquals(expected, login.returnLoginStatus());
    }

    @Test
    public void testReturnLoginStatus_failed() {
        Login login = new Login(registeredUser, "kyl_1", "wrongpassword");
        String expected = "Username or password incorrect, please try again.";
        assertEquals(expected, login.returnLoginStatus());
    }

    
   
    
}