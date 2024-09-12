package controllers;

import java.util.List;

import databases.RoomDatabase;
import objects.Room;

public class RoomController {
    private RoomDatabase roomDatabase;

    public RoomController(RoomDatabase roomDatabase) {
        this.roomDatabase = roomDatabase;
    }

    public void addRoom(Room room) {
        roomDatabase.addRoom(room);
    }

    public void removeRoom(int roomNumber) {
        List<Room> rooms = roomDatabase.getRooms();
        rooms.removeIf(room -> room.getRoomNumber() == roomNumber);
    }

    public void updateRoomCleaningStatus(int roomNumber, boolean cleaned) {
        List<Room> rooms = roomDatabase.getRooms();
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                room.setCleaned(cleaned);
                break;
            }
        }
    }

    public void updatePetAllowance(int roomNumber, boolean petAllowed) {
        List<Room> rooms = roomDatabase.getRooms();
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                room.setPetAllowed(petAllowed);
                break;
            }
        }
    }

    public Room getRoom(int roomNumber) {
        List<Room> rooms = roomDatabase.getRooms();
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    public List<Room> getAllRooms() {
        return roomDatabase.getRooms();
    }
}
