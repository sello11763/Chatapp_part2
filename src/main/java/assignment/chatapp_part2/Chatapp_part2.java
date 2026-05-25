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
        
   //Creating a scanner
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
        
        //MESSAGE
        System.out.println("\nWelcome to QuickChat.");

        //Asking how many messages the user wants to send
        System.out.print("\nHow many messages would you like to send? ");
        int maxMessages = Integer.parseInt(scanner.nextLine());

        //Keeps showing the menu until the user chooses Quit
        boolean running = true;

        while (running == true) {

            System.out.println("\n============================================");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Choose an option: ");
            int menuChoice = Integer.parseInt(scanner.nextLine());

            if (menuChoice == 1) {

                //Checking if the user has used up all their messages
                if (Message.totalMessagesSent >= maxMessages) {
                    System.out.println("You have reached your message limit of " + maxMessages + ".");

                } else {

                    //Collecting the message details from the user
                    System.out.print("Enter recipient cell number (e.g. +27718693002): ");
                    String recipientNumber = scanner.nextLine();

                    System.out.print("Enter your message: ");
                    String messageText = scanner.nextLine();

                    //Creating a new Message object
                    Message msg = new Message(recipientNumber, messageText);

                    //Checking the recipient number
                    String cellCheck = msg.checkRecipientCell();
                    System.out.println(cellCheck);

                    //Checking the message length
                    String lengthCheck = msg.checkMessageLength();
                    System.out.println(lengthCheck);

                   
                    if (cellCheck.equals("Cell phone number successfully captured.")
                            && lengthCheck.equals("Message ready to send.")) {

                        //Showing the full message details
                        System.out.println("\n--- Message Details ---");
                        System.out.println("Message ID: "   + msg.messageID);
                        System.out.println("Message Hash: " + msg.messageHash);
                        System.out.println("Recipient: "    + msg.recipient);
                        System.out.println("Message: "      + msg.messageText);

                        // Ask what to do with the message
                        System.out.println("\nWhat would you like to do?");
                        System.out.println("1) Send Message");
                        System.out.println("2) Disregard Message");
                        System.out.println("3) Store Message to send later");
                        System.out.print("Choose an option: ");
                        int sendChoice = Integer.parseInt(scanner.nextLine());

                       
                        System.out.println(msg.SentMessage(sendChoice));
                    }
                }

            } else if (menuChoice == 2) {
               
                Message.printAllSentMessages();

            } else if (menuChoice == 3) {
                
                running = false;
                System.out.println("\nTotal messages sent: " + Message.totalMessagesSent);
                System.out.println("Goodbye!");

            } else {
                System.out.println("Invalid option. Please choose 1, 2, or 3.");
            }
        }

        scanner.close();
        
  }
}