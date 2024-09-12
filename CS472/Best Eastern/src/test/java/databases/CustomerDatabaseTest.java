package databases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import objects.Customer;

public class CustomerDatabaseTest {
    @Test
    public void testAddCustomer() {
        CustomerDatabase database = new CustomerDatabase();
        Customer customer = new Customer(1, "John Doe", "john@example.com", null, 2, 30, false, null);
        database.addCustomer(customer);
        assertTrue(database.getCustomers().contains(customer));
    }

    @Test
    public void testGetCustomers() {
        CustomerDatabase database = new CustomerDatabase();
        Customer customer1 = new Customer(1, "John Doe", "john@example.com", null, 2, 30, false, null);
        Customer customer2 = new Customer(2, "Jane Doe", "jane@example.com", null, 2, 25, false, null);
        database.addCustomer(customer1);
        database.addCustomer(customer2);
        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);
        assertEquals(customers, database.getCustomers());
    }

    @Test
    public void testRemoveCustomer() {
        CustomerDatabase database = new CustomerDatabase();
        Customer customer = new Customer(1, "John Doe", "john@example.com", null, 2, 30, false, null);
        database.addCustomer(customer);
        database.removeCustomer(1);
        assertNull(database.getCustomer(1));
    }

    @Test
    public void testRemoveCustomerDNE() {
        CustomerDatabase database = new CustomerDatabase();
        Customer customer = new Customer(1, "John Doe", "john@example.com", null, 2, 30, false, null);
        database.addCustomer(customer);
        database.removeCustomer(9);
        assertNull(database.getCustomer(9));
    }

    @Test
    public void testGetCustomer() {
        CustomerDatabase database = new CustomerDatabase();
        Customer customer1 = new Customer(1, "John Doe", "john@example.com", null, 2, 30, false, null);
        Customer customer2 = new Customer(2, "Jane Doe", "jane@example.com", null, 2, 25, false, null);
        database.addCustomer(customer1);
        database.addCustomer(customer2);
        assertEquals(customer1, database.getCustomer(1));
    }

    @Test
    public void testGetCustomerDNE() {
        CustomerDatabase database = new CustomerDatabase();
        Customer customer1 = new Customer(1, "John Doe", "john@example.com", null, 2, 30, false, null);
        Customer customer2 = new Customer(2, "Jane Doe", "jane@example.com", null, 2, 25, false, null);
        database.addCustomer(customer1);
        database.addCustomer(customer2);
        assertEquals(null, database.getCustomer(3));
    }
}
