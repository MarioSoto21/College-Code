package controllers;

import java.util.List;

import databases.CustomerDatabase;
import objects.Cart;
import objects.Customer;
import objects.Room;

public class CustomerController {
    private CustomerDatabase customerDatabase;

    public CustomerController(CustomerDatabase customerDatabase) {
        this.customerDatabase = customerDatabase;
    }

    public void addCustomer(Customer customer) {
        customerDatabase.addCustomer(customer);
    }

    public void removeCustomer(int userId) {
        List<Customer> customers = customerDatabase.getCustomers();
        customers.removeIf(customer -> customer.getUserID() == userId);

        LoginController loginController = Controller.getInstance().getLoginController();
        loginController.removeLoginById(userId);
    }

    public void updateCustomerInfo(int userId, String name, String email, int numberPeople, int age, boolean veteran) {
        Customer customer = getCustomer(userId);
        if (customer != null) {
            customer.setName(name);
            customer.setEmail(email);
            customer.setNumberPeople(numberPeople);
            customer.setAge(age);
            customer.setVeteran(veteran);
        }
    }

    public Customer getCustomer(int userId) {
        for (Customer customer : customerDatabase.getCustomers()) {
            if (customer.getUserID() == userId) {
                return customer;
            }
        }
        return null;
    }

    public Cart getCart(int userId) {
        Customer customer = getCustomer(userId);
        return customer != null ? customer.getCartData() : null;
    }

    public int getNextAvailableUserId() {
        int maxUserId = 0;
        for (Customer customer : customerDatabase.getCustomers()) {
            if (customer.getUserID() > maxUserId) {
                maxUserId = customer.getUserID();
            }
        }
        return maxUserId + 1;
    }

    public List<Customer> getAllCustomers() {
        return customerDatabase.getCustomers();
    }

    public int calculateTotalPrice(int userId) {
        Customer customer = getCustomer(userId);
        if (customer == null) {
            return 0;
        }

        Cart cart = customer.getCartData();
        if (cart == null || cart.getItems().isEmpty()) {
            return 0;
        }

        int totalPrice = 0;
        List<Integer> roomNumbers = cart.getItems();
        RoomController roomController = Controller.getInstance().getRoomController();
        for (Integer roomNumber : roomNumbers) {
            Room room = roomController.getRoom(roomNumber);
            if (room != null) {
                totalPrice += room.getRoomPrice();
            }
        }

        if (customer.getAge() > 75 || customer.isVeteran()) {
            totalPrice -= (totalPrice * 0.1); // Apply 10% discount
        }

        return totalPrice;
    }
}