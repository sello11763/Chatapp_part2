/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment.chatapp_part2;

import java.util.Random;

/**
 *
 * @author rasek
 */
class Message {
    
    //Creating variables to store this messages details
    String messageID;
    String recipient;
    String messageText;
    String messageHash;
    int messageNumber;

    //Static variables are shared across all message objects
    static int totalMessagesSent = 0;
    static String[] sentMessages = new String[100];

    //Constructor called when we create a new message
    public Message(String recipient, String messageText) {
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageNumber = totalMessagesSent + 1;
        this.messageHash = createMessageHash();
    }

    //Building a random 10-digit ID by adding one random digit at a time in a loop
    private String generateMessageID() {
        Random random = new Random();
        String id = "";

        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10);
            id = id + digit;
        }

        return id;
    }

    //Checking that the message ID is not longer than 10 characters
    public boolean checkMessageID() {
        if (messageID.length() <= 10) {
            return true;
        } else {
            return false;
        }
    }

    //Checking the recipient number is correctly formatted with a +27 international code
    public String checkRecipientCell() {
        String regex = "^\\+27[0-9]{9}$";

        if (recipient.matches(regex)) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain "
                 + "an international code. Please correct the number and try again.";
        }
    }

    //Building a the message hash
    public String createMessageHash() {
        
        String firstTwoDigits = messageID.substring(0, 2);

        String[] words = messageText.split(" ");

        //Getting the first and last word from the array
        String firstWord = words[0];
        String lastWord  = words[words.length - 1];

        String hash = firstTwoDigits + ":" + messageNumber + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    //Checking the message is 250 characters or less
    public String checkMessageLength() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int over = messageText.length() - 250;
            return "Message exceeds 250 characters by " + over + "; please reduce the size.";
        }
    }

    //Letting the user choose to send, disregard or store the message
    public String SentMessage(int choice) {
        if (choice == 1) {
            
            //Store the message in the array at the current index then increment
            sentMessages[totalMessagesSent] = printMessages();
            totalMessagesSent = totalMessagesSent + 1;
            return "Message successfully sent.";

        } else if (choice == 2) {
            return "Press 0 to delete the message.";

        } else if (choice == 3) {
            storeMessage();
            return "Message successfully stored.";

        } else {
            return "Invalid option selected.";
        }
    }
    
    
    
}
