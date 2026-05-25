/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment.chatapp_part2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rasek
 */

class RegistrationTest {

    @Test
    public void testCheckUserName_correctlyFormatted() {
        Registration reg = new Registration("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");
        assertTrue(reg.checkUserName());
    }

    @Test
    public void testCheckUserName_incorrectlyFormatted() {
        Registration reg = new Registration("Kyle", "Smith", "kyle!!!!!!!",
                "Ch&&sec@ke99!", "+27838968976");
        assertFalse(reg.checkUserName());
    }

    @Test
    public void testCheckPasswordComplexity_meetsRequirements() {
        Registration reg = new Registration("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");
        assertTrue(reg.checkPasswordComplexity());
    }

    @Test
    public void testCheckPasswordComplexity_doesNotMeetRequirements() {
        Registration reg = new Registration("Kyle", "Smith", "kyl_1",
                "password", "+27838968976");
        assertFalse(reg.checkPasswordComplexity());
    }

    @Test
    public void testCheckCellPhoneNumber_correctlyFormatted() {
        Registration reg = new Registration("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");
        assertTrue(reg.checkCellPhoneNumber());
    }

    @Test
    public void testCheckCellPhoneNumber_incorrectlyFormatted() {
        Registration reg = new Registration("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "08966553");
        assertFalse(reg.checkCellPhoneNumber());
    }

    @Test
    public void testRegisterUser_successful() {
        Registration reg = new Registration("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");
        String expected = "Registration successful! Welcome, Kyle Smith.";
        assertEquals(expected, reg.registerUser());
    }
    
    @Test
    public void testRegisterUser_badUsername_returnsCorrectMessage() {
        Registration reg = new Registration("Kyle", "Smith", "kyle!!!!!!!",
                "Ch&&sec@ke99!", "+27838968976");
        String expected = "Username is not correctly formatted; please ensure that your username "
                + "contains an underscore and is no more than five characters in length.";
        assertEquals(expected, reg.registerUser());
    }

    @Test
    public void testRegisterUser_badPassword_returnsCorrectMessage() {
        Registration reg = new Registration("Kyle", "Smith", "kyl_1",
                "password", "+27838968976");
        String expected = "Password is not correctly formatted; please ensure that the password "
                + "contains at least eight characters, a capital letter, a number, "
                + "and a special character.";
        assertEquals(expected, reg.registerUser());
    }

    @Test
    public void testRegisterUser_badCellNumber_returnsCorrectMessage() {
        Registration reg = new Registration("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "08966553");
        String expected = "Cell phone number is incorrectly formatted or does not contain "
                + "international code; please correct the number and try again.";
        assertEquals(expected, reg.registerUser());
    
}