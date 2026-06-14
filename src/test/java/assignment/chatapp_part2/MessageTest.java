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

public class MessageTest {

    @Test
   public void setUp() {
        // Reset all counters and arrays before each test
        Message.totalMessagesSent   = 0;
        Message.totalDisregarded    = 0;
        Message.totalStored         = 0;
        Message.totalStoredMessages = 0;
        Message.sentMessages            = new String[100];
        Message.disregardedMessages     = new String[100];
        Message.messageHashes           = new String[100];
        Message.messageIDs              = new String[100];
        Message.storedMessageTexts      = new String[100];
        Message.storedMessageRecipients = new String[100];
        Message.storedMessageHashes     = new String[100];

        // Test Message 1 - Flag: Sent
        // Recipient: +27834557896, Message: "Did you get the cake?"
        Message msg1 = new Message("+27834557896", "Did you get the cake?");
        msg1.SentMessage(1);

        // Test Message 2 - Flag: Stored
        // Recipient: +27838884567
        // Message: "Where are you? You are late! I have asked you to be on time."
        Message msg2 = new Message("+27838884567",
                "Where are you? You are late! I have asked you to be on time.");
        msg2.SentMessage(3);

        // Test Message 3 - Flag: Disregard
        // Recipient: +27834484567, Message: "Yohoooo, I am at your gate."
        Message msg3 = new Message("+27834484567", "Yohoooo, I am at your gate.");
        msg3.SentMessage(2);

        // Test Message 4 - Flag: Sent
        // Recipient: 0838884567, Message: "It is dinner time!"
        Message msg4 = new Message("0838884567", "It is dinner time!");
        msg4.SentMessage(1);

        // Test Message 5 - Flag: Stored
        // Recipient: +27838884567, Message: "Ok, I am leaving without you."
        Message msg5 = new Message("+27838884567", "Ok, I am leaving without you.");
        msg5.SentMessage(3);
    }

    // -------------------------------------------------------
    // Sent Messages array correctly populated
    // Test Data: messages 1 and 4
    // The system returns: "Did you get the cake?" and "It is dinner time!"
    // -------------------------------------------------------
    @Test
    public void testSentMessagesArray_correctlyPopulated() {
        assertNotNull(Message.sentMessages[0]);
        assertNotNull(Message.sentMessages[1]);
        assertEquals("Did you get the cake?", Message.sentMessages[0]);
        assertEquals("It is dinner time!",    Message.sentMessages[1]);
    }

    // -------------------------------------------------------
    // Display the longest stored message
    // The system returns:
    // "Where are you? You are late! I have asked you to be on time."
    // -------------------------------------------------------
    @Test
    public void testGetLongestStoredMessage() {
        String expected = "Where are you? You are late! I have asked you to be on time.";
        assertEquals(expected, Message.getLongestStoredMessage());
    }

    // -------------------------------------------------------
    // Search for messageID - found
    // Test Data: message 4 - "It is dinner time!"
    // -------------------------------------------------------
    @Test
    public void testSearchByMessageID_found() {
        // messageIDs[0] = msg1, messageIDs[1] = msg4
        String searchID = Message.messageIDs[1];
        assertNotNull(searchID);
        String result = Message.searchByMessageID(searchID);
        assertTrue(result.contains("It is dinner time!"));
    }

    // Search for messageID - not found
    @Test
    public void testSearchByMessageID_notFound() {
        String result = Message.searchByMessageID("0000000000");
        assertEquals("Message ID not found.", result);
    }

