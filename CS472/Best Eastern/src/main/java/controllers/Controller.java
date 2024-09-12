 package controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import databases.CsvDatabase;
import databases.CustomerDatabase;
import databases.LayoutDatabase;
import databases.LoginDatabase;
import databases.ReservationDatabase;
import databases.RoomDatabase;

public class Controller {
    // To get a location use the method getLocation().

    // private static final String location = "https://best-eastern.enmucs.com/";
    private static final String location = "http://localhost:8080/best-eastern/pages/";

    // Change this to match the filepath of the Database folder
    private static final String filePath = "/home/bassturtle4/Documents/GitHub/CS472/Database/";

    private static Controller instance;
    private CsvDatabase csvDatabase;
    private ScheduledExecutorService scheduler;

    private CustomerDatabase customerDatabase;
    private LoginDatabase loginDatabase;
    private RoomDatabase roomDatabase;
    private ReservationDatabase reservationDatabase;
    private LayoutDatabase layoutDatabase;
    
    private CustomerController customerController;
    private LoginController loginController;
    private RoomController roomController;
    private ReservationController reservationController;
    private LayoutController layoutController;

    private Controller() {
        csvDatabase = new CsvDatabase(filePath);

        customerDatabase = csvDatabase.loadCustomerDatabase();
        roomDatabase = csvDatabase.loadRoomDatabase();
        loginDatabase = csvDatabase.loadLoginDatabase();
        reservationDatabase = csvDatabase.loadReservationDatabase();
        layoutDatabase = csvDatabase.loadLayoutDatabase();

        customerController = new CustomerController(customerDatabase);
        roomController = new RoomController(roomDatabase);
        loginController = new LoginController(loginDatabase);
        reservationController = new ReservationController(reservationDatabase);
        layoutController = new LayoutController(layoutDatabase);

        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::saveDataToCsv, 0, 1, TimeUnit.MINUTES);
    }

    public static Controller getInstance() {
        if (instance == null) {
            synchronized (Controller.class) {
                if (instance == null) {
                    instance = new Controller();
                }
            }
        }
        return instance;
    }

    public void tester(){
        csvDatabase = new CsvDatabase(getTestFilePath());
    }

    // Load databases from CSV files
    public void loadDataFromCsv() {
        customerDatabase = csvDatabase.loadCustomerDatabase();
        roomDatabase = csvDatabase.loadRoomDatabase();
        loginDatabase = csvDatabase.loadLoginDatabase();
        reservationDatabase = csvDatabase.loadReservationDatabase();
        layoutDatabase = csvDatabase.loadLayoutDatabase();
    }

    // Save databases to CSV files
    public void saveDataToCsv() {
        csvDatabase.saveCustomerDatabase(customerDatabase);
        csvDatabase.saveRoomDatabase(roomDatabase);
        csvDatabase.saveLoginDatabase(loginDatabase);
        csvDatabase.saveReservationDatabase(reservationDatabase);
        csvDatabase.saveLayoutDatabase(layoutDatabase);
    }

    // Customer management methods
    public CustomerDatabase getCustomerDatabase() {
        return customerDatabase;
    }

    public CustomerController getCustomerController() {
        return customerController;
    }

    // Room management methods
    public RoomDatabase getRoomDatabase() {
        return roomDatabase;
    }

    public RoomController getRoomController() {
        return roomController;
    }

    // Login management methods
    public LoginDatabase getLoginDatabase() {
        return loginDatabase;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    // Reservation management methods
    public ReservationDatabase getReservationDatabase() {
        return reservationDatabase;
    }

    public ReservationController getReservationController() {
        return reservationController;
    }

    // Layout management methods
    public LayoutDatabase getLayoutDatabase() {
        return layoutDatabase;
    }

    public LayoutController getLayoutController() {
        return layoutController;
    }

    // Returns starting link
    public String getLocation(String fileName) {
        return location + fileName + ".jsp";
    }

    // Returns the Database filepath
    public static String getFilePath() {
        return filePath;
    }

    // Returns the test Database filepath
    public static String getTestFilePath() {
        String testFilePath = filePath + "test/";

        File fileDirectory = new File(filePath);
        File testFileDirectory = new File(testFilePath);

        if (!testFileDirectory.exists()) {
            testFileDirectory.mkdirs();
        }

        File[] files = fileDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                try {
                    Files.copy(file.toPath(), new File(testFilePath + file.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return filePath + "test/";
    }

    // Copies to files
    public static String copyFiles(String fileName) {
        
        String testFilePath = filePath;
        testFilePath.replace("Database/", "src/main/webapp/pages/files/");

        File fileDirectory = new File(filePath);

        File[] files = fileDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                try {
                    Files.copy(file.toPath(), new File(testFilePath + file.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return location + "files/" + fileName;
    }
}
