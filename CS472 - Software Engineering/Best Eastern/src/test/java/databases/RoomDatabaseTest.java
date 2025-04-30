package databases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import objects.Room;

public class RoomDatabaseTest {
    @Test
    public void testAddRoom() {
        RoomDatabase database = new RoomDatabase();
        Room room = new Room(0, 101, 2, true, true, 100);
        database.addRoom(room);
        List<Room> rooms = database.getRooms();
        assertTrue(rooms.contains(room));
    }

    @Test
    public void testGetRooms() {
        RoomDatabase database = new RoomDatabase();
        database.addRoom(new Room(0, 101, 2, true, true, 100));
        database.addRoom(new Room(0, 102, 2, true, true, 100));
        List<Room> rooms = database.getRooms();
        assertEquals(2, rooms.size());
    }

    @Test
    public void testRemoveRoom() {
        RoomDatabase database = new RoomDatabase();
        Room room1 = new Room(0, 101, 2, true, true, 100);
        Room room2 = new Room(0, 102, 2, true, true, 100);
        database.addRoom(room1);
        database.addRoom(room2);
        database.removeRoom(101);
        assertNull(database.getRoom(101));
        assertNotNull(database.getRoom(102));
    }

    @Test
    public void testRemoveRoomDNE() {
        RoomDatabase database = new RoomDatabase();
        Room room1 = new Room(0, 101, 2, true, true, 100);
        database.addRoom(room1);
        database.removeRoom(102);
        assertNotNull(database.getRoom(101));
        assertNull(database.getRoom(102));
    }

    @Test
    public void testGetRoom() {
        RoomDatabase database = new RoomDatabase();
        Room room1 = new Room(0, 101, 2, true, true, 100);
        Room room2 = new Room(0, 102, 2, true, true, 100);
        database.addRoom(room1);
        database.addRoom(room2);
        assertEquals(room1, database.getRoom(101));
        assertEquals(room2, database.getRoom(102));
    }
}
