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
    
 // Variables to store this message's details
   String messageID;
    String recipient;
    String messageText;
    String messageHash;
    int messageNumber;

    // Static variables shared across ALL Message objects
    static int totalMessagesSent   = 0;
    static int totalDisregarded    = 0;
    static int totalStored         = 0;
    static int totalStoredMessages = 0;

    // Array to store all sent message texts
    static String[] sentMessages = new String[100];

    // Array to store all disregarded messages
    static String[] disregardedMessages = new String[100];

    // Array to store all message hashes
    static String[] messageHashes = new String[100];

    // Array to store all message IDs
    static String[] messageIDs = new String[100];

    // Arrays to store stored messages
    static String[] storedMessageTexts      = new String[100];
    static String[] storedMessageRecipients = new String[100];
    static String[] storedMessageHashes     = new String[100];

    // Constructor - creates a new message and auto-generates the ID and hash
    public Message(String recipient, String messageText) {
        this.recipient   = recipient;
        this.messageText = messageText;
        this.messageID   = generateMessageID();
        this.messageNumber = totalMessagesSent + totalStored + totalDisregarded + 1;
        this.messageHash = createMessageHash();
    }

    // Builds a random 10-digit ID by adding one random digit at a time in a loop
    private String generateMessageID() {
        Random random = new Random();
        String id = "";

        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10);
            id = id + digit;
        }

        return id;
    }

    // Checks that the message ID is not longer than 10 characters
    public boolean checkMessageID() {
        if (messageID.length() <= 10) {
            return true;
        } else {
            return false;
        }
    }

    // Checks the recipient number is correctly formatted with a +27 international code
    public String checkRecipientCell() {
        String regex = "^\\+27[0-9]{9}$";

        if (recipient.matches(regex)) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain "
                 + "an international code. Please correct the number and try again.";
        }
    }

    // Building the message hash
    public String createMessageHash() {
        String firstTwoDigits = messageID.substring(0, 2);
        String[] words        = messageText.split(" ");
        String firstWord      = words[0];
        String lastWord       = words[words.length - 1];
        String hash           = firstTwoDigits + ":" + messageNumber + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    // Checks the message is 250 characters or less
    public String checkMessageLength() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int over = messageText.length() - 250;
            return "Message exceeds 250 characters by " + over + "; please reduce the size.";
        }
    }

    // Lets the user choose to send, disregard, or store the message
    // This method also populates all the correct arrays
    public String SentMessage(int choice) {
        if (choice == 1) {
            // Store message text in sentMessages array at current index
            sentMessages[totalMessagesSent]  = messageText;
            // Store hash and ID at the same index
            messageHashes[totalMessagesSent] = messageHash;
            messageIDs[totalMessagesSent]    = messageID;
            // Also add to storedMessageTexts so search methods can find it
            storedMessageTexts[totalStoredMessages]      = messageText;
            storedMessageRecipients[totalStoredMessages] = recipient;
            storedMessageHashes[totalStoredMessages]     = messageHash;
            totalStoredMessages  = totalStoredMessages + 1;
            totalMessagesSent    = totalMessagesSent + 1;
            return "Message successfully sent.";

        } else if (choice == 2) {
            // Store the full message details in the disregarded array
            disregardedMessages[totalDisregarded] = printMessages();
            totalDisregarded = totalDisregarded + 1;
            return "Press 0 to delete the message.";

        } else if (choice == 3) {
            // Write to JSON file and also populate stored arrays
            storedMessageTexts[totalStoredMessages]      = messageText;
            storedMessageRecipients[totalStoredMessages] = recipient;
            storedMessageHashes[totalStoredMessages]     = messageHash;
            totalStoredMessages = totalStoredMessages + 1;
            storeMessage();
            totalStored = totalStored + 1;
            return "Message successfully stored.";

        } else {
            return "Invalid option selected.";
        }
    }

    // Returns the full details of this message as a formatted string
    public String printMessages() {
        return "Message ID: "   + messageID   + "\n"
             + "Message Hash: " + messageHash + "\n"
             + "Recipient: "    + recipient   + "\n"
             + "Message: "      + messageText;
    }

    // Returns the total number of messages sent so far
    public int returnTotalMessagess() {
        return totalMessagesSent;
    }

    // Loops through the sentMessages array and prints each one
    public static void printAllSentMessages() {
        if (totalMessagesSent == 0) {
            System.out.println("No messages have been sent yet.");
        } else {
            System.out.println("\n--- All Sent Messages ---");
            for (int i = 0; i < totalMessagesSent; i++) {
                System.out.println("\nMessage " + (i + 1) + ":");
                System.out.println(sentMessages[i]);
                System.out.println("-------------------------");
            }
        }
    }

    // Loads stored messages from the JSON file into the stored arrays
    public static void loadStoredMessages() {
        totalStoredMessages = 0;

        try {
            java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.FileReader("stored_messages.json"));
            String line;

            while ((line = reader.readLine()) != null) {
                String recipient = extractValue(line, "recipient");
                String message   = extractValue(line, "message");
                String hash      = extractValue(line, "messageHash");

                if (recipient != null && message != null) {
                    storedMessageRecipients[totalStoredMessages] = recipient;
                    storedMessageTexts[totalStoredMessages]      = message;
                    storedMessageHashes[totalStoredMessages]     = hash;
                    totalStoredMessages = totalStoredMessages + 1;
                }
            }
            reader.close();

        } catch (Exception e) {
            System.out.println("No stored messages file found.");
        }
    }

    // Helper method to extract a value from a JSON line by its key
    private static String extractValue(String line, String key) {
        String searchFor = "\"" + key + "\": \"";
        int start        = line.indexOf(searchFor);

        if (start == -1) {
            return null;
        }

        start   = start + searchFor.length();
        int end = line.indexOf("\"", start);

        if (end == -1) {
            return null;
        }

        return line.substring(start, end);
    }

    // a. Displays the sender and recipient of all stored messages
    public static void displayStoredSenderAndRecipient(String senderUsername) {
        if (totalStoredMessages == 0) {
            System.out.println("No stored messages found.");
        } else {
            System.out.println("\n--- Stored Message Senders and Recipients ---");
            for (int i = 0; i < totalStoredMessages; i++) {
                System.out.println("Sender: "    + senderUsername);
                System.out.println("Recipient: " + storedMessageRecipients[i]);
                System.out.println("-------------------------");
            }
        }
    }

    // b. Finds and returns the longest stored message
    public static String getLongestStoredMessage() {
        if (totalStoredMessages == 0) {
            return "No stored messages found.";
        }

        String longest = storedMessageTexts[0];

        for (int i = 1; i < totalStoredMessages; i++) {
            if (storedMessageTexts[i].length() > longest.length()) {
                longest = storedMessageTexts[i];
            }
        }

        return longest;
    }

    // c. Searches for a message by ID and returns the recipient and message
    public static String searchByMessageID(String searchID) {
        for (int i = 0; i < totalMessagesSent; i++) {
            if (messageIDs[i] != null && messageIDs[i].equals(searchID)) {
                return "Recipient: " + storedMessageRecipients[i]
                     + "\nMessage: "  + storedMessageTexts[i];
            }
        }
        return "Message ID not found.";
    }

    // d. Searches for all messages stored for a particular recipient
    public static String searchByRecipient(String searchRecipient) {
        String result = "";

        for (int i = 0; i < totalStoredMessages; i++) {
            if (storedMessageRecipients[i] != null
                    && storedMessageRecipients[i].equals(searchRecipient)) {
                result = result + storedMessageTexts[i] + " ";
            }
        }

        if (result.equals("")) {
            return "No messages found for recipient: " + searchRecipient;
        }

        return result.trim();
    }

    // e. Deletes a stored message using its message hash
    public static String deleteByMessageHash(String hashToDelete) {
        for (int i = 0; i < totalStoredMessages; i++) {
            if (storedMessageHashes[i] != null
                    && storedMessageHashes[i].equals(hashToDelete)) {

                String deletedMessage = storedMessageTexts[i];

                // Shift all remaining messages down one position in the array
                for (int j = i; j < totalStoredMessages - 1; j++) {
                    storedMessageTexts[j]      = storedMessageTexts[j + 1];
                    storedMessageRecipients[j] = storedMessageRecipients[j + 1];
                    storedMessageHashes[j]     = storedMessageHashes[j + 1];
                }

                // Clear the last slot and reduce the count
                storedMessageTexts[totalStoredMessages - 1]      = null;
                storedMessageRecipients[totalStoredMessages - 1] = null;
                storedMessageHashes[totalStoredMessages - 1]     = null;
                totalStoredMessages = totalStoredMessages - 1;

                return "Message: \"" + deletedMessage + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    // f. Displays a full report of all stored messages
    public static void displayStoredMessagesReport() {
        if (totalStoredMessages == 0) {
            System.out.println("No stored messages found.");
        } else {
            System.out.println("\n--- Stored Messages Report ---");
            for (int i = 0; i < totalStoredMessages; i++) {
                System.out.println("\nMessage Hash: " + storedMessageHashes[i]);
                System.out.println("Recipient: "    + storedMessageRecipients[i]);
                System.out.println("Message: "      + storedMessageTexts[i]);
                System.out.println("------------------------------");
            }
        }
    }

    // Saves the message to a JSON file so it can be sent later
    public void storeMessage() {
        try {
            String json = "{"
                + "\"messageID\": \""   + messageID   + "\", "
                + "\"messageHash\": \"" + messageHash + "\", "
                + "\"recipient\": \""   + recipient   + "\", "
                + "\"message\": \""     + messageText + "\""
                + "}";

            java.io.FileWriter writer = new java.io.FileWriter("stored_messages.json", true);
            writer.write(json + "\n");
            writer.close();

        } catch (Exception e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }
}
