package model;

/**
 * Driver class for testing purposes only
 *
 * @author rachellai
 */
public class Driver {
    public static void main(String[] args) {
        Customer customer1 = new Customer("first", "second", "j@domain.com");
        System.out.println(customer1);

        Customer customer2 = new Customer("first", "second", "email");
        System.out.println(customer2);
    }
}
