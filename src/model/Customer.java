package model;
import java.util.regex.Pattern;
public class Customer {
    private final String firstName;
    private final String lastName;
    private final String email;

    /**
     * Constructor
     *
     * @param firstName         String, first name of customer
     * @param lastName          String, last name of customer
     * @param email             String, email of customer
     * @throws IllegalArgumentException if email is in wrong format
     */
    public Customer(String firstName, String lastName, String email) {
        if (isValid(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Please check the format of your email (xxx@xxx.xxx) and enter again");
        }
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    /**
     *
     * @return         String of customer data
     */
    @Override
    public String toString() {
        return "Customer: " + firstName + " " + lastName + ", " + email + ".";
    }

    /**
     *
     * @param email
     * @return          boolean that checks whether email is in right format
     */
    public boolean isValid(String email) {
        final String emailRegex = "^(.+)@(.+)[.](.+)$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        return emailPattern.matcher(email).matches();
    }

    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}

        Customer customer = (Customer) o;

        return email.equals(customer.email);
    }
}