    // -------------------------------------------------------
    // Search all messages for a particular recipient
    // Test Data: +27838884567
    // The system returns both messages for that recipient
    // -------------------------------------------------------
    @Test
    public void testSearchByRecipient_found() {
        String result = Message.searchByRecipient("+27838884567");
        assertNotNull(result);
        assertTrue(result.contains(
                "Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    // Search by recipient - not found
    @Test
    public void testSearchByRecipient_notFound() {
        String result = Message.searchByRecipient("+27000000000");
        assertTrue(result.contains("No messages found for recipient"));
    }

    // -------------------------------------------------------
    // Delete a message using message hash
    // Test Data: Test Message 2
    // The system returns: message successfully deleted
    // -------------------------------------------------------
    @Test
    public void testDeleteByMessageHash_found() {
        // storedMessageHashes[0] = msg2 hash (first stored message)
        String hashToDelete = Message.storedMessageHashes[0];
        assertNotNull(hashToDelete);
        String result = Message.deleteByMessageHash(hashToDelete);
        assertTrue(result.contains("successfully deleted."));
    }

    // Delete by hash - not found
    @Test
    public void testDeleteByMessageHash_notFound() {
        String result = Message.deleteByMessageHash("INVALIDHASH");
        assertEquals("Message hash not found.", result);
    }

    // -------------------------------------------------------
    // checkMessageLength tests
    // -------------------------------------------------------

    // Message under 250 characters
    @Test
    public void testCheckMessageLength_underLimit() {
        Message msg     = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        String expected = "Message ready to send.";
        assertEquals(expected, msg.checkMessageLength());
    }

    // Message exactly 250 characters
    @Test
    public void testCheckMessageLength_exactLimit() {
        String exactMessage = "";
        for (int i = 0; i < 250; i++) {
            exactMessage = exactMessage + "a";
        }
        Message msg     = new Message("+27718693002", exactMessage);
        String expected = "Message ready to send.";
        assertEquals(expected, msg.checkMessageLength());
    }

    // Message over 250 characters
    @Test
    public void testCheckMessageLength_overLimit() {
        String longMessage = "";
        for (int i = 0; i < 275; i++) {
            longMessage = longMessage + "a";
        }
        Message msg     = new Message("+27718693002", longMessage);
        int over        = longMessage.length() - 250;
        String expected = "Message exceeds 250 characters by " + over
                + "; please reduce the size.";
        assertEquals(expected, msg.checkMessageLength());
    }

    // -------------------------------------------------------
    // checkRecipientCell tests
    // -------------------------------------------------------

    // Correctly formatted
    @Test
    public void testCheckRecipientCell_correctlyFormatted() {
        Message msg     = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        String expected = "Cell phone number successfully captured.";
        assertEquals(expected, msg.checkRecipientCell());
    }

    // Incorrectly formatted
    @Test
    public void testCheckRecipientCell_incorrectlyFormatted() {
        Message msg     = new Message("08575975889",
                "Hi Keegan, did you receive the payment?");
        String expected = "Cell phone number is incorrectly formatted or does not contain "
                + "an international code. Please correct the number and try again.";
        assertEquals(expected, msg.checkRecipientCell());
    }

    // -------------------------------------------------------
    // returnTotalMessagess tests
    // -------------------------------------------------------

    // After sending one more message total should increase
    @Test
    public void testReturnTotalMessagess_afterSending() {
        Message msg = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        msg.SentMessage(1);
        // setUp already sent 2 messages so total should now be 3
        assertEquals(3, msg.returnTotalMessagess());
    }

    // After discarding a message the sent total should not change
    @Test
    public void testReturnTotalMessagess_afterDiscard_remainsZero() {
        Message.totalMessagesSent = 0;
        Message msg = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        msg.SentMessage(2);
        assertEquals(0, Message.totalMessagesSent);
    }

    // -------------------------------------------------------
    // checkMessageID test
    // -------------------------------------------------------

    // Auto-generated ID should always be 10 digits or less
    @Test
    public void testCheckMessageID_isValid() {
        Message msg = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        assertTrue(msg.checkMessageID());
    }

    // -------------------------------------------------------
    // SentMessage() choice tests
    // -------------------------------------------------------

    // Choice 1 - Send
    @Test
    public void testSentMessage_sendChoice_returnsCorrectMessage() {
        Message msg     = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        String expected = "Message successfully sent.";
        assertEquals(expected, msg.SentMessage(1));
    }

    // Choice 2 - Disregard
    @Test
    public void testSentMessage_disregardChoice_returnsCorrectMessage() {
        Message msg     = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        String expected = "Press 0 to delete the message.";
        assertEquals(expected, msg.SentMessage(2));
    }

    // Choice 3 - Store
    @Test
    public void testSentMessage_storeChoice_returnsCorrectMessage() {
        Message msg     = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        String expected = "Message successfully stored.";
        assertEquals(expected, msg.SentMessage(3));
    }
}
