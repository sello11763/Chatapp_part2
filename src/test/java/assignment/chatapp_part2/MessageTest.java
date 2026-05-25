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
        Message.totalMessagesSent = 0;
        Message.sentMessages = new String[100];
    }

    @Test
    public void testCheckMessageID_isValid() {
        Message msg = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testCheckRecipientCell_correctlyFormatted() {
        Message msg = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        String expected = "Cell phone number successfully captured.";
        assertEquals(expected, msg.checkRecipientCell());
    }

    @Test
    public void testCheckRecipientCell_incorrectlyFormatted() {
        Message msg = new Message("08575975889",
                "Hi Keegan, did you receive the payment?");
        String expected = "Cell phone number is incorrectly formatted or does not contain "
                + "an international code. Please correct the number and try again.";
        assertEquals(expected, msg.checkRecipientCell());
    }

    @Test
    public void testCheckMessageLength_underLimit() {
        Message msg = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        String expected = "Message ready to send.";
        assertEquals(expected, msg.checkMessageLength());
    }

    @Test
    public void testCheckMessageLength_overLimit() {
      
        String longMessage = "This message is going to be very long indeed. "
                + "We are adding lots of extra text here to make sure it goes "
                + "well over the two hundred and fifty character limit that has "
                + "been set for this chat application by the developers of the system.";

        Message msg = new Message("+27718693002", longMessage);

        int over = longMessage.length() - 250;
        String expected = "Message exceeds 250 characters by " + over
                + "; please reduce the size.";
        assertEquals(expected, msg.checkMessageLength());
    }

    @Test
    public void testCreateMessageHash_correctFormat() {
        Message msg = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        String firstTwo = msg.messageID.substring(0, 2);
        String expected = (firstTwo + ":1:HITONIGHT").toUpperCase();

        assertEquals(expected, msg.messageHash);
    }

    @Test
    public void testSentMessage_sendChoice_returnsCorrectMessage() {
        Message msg = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        String expected = "Message successfully sent.";
        assertEquals(expected, msg.SentMessage(1));
    }

    @Test
    public void testSentMessage_disregardChoice_returnsCorrectMessage() {
        Message msg = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        String expected = "Press 0 to delete the message.";
        assertEquals(expected, msg.SentMessage(2));
    }

    @Test
    public void testSentMessage_storeChoice_returnsCorrectMessage() {
        Message msg = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        String expected = "Message successfully stored.";
        assertEquals(expected, msg.SentMessage(3));
    }

    @Test
    public void testReturnTotalMessagess_afterOneSent() {
        Message msg = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        msg.SentMessage(1);
        assertEquals(1, msg.returnTotalMessagess());
    }

    @Test
    public void testReturnTotalMessagess_afterDiscard_remainsZero() {
        Message msg = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        msg.SentMessage(2);
        assertEquals(0, msg.returnTotalMessagess());
    }
}
