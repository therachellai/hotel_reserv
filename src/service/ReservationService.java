package service;
import model.Customer;
import model.IRoom;
import model.Reservation;
import model.ReservationFactory;
import java.util.*;

/**
 * ReservationService class stores and retrieves all the reservations that has been made or is currently being made.
 *
 * @author rachellai
 */
public final class ReservationService {
    private static ReservationService instance;
    private final Set<Reservation> reservations;
    private final Map<String, IRoom> rooms;
    private final ReservationFactory reservationFactory;

    private ReservationService(ReservationFactory reservationFactory) {
        reservations = new HashSet<>();
        rooms = new HashMap<>();
        this.reservationFactory = reservationFactory;
    }

    public static ReservationService getInstance(ReservationFactory reservationFactory) {
        if (instance == null) {
            instance = new ReservationService(reservationFactory);
        }

        return instance;
    }

    /**
     * Returns all rooms that have been added
     *
     * @return          map of rooms
     */
    public Map<String, IRoom> getRooms() {
        return rooms;
    }

    /**
     * Adds a new room if a room with the same room number hasn't been added
     * @param room          IRoom
     * @throws IllegalArgumentException if a room with the same room number has been added
     */
    public void addRoom(IRoom room) {
        if (rooms.containsKey(room.getRoomNumber())) {
            throw new IllegalArgumentException("Room Number " + room.getRoomNumber() + " already exists.");
        } else {
            rooms.put(room.getRoomNumber(), room);
        }
    }

    /**
     * Returns a room previously added
     * @param roomId            string of room ID
     * @return                  IRoom
     * @throws IllegalArgumentException if no room with the ID has been added
     */
    public IRoom getARoom(String roomId) {
        if (rooms.containsKey(roomId)) {
            return rooms.get(roomId);
        } else {
            throw new IllegalArgumentException("There is no room with number " +
                    roomId);
        }
    }

    /**
     * Makes a new reservation
     * @param customer          Customer who makes the reservation
     * @param room              The IRoom being reserved
     * @param checkInDate       check-in date
     * @param checkOutDate      check-out date
     * @return                  new reservation
     * @throws IllegalArgumentException if room is reserved on those dates.
     */
    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation newReservation = new Reservation(customer, room, checkInDate, checkOutDate);
        if (reservations.contains(newReservation)) {
            throw new IllegalArgumentException("This room is already reserved for these " +
                    "days");
        }
        reservations.add(newReservation);
        return newReservation;
    }

    /**
     * Check for conflicts in dates
     * @param reservation           Reservation
     * @param checkIn               check-in date
     * @param checkOut              check-out date
     * @return                      datesCheckResult containing a check result for each date
     */
    DatesCheckResult checkDates(Reservation reservation, Date checkIn, Date checkOut) {
        boolean isCheckInOK = checkIn.before(reservation.getCheckInDate()) ||
                checkIn.compareTo(reservation.getCheckOutDate()) >= 0;
        boolean isCheckOutOK = checkOut.compareTo(reservation.getCheckInDate()) <= 0 ||
                checkOut.after(reservation.getCheckOutDate());
        return new DatesCheckResult(isCheckInOK, isCheckOutOK);
    }

    /**
     * Finds rooms available on dates.
     *
     * @param checkInDate   check-in date
     * @param checkOutDate  check-out date
     * @return              collection of available rooms
     */
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Map<String, IRoom> availableRooms = new HashMap<>(this.rooms);
        for (Reservation aReservation: this.reservations) {
            DatesCheckResult checkResult = checkDates(aReservation, checkInDate,
                    checkOutDate);
            boolean isCheckInOK = checkResult.isCheckInOK();
            boolean isCheckOutOK = checkResult.isCheckOutOK();
            if (! isCheckInOK || ! isCheckOutOK) {
                availableRooms.remove(aReservation.getRoom().getRoomNumber());
            }
        }
        return new ArrayList<>(availableRooms.values());
    }

    /**
     * Find all reservations a customer has made
     * @param customer           Customer
     * @return                   collection of reservations the customer has made
     */
    public Collection<Reservation> getCustomersReservation(Customer customer) {
        List<Reservation> customersReservation = new ArrayList<>();
        for (Reservation aReservation: this.reservations) {
            if (aReservation.getCustomer().equals(customer)) {
                customersReservation.add(aReservation);
            }
        }
        return customersReservation;
    }

    public void printAllReservation() {
        System.out.println(reservations);
    }

    public Set<Reservation> getAllReservations() {
        return reservations;
    }
    record DatesCheckResult(boolean isCheckInOK, boolean isCheckOutOK) {}
}