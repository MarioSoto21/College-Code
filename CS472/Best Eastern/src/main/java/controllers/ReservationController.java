package controllers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import databases.ReservationDatabase;
import objects.Reservation;

public class ReservationController {
    private ReservationDatabase reservationDatabase;

    public ReservationController(ReservationDatabase reservationDatabase) {
        this.reservationDatabase = reservationDatabase;
    }

    public void addReservation(Reservation reservation) {
        reservationDatabase.addReservation(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationDatabase.getReservations();
    }

    public void removeReservation(int reservationId){
        reservationDatabase.removeReservation(reservationId);
    }

    public Reservation getReservation(int reservationId){
        return reservationDatabase.getReservation(reservationId);
    }

    public boolean checkReservationAvailable(int roomNumber, String start, String end) {
        boolean check = true;
        for (Reservation reservation : reservationDatabase.getReservations()) {
            if (reservation.getRoomNumber() == roomNumber) {
                String reservationStart = reservation.getStartDate();
                String reservationEnd = reservation.getEndDate();
                if (isOverlap(start, end, reservationStart, reservationEnd)){
                    check = false;
                }
            }
        }
        return check; // Room available
    }

    private boolean isOverlap(String start1, String end1, String start2, String end2) {
        LocalDate startTime1 = LocalDate.parse(start1);
        LocalDate endTime1 = LocalDate.parse(end1);
        LocalDate startTime2 = LocalDate.parse(start2);
        LocalDate endTime2 = LocalDate.parse(end2);
        return !(endTime1.isBefore(startTime2) || startTime1.isAfter(endTime2));
    }

    public List<Reservation> getCustomerReservations(int customerId) {
        List<Reservation> customerReservations = new ArrayList<>();
        for (Reservation reservation : reservationDatabase.getReservations()) {
            if (reservation.getCustomerId() == customerId) {
                customerReservations.add(reservation);
            }
        }
        return customerReservations;
    }
    
    public List<Reservation> getRoomReservations(int roomNumber) {
        List<Reservation> roomReservations = new ArrayList<>();
        for (Reservation reservation : reservationDatabase.getReservations()) {
            if (reservation.getRoomNumber() == roomNumber) {
                roomReservations.add(reservation);
            }
        }
        return roomReservations;
    }
    
    public int getNextAvailableReservationID() {
        int maxReservationId = 0;
        for (Reservation reservation : reservationDatabase.getReservations()) {
            if (reservation.getReservationId() > maxReservationId) {
                maxReservationId = reservation.getReservationId();
            }
        }
        return maxReservationId + 1;
    }

    public double getPriceFromDates(int pricePerNight, String start, String end) {
        double price = 0;
        LocalDate startTime = LocalDate.parse(start);
        LocalDate endTime = LocalDate.parse(end);
        long numberOfNights = ChronoUnit.DAYS.between(startTime, endTime);
        price = numberOfNights * pricePerNight;
        return price;
    }
}