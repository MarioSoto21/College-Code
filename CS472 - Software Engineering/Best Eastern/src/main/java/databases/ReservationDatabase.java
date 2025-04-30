package databases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import objects.Reservation;

public class ReservationDatabase {
    private List<Reservation> database;

    public ReservationDatabase() {
        database = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        database.add(reservation);
    }

    public List<Reservation> getReservations() {
        return database;
    }

    public void removeReservation(int reservationId) {
        Iterator<Reservation> iterator = database.iterator();
        while (iterator.hasNext()) {
            Reservation reservation = iterator.next();
            if (reservation.getReservationId() == reservationId) {
                iterator.remove();
                return;
            }
        }
    }

    public Reservation getReservation(int reservationId) {
        for (Reservation reservation : database) {
            if (reservation.getReservationId() == reservationId) {
                return reservation;
            }
        }
        return null;
    }
}
