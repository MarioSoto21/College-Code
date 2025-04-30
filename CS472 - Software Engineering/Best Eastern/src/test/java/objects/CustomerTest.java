package objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CustomerTest {
    @Test
    public void testGetUserID() {
        Customer customer = new Customer(1, null, null, null, 0, 0, false, null);
        assertEquals(1, customer.getUserID());
    }

    @Test
    public void testSetUserID() {
        Customer customer = new Customer(1, null, null, null, 0, 0, false, null);
        customer.setUserID(2);
        assertEquals(2, customer.getUserID());
    }

    @Test
    public void testGetName() {
        Customer customer = new Customer(1, "John Doe", null, null, 0, 0, false, null);
        assertEquals("John Doe", customer.getName());
    }

    @Test
    public void testSetName() {
        Customer customer = new Customer(1, "John Doe", null, null, 0, 0, false, null);
        customer.setName("Jane Smith");
        assertEquals("Jane Smith", customer.getName());
    }

    @Test
    public void testGetEmail() {
        Customer customer = new Customer(1, "John Doe", "john@gmail.com", null, 2, 30, true, null);
        assertEquals("john@gmail.com", customer.getEmail());
    }

    @Test
    public void testSetEmail() {
        Customer customer = new Customer(1, "John Doe", "john@gmail.com", null, 2, 30, true, null);
        customer.setEmail("jane@gmail.com");
        assertEquals("jane@gmail.com", customer.getEmail());
    }

    @Test
    public void testGetCardData() {
        Card card = new Card(1234,123,"NAME","EXPRDATE");
        Customer customer = new Customer(1, "John Doe", "john@gmail.com", card, 2, 30, true, null);
        assertEquals(card, customer.getCardData());
    }

    @Test
    public void testSetCardData() {
        Card card = new Card(1234,123,"NAME","EXPRDATE");
        Customer customer = new Customer(1, "John Doe", "john@gmail.com", null, 2, 30, true, null);
        customer.setCardData(card);
        assertEquals(card, customer.getCardData());
    }

    @Test
    public void testGetNumberPeople() {
        Customer customer = new Customer(1, "John Doe", "john@gmail.com", null, 2, 30, true, null);
        assertEquals(2, customer.getNumberPeople());
    }

    @Test
    public void testSetNumberPeople() {
        Customer customer = new Customer(1, "John Doe", "john@gmail.com", null, 0, 30, true, null);
        customer.setNumberPeople(4);
        assertEquals(4, customer.getNumberPeople());
    }

    @Test
    public void testGetAge() {
        Customer customer = new Customer(1, "John Doe", "john@gmail.com", null, 2, 30, true, null);
        assertEquals(30, customer.getAge());
    }

    @Test
    public void testSetAge() {
        Customer customer = new Customer(1, "John Doe", "john@gmail.com", null, 2, 0, true, null);
        customer.setAge(25);
        assertEquals(25, customer.getAge());
    }

    @Test
    public void testIsVeteran() {
        Customer customer = new Customer(1, "John Doe", "john@gmail.com", null, 2, 30, true, null);
        assertTrue(customer.isVeteran());
    }

    @Test
    public void testSetVeteran() {
        Customer customer = new Customer(1, "John Doe", "john@gmail.com", null, 2, 30, false, null);
        customer.setVeteran(true);
        assertTrue(customer.isVeteran());
    }

    @Test
    public void testGetCartData() {
        List<Integer> items = Arrays.asList(1, 2);
        Cart cart = new Cart(items);
        Customer customer = new Customer(1, "John Doe", "john@gmail.com", null, 2, 30, true, cart);
        assertEquals(cart, customer.getCartData());
    }

    @Test
    public void testSetCartData() {
        Cart cart = new Cart(Arrays.asList(1, 2));
        Cart afterCart = new Cart(Arrays.asList(2, 1));
        Customer customer = new Customer(1, "John Doe", "john@gmail.com", null, 2, 30, true, cart);
        customer.setCartData(afterCart);
        assertEquals(afterCart, customer.getCartData());
    }
}
