package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;
import java.util.*;

/**
 * HotelResource has little to no behavior contained inside the class and make uses of
 * the Service classes to implement its methods.
 *
 * @author rachellai
 */

public final class HotelResource {
    private final CustomerService customerService;
    private final ReservationService reservationService;

    /**
     * Constructor
     *
     * @param customerService           CustomerService object that keeps track of all customers
     * @param reservationService        ReservationService object that stores and retrieves reservations
     */

    public HotelResource(CustomerService customerService, ReservationService reservationService) {
        this.customerService = customerService;
        this.reservationService = reservationService;
    }

    /**
     * Get customer with email
     *
     * @param email         string of email
     * @return              customer object
     */
    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    /**
     * Create customer given email, first name, and last name
     * @param email         string of email
     * @param firstName     string of first name
     * @param lastName      string of last name
     */
    public void createACustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);
    }

    /**
     * Get a room with room number
     * @param roomNumber            string of room number
     * @return                      IRoom with the room number
     */
    public IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    /**
     * First get a customer with customer email. Then make a reservation for the customer
     *
     * @param customerEmail         string of customer email
     * @param room                  IRoom
     * @param checkInDate           check-in date
     * @param checkOutDate          check-out date
     * @return                      Reservation object
     */
    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer = getCustomer(customerEmail);
        return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    /**
     * First get a customer with customer email. Then get all reservations they have made.
     *
     * @param customerEmail         string of customer email
     * @return                      collection of reservations
     */
    public Collection<Reservation> getCustomersReservations(String customerEmail) {
        Customer customer = getCustomer(customerEmail);
        return reservationService.getCustomersReservation(customer);
    }

    /**
     * Get all rooms available during the check-in and check-out time
     *
     * @param checkIn           check-in date
     * @param checkOut          check-out date
     * @return                  collection of available rooms
     */

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        return reservationService.findRooms(checkIn, checkOut);
    }
}
