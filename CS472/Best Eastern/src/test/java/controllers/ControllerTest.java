package controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeTrue;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class ControllerTest {

    @Test
    public void testLoadDataFromCsv() {
        try {
            Controller.getInstance().loadDataFromCsv();
            assertTrue(true);
        } catch (Exception e) {
            fail("Error loading data from CSV: " + e.getMessage());
        }
    }

    @Test
    public void testSaveDataToCsv() {
        try {
            Controller.getInstance().saveDataToCsv();
            assertTrue(true);
        } catch (Exception e) {
            fail("Error saving data to CSV: " + e.getMessage());
        }
    }

    @Test
    public void testGetCustomerDatabase() {
        assertNotNull(Controller.getInstance().getCustomerDatabase());
    }

    @Test
    public void testGetRoomDatabase() {
        assertNotNull(Controller.getInstance().getRoomDatabase());
    }

    @Test
    public void testGetLoginDatabase() {
        assertNotNull(Controller.getInstance().getLoginDatabase());
    }

    @Test
    public void testGetReservationDatabase() {
        assertNotNull(Controller.getInstance().getReservationDatabase());
    }

    @Test
    public void testGetLayout() {
        assumeTrue(Files.exists(Paths.get(Controller.getInstance().getFilePath())));
        assertNotNull(Controller.getInstance().getLayoutDatabase());
    }

    @Test
    public void CHECK_FILE_PATH() {
        assertTrue(Files.exists(Paths.get(Controller.getInstance().getFilePath())));
    }

    @Test
    public void testGetCustomerController() {
        assertNotNull(Controller.getInstance().getCustomerController());
    }

    @Test
    public void testGetRoomController() {
        assertNotNull(Controller.getInstance().getRoomController());
    }

    @Test
    public void testGetLoginController() {
        assertNotNull(Controller.getInstance().getLoginController());
    }

    @Test
    public void testGetReservationController() {
        assertNotNull(Controller.getInstance().getReservationController());
    }

    @Test
    public void testGetLayoutController() {
        assertNotNull(Controller.getInstance().getLayoutController());
    }

    @Test
    public void testGetLocation() {
        assertNotNull(Controller.getInstance().getLocation("test"));
    }

    @Test
    public void testGetFilePath() {
        assertNotNull(Controller.getFilePath());
    }
}
