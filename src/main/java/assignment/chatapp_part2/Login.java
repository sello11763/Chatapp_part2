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

    //Variables to store the users details
    String firstName;
    String lastName;
    String username;
    String password;
    String cellPhoneNumber;

    //Constructor that stores the users details when a Registration object is created
    public Registration(String firstName, String lastName, String username,
                        String password, String cellPhoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    //Checking that the username contains an underscore and is 5 characters or less
    public boolean checkUserName() {
        boolean hasUnderscore = username.contains("_");
        boolean shortEnough = username.length() <= 5;

        if (hasUnderscore == true && shortEnough == true) {
            return true;
        } else {
            return false;
        }
    }

    //Checking the password meets all rules using regex
    public boolean checkPasswordComplexity() {
        String regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,}$";

        if (password.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }

    //Checking the cell number starts with +27 followed by exactly 9 digits
    public boolean checkCellPhoneNumber() {
        String regex = "^\\+27[0-9]{9}$";

        if (cellPhoneNumber.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }

    //Registering the user checks all three fields and returns the correct message
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
    
     //I store the registered user so i can compare credentials at login
    Registration registeredUser;
    String enteredUsername;
    String enteredPassword;

    //Constructor takes the registered user and the login attempt details
    public Login(Registration registeredUser, String enteredUsername, String enteredPassword) {
        this.registeredUser = registeredUser;
        this.enteredUsername = enteredUsername;
        this.enteredPassword = enteredPassword;
    }

    //Checking if the entered username and password match what was registered
    public boolean loginUser() {
        boolean usernameMatch = enteredUsername.equals(registeredUser.username);
        boolean passwordMatch = enteredPassword.equals(registeredUser.password);

        if (usernameMatch == true && passwordMatch == true) {
            return true;
        } else {
            return false;
        }
    }

    //Returning a welcome message on success or an error message on failure
    public String returnLoginStatus() {
        if (loginUser() == true) {
            return "Welcome " + registeredUser.firstName + ", "
                 + registeredUser.lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
    
}
