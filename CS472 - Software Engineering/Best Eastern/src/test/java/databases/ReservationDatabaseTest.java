package databases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import objects.Reservation;

public class ReservationDatabaseTest {
    @Test
    public void testAddReservation() {
        ReservationDatabase database = new ReservationDatabase();
        Reservation reservation = new Reservation(1, 100.0, "2024-05-01", "2024-05-05", 101, 1);
        database.addReservation(reservation);
        List<Reservation> reservations = database.getReservations();
        assertTrue(reservations.contains(reservation));
    }

    @Test
    public void testGetReservations() {
        ReservationDatabase database = new ReservationDatabase();
        database.addReservation(new Reservation(1, 100.0, "2024-05-01", "2024-05-05", 101, 1));
        database.addReservation(new Reservation(2, 150.0, "2024-06-01", "2024-06-05", 102, 2));
        List<Reservation> reservations = database.getReservations();
        assertEquals(2, reservations.size());
    }

    @Test
    public void testRemoveReservation() {
        ReservationDatabase database = new ReservationDatabase();
        Reservation reservation1 = new Reservation(1, 100.0, "2024-05-01", "2024-05-05", 101, 1);
        Reservation reservation2 = new Reservation(2, 150.0, "2024-06-01", "2024-06-05", 102, 2);
        database.addReservation(reservation1);
        database.addReservation(reservation2);
        database.removeReservation(1);
        assertNull(database.getReservation(1));
        assertNotNull(database.getReservation(2));
    }

    @Test
    public void testRemoveReservationDNE() {
        ReservationDatabase database = new ReservationDatabase();
        Reservation reservation1 = new Reservation(1, 100.0, "2024-05-01", "2024-05-05", 101, 1);
        database.addReservation(reservation1);
        database.removeReservation(2);
        assertNotNull(database.getReservation(1));
        assertNull(database.getReservation(2));
    }

    @Test
    public void testGetReservation() {
        ReservationDatabase database = new ReservationDatabase();
        Reservation reservation1 = new Reservation(1, 100.0, "2024-05-01", "2024-05-05", 101, 1);
        Reservation reservation2 = new Reservation(2, 150.0, "2024-06-01", "2024-06-05", 102, 2);
        database.addReservation(reservation1);
        database.addReservation(reservation2);
        assertEquals(reservation1, database.getReservation(1));
        assertEquals(reservation2, database.getReservation(2));
    }
}