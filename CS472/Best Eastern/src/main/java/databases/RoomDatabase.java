package databases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import objects.Room;

public class RoomDatabase {
    private List<Room> database;

    public RoomDatabase() {
        database = new ArrayList<>();
    }

    public void addRoom(Room room) {
        database.add(room);
    }

    public List<Room> getRooms() {
        return database;
    }

    public void removeRoom(int roomNumber) {
        Iterator<Room> iterator = database.iterator();
        while (iterator.hasNext()) {
            Room room = iterator.next();
            if (room.getRoomNumber() == roomNumber) {
                iterator.remove();
                return;
            }
        }
    }

    public Room getRoom(int roomNumber) {
        for (Room room : database) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }
}
