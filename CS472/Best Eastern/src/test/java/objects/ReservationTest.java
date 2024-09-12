package objects;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReservationTest {
    @Test
    public void testGetCustomerId() {
        Reservation reservation = new Reservation(1, 100.0, "2024-05-01", "2024-05-10", 101, 1);
        assertEquals(1, reservation.getCustomerId());
    }

    @Test
    public void testSetCustomerId() {
        Reservation reservation = new Reservation(1, 100.0, "2024-05-01", "2024-05-10", 101, 1);
        reservation.setCustomer(2);
        assertEquals(2, reservation.getCustomerId());
    }

    @Test
    public void testGetPrice() {
        Reservation reservation = new Reservation(1, 100.0, "2024-05-01", "2024-05-10", 101, 1);
        assertEquals(100.0, reservation.getPrice(), 0.001);
    }

    @Test
    public void testSetPrice() {
        Reservation reservation = new Reservation(1, 100.0, "2024-05-01", "2024-05-10", 101, 1);
        reservation.setPrice(150.0);
        assertEquals(150.0, reservation.getPrice(), 0.001);
    }

    @Test
    public void testGetStartDate() {
        Reservation reservation = new Reservation(1, 100.0, "2024-05-01", "2024-05-10", 101, 1);
        assertEquals("2024-05-01", reservation.getStartDate());
    }

    @Test
    public void testSetStartDate() {
        Reservation reservation = new Reservation(1, 100.0, "2024-05-01", "2024-05-10", 101, 1);
        reservation.setStartDate("2024-06-01");
        assertEquals("2024-06-01", reservation.getStartDate());
    }

    @Test
    public void testGetEndDate() {
        Reservation reservation = new Reservation(1, 100.0, "2024-05-01", "2024-05-10", 101, 1);
        assertEquals("2024-05-10", reservation.getEndDate());
    }

    @Test
    public void testSetEndDate() {
        Reservation reservation = new Reservation(1, 100.0, "2024-05-01", "2024-05-10", 101, 1);
        reservation.setEndDate("2024-06-10");
        assertEquals("2024-06-10", reservation.getEndDate());
    }

    @Test
    public void testGetRoomNumber() {
        Reservation reservation = new Reservation(1, 100.0, "2024-05-01", "2024-05-10", 101, 1);
        assertEquals(101, reservation.getRoomNumber());
    }

    @Test
    public void testSetRoomNumber() {
        Reservation reservation = new Reservation(1, 100.0, "2024-05-01", "2024-05-10", 101, 1);
        reservation.setRoomNumber(102);
        assertEquals(102, reservation.getRoomNumber());
    }

    @Test
    public void testGetReservationId() {
        Reservation reservation = new Reservation(1, 100.0, "2024-05-01", "2024-05-10", 101, 1);
        assertEquals(1, reservation.getReservationId());
    }

    @Test
    public void testSetReservationId() {
        Reservation reservation = new Reservation(1, 100.0, "2024-05-01", "2024-05-10", 101, 1);
        reservation.setReservationId(2);
        assertEquals(2, reservation.getReservationId());
    }
}
