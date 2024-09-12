package databases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import objects.Card;
import objects.Cart;
import objects.Customer;
import objects.Login;
import objects.Reservation;
import objects.Room;


public class CsvDatabase {
    private static final Logger logger = Logger.getLogger(CsvDatabase.class.getName());
    
    private String CUSTOMER_CSV_FILE;
    private String ROOM_CSV_FILE;
    private String LOGIN_CSV_FILE;
    private String RESERVATION_CSV_FILE;
    private String LAYOUT_CSV_FILE;
    
    public CsvDatabase(String databaseRoot) {
        this.CUSTOMER_CSV_FILE = databaseRoot + "customer_database.csv";
        this.ROOM_CSV_FILE = databaseRoot + "room_database.csv";
        this.LOGIN_CSV_FILE = databaseRoot + "login_database.csv";
        this.RESERVATION_CSV_FILE = databaseRoot + "reservation_database.csv";
        this.LAYOUT_CSV_FILE = databaseRoot + "layout_database.csv";
    }

    //Savers
    public void saveCustomerDatabase(CustomerDatabase database) {
        try (FileWriter writer = new FileWriter(CUSTOMER_CSV_FILE)) {
            for (Customer customer : database.getCustomers()) {
                writer.append(customerToCsvString(customer)).append("\n");
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing to customer CSV file", e);
        }
    }

    public void saveRoomDatabase(RoomDatabase database) {
        try (FileWriter writer = new FileWriter(ROOM_CSV_FILE)) {
            for (Room room : database.getRooms()) {
                writer.append(roomToCsvString(room)).append("\n");
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing to room CSV file", e);
        }
    }

    public void saveLoginDatabase(LoginDatabase database) {
        try (FileWriter writer = new FileWriter(LOGIN_CSV_FILE)) {
            for (Login login : database.getLogins()) {
                writer.append(loginToCsvString(login)).append("\n");
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing to login CSV file", e);
        }
    }

    public void saveReservationDatabase(ReservationDatabase database) {
        try (FileWriter writer = new FileWriter(RESERVATION_CSV_FILE)) {
            for (Reservation reservation : database.getReservations()) {
                writer.append(reservationToCsvString(reservation)).append("\n");
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing to reservation CSV file", e);
        }
    }

    public void saveLayoutDatabase(LayoutDatabase database) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LAYOUT_CSV_FILE))) {
            int[][][] roomLayout = database.getRoomLayout();
            for (int[][] floor : roomLayout) {
                for (int[] row : floor) {
                    StringBuilder line = new StringBuilder();
                    for (int roomNumber : row) {
                        line.append(roomNumber).append(",");
                    }
                    line.deleteCharAt(line.length() - 1); // Remove trailing comma
                    writer.write(line.toString());
                    writer.newLine();
                }
                if (floor != roomLayout[roomLayout.length - 1]) {
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing to layout CSV file", e);
        }
    }

    //Loaders
    public CustomerDatabase loadCustomerDatabase() {
        CustomerDatabase database = new CustomerDatabase();
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Customer customer = csvStringToCustomer(line);
                database.addCustomer(customer);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading customer CSV file", e);
        }
        return database;
    }

    public RoomDatabase loadRoomDatabase() {
        RoomDatabase database = new RoomDatabase();
        try (BufferedReader reader = new BufferedReader(new FileReader(ROOM_CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Room room = csvStringToRoom(line);
                database.addRoom(room);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading room CSV file", e);
        }
        return database;
    }

    public LoginDatabase loadLoginDatabase() {
        LoginDatabase database = new LoginDatabase();
        try (BufferedReader reader = new BufferedReader(new FileReader(LOGIN_CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Login login = csvStringToLogin(line);
                database.addLogin(login);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading login CSV file", e);
        }
        return database;
    }

    public ReservationDatabase loadReservationDatabase() {
        ReservationDatabase database = new ReservationDatabase();
        try (BufferedReader reader = new BufferedReader(new FileReader(RESERVATION_CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Reservation reservation = csvStringToReservation(line);
                database.addReservation(reservation);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading login CSV file", e);
        }
        return database;
    }

    public LayoutDatabase loadLayoutDatabase() {
        LayoutDatabase layout = null;
        List<List<List<Integer>>> layoutData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(LAYOUT_CSV_FILE))) {
            String line;
            List<List<Integer>> currentFloor = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    layoutData.add(currentFloor);
                    currentFloor = new ArrayList<>();
                } else {
                    String[] roomNumbers = line.split(",");
                    List<Integer> row = new ArrayList<>();
                    for (String roomNumber : roomNumbers) {
                        row.add(Integer.parseInt(roomNumber));
                    }
                    currentFloor.add(row);
                }
            }
            layoutData.add(currentFloor);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading layout CSV file", e);
        }
        if (!layoutData.isEmpty()) {
            int[][][] roomLayout = new int[layoutData.size()][][];
            for (int i = 0; i < layoutData.size(); i++) {
                List<List<Integer>> floorData = layoutData.get(i);
                int[][] floorArray = new int[floorData.size()][];
                for (int j = 0; j < floorData.size(); j++) {
                    List<Integer> rowData = floorData.get(j);
                    int[] rowArray = new int[rowData.size()];
                    for (int k = 0; k < rowData.size(); k++) {
                        rowArray[k] = rowData.get(k);
                    }
                    floorArray[j] = rowArray;
                }
                roomLayout[i] = floorArray;
            }
            layout = new LayoutDatabase(roomLayout);
        }
        return layout;
    }

    //String makers
    private String customerToCsvString(Customer customer) {
        return String.format("%d,%s,%s,%s,%d,%d,%b,%s",
                customer.getUserID(),
                customer.getName(),
                customer.getEmail(),
                cardDataToCsvString(customer.getCardData()),
                customer.getNumberPeople(),
                customer.getAge(),
                customer.isVeteran(),
                cartDataToCsvString(customer.getCartData()));
    }

    private String roomToCsvString(Room room) {
        return String.format("%d,%d,%d,%b,%b,%d",
                room.getCustomerId(),
                room.getRoomNumber(),
                room.getRoomSize(),
                room.isCleaned(),
                room.isPetAllowed(),
                room.getRoomPrice());
    }

    private String cardDataToCsvString(Card cardData) {
        return String.format("%d,%d,%s,%s",
                cardData.getCardNumber(),
                cardData.getCvv(),
                cardData.getCardholderName(),
                cardData.getExpirationDate());
    }

    private String cartDataToCsvString(Cart cartData) {
        StringBuilder stringBuilder = new StringBuilder();
        List<Integer> items = cartData.getItems();
        for (int i = 0; i < items.size(); i++) {
            stringBuilder.append(items.get(i));
            if (i < items.size() - 1) {
                stringBuilder.append("-");
            }
        }
        return stringBuilder.toString();
    }

    private String loginToCsvString(Login login) {
        return String.format("%d,%s,%s",
                login.getUserID(),
                login.getUsername(),
                login.getPassword());
    }

    private String reservationToCsvString(Reservation reservation) {
        return String.format("%d,%f,%s,%s,%d,%d",
                reservation.getCustomerId(),
                reservation.getPrice(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getRoomNumber(),
                reservation.getReservationId());
    }

    //String UNmakers
    private Customer csvStringToCustomer(String csvString) {
        String[] parts = csvString.split(",");
        int userId = Integer.parseInt(parts[0]);
        String name = parts[1];
        String email = parts[2];
        Card cardData = csvStringToCardData(Integer.parseInt(parts[3]),Integer.parseInt(parts[4]),parts[5],parts[6]);
        int numberPeople = Integer.parseInt(parts[7]);
        int age = Integer.parseInt(parts[8]);
        boolean veteran = Boolean.parseBoolean(parts[9]);
        Cart cartData = csvStringToCartData(parts[10]);
        return new Customer(userId, name, email, cardData, numberPeople, age, veteran, cartData);
    }

    private Room csvStringToRoom(String csvString) {
        String[] parts = csvString.split(",");
        int customerId = Integer.parseInt(parts[0]);
        int roomNumber = Integer.parseInt(parts[1]);
        int roomSize = Integer.parseInt(parts[2]);
        boolean cleaned = Boolean.parseBoolean(parts[3]);
        boolean petAllowed = Boolean.parseBoolean(parts[4]);
        int roomPrice = Integer.parseInt(parts[5]);
        return new Room(customerId, roomNumber, roomSize, cleaned, petAllowed, roomPrice);
    }

    private Card csvStringToCardData(int cardNumber,int  cvv, String cardholderName, String expirationDate) {
        return new Card(cardNumber, cvv, cardholderName, expirationDate);
    }
    
    private Cart csvStringToCartData(String csvString) {
        String[] itemStrings = csvString.split("-");
        List<Integer> items = new ArrayList<>();
        for (String itemString : itemStrings) {
            items.add(Integer.parseInt(itemString));
        }
        return new Cart(items);
    }

    private Login csvStringToLogin(String csvString) {
        String[] parts = csvString.split(",");
        int userID = Integer.parseInt(parts[0]);
        String username = parts[1];
        String password = parts[2];
        return new Login(userID, username, password);
    }

    private Reservation csvStringToReservation(String csvString) {
        String[] parts = csvString.split(",");
        int customerId = Integer.parseInt(parts[0]);
        double price = Double.parseDouble(parts[1]);
        String startDate = parts[2];
        String endDate = parts[3];
        int roomNumber = Integer.parseInt(parts[4]);
        int reservationId = Integer.parseInt(parts[5]);
        return new Reservation(customerId, price, startDate, endDate, roomNumber, reservationId);
    }
}