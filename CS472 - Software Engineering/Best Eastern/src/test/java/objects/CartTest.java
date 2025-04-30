package objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CartTest {
    @Test
    public void testGetItems() {
        List<Integer> items = new ArrayList<>();
        items.add(101);
        items.add(102);
        Cart cart = new Cart(items);
        assertEquals(items, cart.getItems());
    }

    @Test
    public void testSetItems() {
        List<Integer> items = new ArrayList<>();
        items.add(101);
        items.add(102);
        Cart cart = new Cart(new ArrayList<>());
        cart.setItems(items);
        assertEquals(items, cart.getItems());
    }

    @Test
    public void testAddItem() {
        List<Integer> items = new ArrayList<>();
        items.add(101);
        items.add(102);
        Cart cart = new Cart(items);
        cart.addItem(103);
        assertTrue(cart.getItems().contains(103));
    }

    @Test
    public void testRemoveItem() {
        List<Integer> items = new ArrayList<>();
        items.add(101);
        items.add(102);
        Cart cart = new Cart(items);
        cart.removeItem(102);
        assertFalse(cart.getItems().contains(102));
    }

    @Test
    public void testRemoveItems() {
        List<Integer> items = new ArrayList<>();
        items.add(101);
        items.add(102);
        Cart cart = new Cart(items);
        cart.removeItems();
        assertTrue(cart.getItems().isEmpty());
    }
}
