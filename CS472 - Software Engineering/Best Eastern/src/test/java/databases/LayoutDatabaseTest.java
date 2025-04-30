package databases;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LayoutDatabaseTest {
    @Test
    public void testSetRoomNumber() {
        int[][][] roomLayout = new int[2][3][4];
        LayoutDatabase database = new LayoutDatabase(roomLayout);
        database.setRoomNumber(0, 1, 1, 101);
        assertEquals(101, database.getRoomNumber(0, 1, 1));
    }

    @Test
    public void testGetRoomNumber() {
        int[][][] roomLayout = new int[2][3][4];
        LayoutDatabase database = new LayoutDatabase(roomLayout);
        roomLayout[1][2][3] = 202;
        assertEquals(202, database.getRoomNumber(1, 2, 3));
    }

    @Test
    public void testSetRoomLayout() {
        int[][][] roomLayout = new int[2][3][4];
        LayoutDatabase database = new LayoutDatabase(roomLayout);
        int[][][] newRoomLayout = new int[2][3][4];
        newRoomLayout[1][2][3] = 303;
        database.setRoomLayout(newRoomLayout);
        assertEquals(303, database.getRoomNumber(1, 2, 3));
    }

    @Test
    public void testGetRoomLayout() {
        int[][][] roomLayout = new int[2][3][4];
        roomLayout[0][1][1] = 101;
        LayoutDatabase database = new LayoutDatabase(roomLayout);
        assertArrayEquals(roomLayout, database.getRoomLayout());
    }
}
