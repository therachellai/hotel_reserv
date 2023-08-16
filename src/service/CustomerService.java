package service;
import model.Customer;
import java.util.*;

/**
 * CustomerService class keeps track of all customers
 *
 * @author rachellai
 */

public final class CustomerService {

    private static CustomerService instance;
    private final Map<String, Customer> customers;
    private CustomerService() {
        this.customers = new HashMap<>();
    }

    public static CustomerService getInstance() {
        if (instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }

    /**
     * Add a new customer if the email isn't already in the database
     *
     * @param email         string, customer's email
     * @param firstName     string, customer's first name
     * @param lastName      string, customer's last name
     */
    public void addCustomer(String email, String firstName, String lastName) {
        Customer newCustomer = new Customer(firstName, lastName, email);
        if (customers.containsKey(email)) {
            throw new IllegalArgumentException("Customer with this email is " +
                    "already registered.");
        } else {
            customers.put(email, newCustomer);
        }
    }

    /**
     *
     * @param customerEmail
     * @return          customer if their email is already in the database
     */
    public Customer getCustomer(String customerEmail) {
        if (this.customers.containsKey(customerEmail)) {
            return this.customers.get(customerEmail);
        } else {return null;}
    }

    /**
     *
     * @return          all customers in the database
     */
    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }
}
