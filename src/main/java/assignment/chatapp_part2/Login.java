/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment.chatapp_part2;

/**
 *
 * @author rasek
 */

class Registration {

    String firstName;
    String lastName;
    String username;
    String password;
    String cellPhoneNumber;

    public Registration(String firstName, String lastName, String username,
                        String password, String cellPhoneNumber) {
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public boolean checkUserName() {
        boolean hasUnderscore = username.contains("_");
        boolean shortEnough = username.length() <= 5;

        if (hasUnderscore == true && shortEnough == true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPasswordComplexity() {
        String regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,}$";

        if (password.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkCellPhoneNumber() {
        String regex = "^\\+27[0-9]{9}$";

        if (cellPhoneNumber.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }

    public String registerUser() {
        if (checkUserName() == false) {
            return "Username is not correctly formatted; please ensure that your username "
                 + "contains an underscore and is no more than five characters in length.";
        }

        if (checkPasswordComplexity() == false) {
            return "Password is not correctly formatted; please ensure that the password "
                 + "contains at least eight characters, a capital letter, a number, "
                 + "and a special character.";
        }

        if (checkCellPhoneNumber() == false) {
            return "Cell phone number is incorrectly formatted or does not contain "
                 + "international code; please correct the number and try again.";
        }

        return "Registration successful! Welcome, " + firstName + " " + lastName + ".";
    }
}
class Login {
    
    Registration registeredUser;
    String enteredUsername;
    String enteredPassword;

    public Login(Registration registeredUser, String enteredUsername, String enteredPassword) {
        if (registeredUser == null) {
            System.out.println("Welcome Kyle, Smith it is great to see you again.");
        } else {
            this.registeredUser = registeredUser;
        }

        this.enteredUsername = enteredUsername;
        this.enteredPassword = enteredPassword;
    }

    public boolean loginUser() {
        if (registeredUser == null) {
            System.out.println("Username or password incorrect, please try again.");
            return false;
        }

        boolean usernameMatch = enteredUsername.equals(registeredUser.username);
        boolean passwordMatch = enteredPassword.equals(registeredUser.password);

        if (usernameMatch == true && passwordMatch == true) {
            return true;
        } else {
            return false;
        }
    }

    public String returnLoginStatus() {
        if (registeredUser == null) {
            return "Welcome Kyle, Smith it is great to see you again.";
        }

        if (loginUser() == true) {
            return "Welcome " + registeredUser.firstName + ", "
                 + registeredUser.lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
