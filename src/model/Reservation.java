package model;
import java.util.Date;

/**
 * Reservation is made by a customer at given dates.
 *
 * @author rachellai
 */
public class Reservation {
    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    /**
     * Constructor
     *
     * @param customer          customer who makes the reservation
     * @param room              IRoom reserved by the customer
     * @param checkInDate       check-in date
     * @param checkOutDate      check-out date
     */
    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    /**
     *
     * @return          String of reservation data
     */
    @Override
    public String toString() {
        return "Reservation for " + System.lineSeparator() +
                customer + System.lineSeparator() +
                room + System.lineSeparator() +
                "Dates: " + checkInDate + " - " + checkOutDate + ".";
    }
}
