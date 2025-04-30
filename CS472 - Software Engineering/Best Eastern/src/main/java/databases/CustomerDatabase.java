package databases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import objects.Customer;

public class CustomerDatabase {
    private List<Customer> database;

    public CustomerDatabase() {
        database = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        database.add(customer);
    }
    
    public List<Customer> getCustomers() {
        return database;
    }

    public void removeCustomer(int customerId) {
        Iterator<Customer> iterator = database.iterator();
        while (iterator.hasNext()) {
            Customer customer = iterator.next();
            if (customer.getUserID() == customerId) {
                iterator.remove();
                break;
            }
        }
    }

    public Customer getCustomer(int customerId) {
        for (Customer customer : database) {
            if (customer.getUserID() == customerId) {
                return customer;
            }
        }
        return null;
    }
}
