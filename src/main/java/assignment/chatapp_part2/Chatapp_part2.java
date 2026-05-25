/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package assignment.chatapp_part2;

import java.util.Scanner;

/**
 *
 * @author rasek
 */
public class Chatapp_part2 {

    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
        
        //REGISTRATION
          System.out.println("============================================");
        System.out.println("       Welcome to ChatApp Registration      ");
        System.out.println("============================================");

        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter a username (max 5 characters, must contain '_'): ");
        String username = scanner.nextLine();

        System.out.print("Enter a password (min 8 chars, 1 capital, 1 number, 1 special character): ");
        String password = scanner.nextLine();

        System.out.print("Enter your SA cell number (example: +27838968976): ");
        String cellNumber = scanner.nextLine();

        //Creating a registration object and try to register
        Registration reg = new Registration(firstName, lastName, username, password, cellNumber);
        String registrationResult = reg.registerUser();

        System.out.println("\n--- Registration Result ---");
        System.out.println(registrationResult);

        //Stoping the program if registration failed
        if (registrationResult.startsWith("Registration successful") == false) {
            System.out.println("Please restart and try again.");
            scanner.close();
            return;
        }

        //LOGIN
        System.out.println("\n============================================");
        System.out.println("                  Login                     ");
        System.out.println("============================================");

        System.out.print("Enter your username to log in: ");
        String loginUsername = scanner.nextLine();

        System.out.print("Enter your password to log in: ");
        String loginPassword = scanner.nextLine();

        //Creating a Login object and check the credentials
        Login login = new Login(reg, loginUsername, loginPassword);

        System.out.println("\n--- Login Result ---");
        System.out.println(login.returnLoginStatus());

        //Stops the program if login failed
        if (login.loginUser() == false) {
            System.out.println("Please restart and try again.");
            scanner.close();
            return;
        }
    }
}
