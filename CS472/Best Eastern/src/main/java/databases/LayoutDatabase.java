package databases;

public class LayoutDatabase {
    private int[][][] roomLayout;

    public LayoutDatabase(int[][][] roomLayout) {
        this.roomLayout = roomLayout;
    }

    public void setRoomNumber(int floor, int row, int col, int roomNumber) {
        roomLayout[floor][row][col] = roomNumber;
    }

    public int getRoomNumber(int floor, int row, int col) {
        return roomLayout[floor][row][col];
    }

    public int[][][] getRoomLayout() {
        return roomLayout;
    }

    public void setRoomLayout(int[][][] roomLayout) {
        this.roomLayout = roomLayout;
    }
}