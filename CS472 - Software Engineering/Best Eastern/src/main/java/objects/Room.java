package objects;

public class Room {
    private int customerId;  // 0 if not ocupied
    private int roomNumber;  // Sort by this in tables
    private int roomSize;    // Num humans can fit
    private int roomPrice;   // Stored as int showing price

    private boolean cleaned;
    private boolean petAllowed;

    public Room(int customerId, int roomNumber, int roomSize, boolean cleaned, boolean petAllowed, int roomPrice) {
        this.customerId = customerId;
        this.roomNumber = roomNumber;
        this.roomSize = roomSize;
        this.roomPrice = roomPrice;
        this.cleaned = cleaned;
        this.petAllowed = petAllowed;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }

    public boolean isCleaned() {
        return cleaned;
    }

    public void setCleaned(boolean cleaned) {
        this.cleaned = cleaned;
    }

    public boolean isPetAllowed() {
        return petAllowed;
    }

    public void setPetAllowed(boolean petAllowed) {
        this.petAllowed = petAllowed;
    }
    
    public int getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(int roomPrice) {
        this.roomPrice = roomPrice;
    }
}
