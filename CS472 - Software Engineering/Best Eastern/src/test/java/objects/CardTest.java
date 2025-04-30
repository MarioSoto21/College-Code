package objects;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CardTest {
    @Test
    public void testGetCardNumber() {
        Card card = new Card(1234567890, 123, "John Doe", "12/23");
        assertEquals(1234567890, card.getCardNumber());
    }

    @Test
    public void testSetCardNumber() {
        Card card = new Card(0, 123, "John Doe", "12/23");
        card.setCardNumber(987654321);
        assertEquals(987654321, card.getCardNumber());
    }

    @Test
    public void testGetCvv() {
        Card card = new Card(1234567890, 123, "John Doe", "12/23");
        assertEquals(123, card.getCvv());
    }

    @Test
    public void testSetCvv() {
        Card card = new Card(1234567890, 0, "John Doe", "12/23");
        card.setCvv(321);
        assertEquals(321, card.getCvv());
    }

    @Test
    public void testGetCardholderName() {
        Card card = new Card(1234567890, 123, "John Doe", "12/23");
        assertEquals("John Doe", card.getCardholderName());
    }

    @Test
    public void testSetCardholderName() {
        Card card = new Card(1234567890, 123, null, "12/23");
        card.setCardholderName("Jane Smith");
        assertEquals("Jane Smith", card.getCardholderName());
    }

    @Test
    public void testGetExpirationDate() {
        Card card = new Card(1234567890, 123, "John Doe", "12/23");
        assertEquals("12/23", card.getExpirationDate());
    }

    @Test
    public void testSetExpirationDate() {
        Card card = new Card(1234567890, 123, "John Doe", null);
        card.setExpirationDate("01/25");
        assertEquals("01/25", card.getExpirationDate());
    }
}
