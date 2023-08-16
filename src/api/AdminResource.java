package api;

import model.Reservation;
import model.Customer;
import model.IRoom;
import service.ReservationService;
import service.CustomerService;
import java.util.*;

/**
 * Admin Resources act as API to admin menu's user interfaces.
 *
 * @author rachellai
 */

public final class AdminResource {
    private final CustomerService customerService;
    private final ReservationService reservationService;

    /**
     * Constructor
     *
     * @param customerService           CustomerService object that keeps track of customers
     * @param reservationService        reservationService object that keeps track of reservations
     */
    public AdminResource(CustomerService customerService, ReservationService reservationService) {
        this.customerService = customerService;
        this.reservationService = reservationService;
    }

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    /**
     * Add a list of new roosm
     *
     * @param rooms         list of IRoom objects to be added
     */
    public void addRoom(List<IRoom> rooms) {
        for (IRoom newRoom: rooms) {
            reservationService.addRoom(newRoom);
        }
    }

    /**
     * Get all added rooms
     *
     * @return          list of added IRoom objects
     */
    public Collection<IRoom> getAllRooms() {
        Map<String, IRoom> allRooms = reservationService.getRooms();
        return new ArrayList<>(allRooms.values());
    }

    /**
     * Get all customers added
     *
     * @return          collection of added customers
     */
    public Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public Set<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    /**
     * Print all reservations
     */
    public void displayAllReservations() {
        System.out.println(reservationService.getAllReservations());
    }
}
