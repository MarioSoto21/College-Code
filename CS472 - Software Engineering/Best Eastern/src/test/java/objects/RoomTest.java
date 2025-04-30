package objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RoomTest {
    @Test
    public void testGetCustomerId() {
        Room room = new Room(1, 101, 2, true, true, 100);
        assertEquals(1, room.getCustomerId());
    }

    @Test
    public void testSetCustomerId() {
        Room room = new Room(1, 101, 2, true, true, 100);
        room.setCustomerId(2);
        assertEquals(2, room.getCustomerId());
    }

    @Test
    public void testGetRoomNumber() {
        Room room = new Room(1, 101, 2, true, true, 100);
        assertEquals(101, room.getRoomNumber());
    }

    @Test
    public void testSetRoomNumber() {
        Room room = new Room(1, 101, 2, true, true, 100);
        room.setRoomNumber(102);
        assertEquals(102, room.getRoomNumber());
    }

    @Test
    public void testGetRoomSize() {
        Room room = new Room(1, 101, 2, true, true, 100);
        assertEquals(2, room.getRoomSize());
    }

    @Test
    public void testSetRoomSize() {
        Room room = new Room(1, 101, 2, true, true, 100);
        room.setRoomSize(3);
        assertEquals(3, room.getRoomSize());
    }

    @Test
    public void testIsCleaned() {
        Room room = new Room(1, 101, 2, true, true, 100);
        assertTrue(room.isCleaned());
    }

    @Test
    public void testSetCleaned() {
        Room room = new Room(1, 101, 2, true, true, 100);
        room.setCleaned(false);
        assertFalse(room.isCleaned());
    }

    @Test
    public void testIsPetAllowed() {
        Room room = new Room(1, 101, 2, true, true, 100);
        assertTrue(room.isPetAllowed());
    }

    @Test
    public void testSetPetAllowed() {
        Room room = new Room(1, 101, 2, true, true, 100);
        room.setPetAllowed(false);
        assertFalse(room.isPetAllowed());
    }
    
    @Test
    public void testGetRoomPrice() {
        Room room = new Room(1, 101, 2, true, true, 100);
        assertEquals(100, room.getRoomPrice());
    }

    @Test
    public void testSetRoomPrice() {
        Room room = new Room(1, 101, 2, true, true, 100);
        room.setRoomPrice(150);
        assertEquals(150, room.getRoomPrice());
    }
}
