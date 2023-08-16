package ui;

import api.AdminResource;
import model.*;
import ui.ConsolePrinter;

import java.util.*;

/**
 * Admin menu UI
 *
 * @author rachellai
 */
public class AdminMenu{
    private final AdminResource adminResource;
    private final ConsolePrinter consolePrinter;
    private final Scanner scanner;

    /**
     * Constructor of this class.
     *
     * @param adminResource     adminResource object
     * @param scanner           scanner object that reads user input
     * @param consolePrinter    consolePrinter object that prints text to the console
     */
    public AdminMenu(AdminResource adminResource, Scanner scanner, ConsolePrinter consolePrinter) {
        this.scanner = scanner;
        this.adminResource = adminResource;
        this.consolePrinter = consolePrinter;
    }

    boolean isNumber(String strInt) {
        if (strInt == null) {
            return false;
        }
        try {
            Double.parseDouble(strInt);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public void printMenu() {
        consolePrinter.print("");
        consolePrinter.print("Admin menu of Rachel's Hotel Reservation App");
        consolePrinter.print("----------------------------------------");
        consolePrinter.print("1. See all Customers");
        consolePrinter.print("2. See all Rooms");
        consolePrinter.print("3. See all Reservations");
        consolePrinter.print("4. Add a room");
        consolePrinter.print("5. Back to Main Menu");
        consolePrinter.print("----------------------------------------");
        consolePrinter.print("Select a menu option");
    }

    /**
     * Gets all customers and prints to console.
     */
    public void showAllCustomers() {
        Collection<Customer> allCustomers = adminResource.getAllCustomers();
        if (allCustomers.isEmpty()) {
            consolePrinter.print("There are no registered customers yet. You can add one in main menu.");
            return;
        }
        for (Customer aCustomer: allCustomers) {
            consolePrinter.print(aCustomer);
        }
    }

    /**
     * Gets all rooms and prints to console.
     */
    public void showAllRooms() {
        Collection<IRoom> allRooms = adminResource.getAllRooms();
        if (allRooms.isEmpty()) {
            consolePrinter.print("There are no rooms yet. Please add some.");
            return;
        }
        for (IRoom aRoom: allRooms) {
            consolePrinter.print(aRoom);
        }
    }

    /**
     * Gets all reservations using admin resource and if any present, prints them to the console.
     */
    public void showAllReservations() {
        Set<Reservation> allReservations = adminResource.getAllReservations();
        if (allReservations.isEmpty()) {
            consolePrinter.print("There are no reservations yet.");
            return;
        }
        for (Reservation reservation: allReservations) {
            consolePrinter.print(reservation);
        }
    }

    /**
     * Creates a new room from user input for as long as the user wants.
     */
    public void addARoom() {
        List<IRoom> newRooms = new ArrayList<>();
        boolean keepAddingRooms = true;
        while (keepAddingRooms) {
            String roomNumber = readRoomNumber(newRooms);
            double roomPrice = readRoomPrice();
            RoomType roomType = readRoomType();
            newRooms.add(new Room(roomNumber, roomPrice, roomType));
            keepAddingRooms = readAddingAnotherRoom();
        }
        adminResource.addRoom(newRooms);
        consolePrinter.print("Rooms were successfully added.");
    }

    /**
     * Checks whether a room number is valid or the corresponding room has been added
     *
     * @param newRooms          List of IRooms
     * @return                  string of room number
     */
    private String readRoomNumber(List<IRoom> newRooms) {
        consolePrinter.print("Enter room number:");
        String input = "";
        boolean isBadRoomNumber = true;
        while (isBadRoomNumber) {
            input = scanner.nextLine();
            if (! isNumber(input)) {
                consolePrinter.print("Room number should be an integer.");
                continue;
            }
            if (! isNewRoomNumber(newRooms, input)) {
                consolePrinter.print("You have already added a room with room number " + input);
            } else {
                isBadRoomNumber = false;
            }
        }
        return input;
    }

    /**
     * Checks whether a room is added for the first time
     *
     * @param newRooms          List of IRooms
     * @param roomNumber        string of room number
     * @return                  boolean of whether a room is added for the first time
     */
    private boolean isNewRoomNumber(List<IRoom> newRooms, String roomNumber) {
        for (IRoom aRoom: newRooms) {
            if (aRoom.getRoomNumber().equals(roomNumber)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks whether room price is a decimal number, and if so return it
     * @return          double of room price
     */
    private double readRoomPrice() {
        consolePrinter.print("Enter room price");
        boolean isBadRoomPrice = true;
        String input = "";
        while (isBadRoomPrice) {
            input = scanner.nextLine();
            if (! isNumber(input)) {
                consolePrinter.print("Room price should be a decimal number");
                continue;
            }
            isBadRoomPrice = false;
        }
        return Double.parseDouble(input);
    }

    /**
     * Checks whether room type is entered in the right format, and if so return it
     *
     * @return          string of room type
     */
    private RoomType readRoomType() {
        consolePrinter.print("Choose room type. \"s\" for single or " +
                "\"d\" for double");
        RoomType roomType = null;
        boolean isBadRoomType = true;
        while (isBadRoomType) {
            String input = scanner.nextLine();
            switch (input) {
                case "d", "D" -> {
                    isBadRoomType = false;
                    roomType = RoomType.DOUBLE;
                }
                case "s", "S" -> {
                    isBadRoomType = false;
                    roomType = RoomType.SINGLE;
                }
                default -> consolePrinter.print("Enter \"s\" for single or \"d\" " +
                        "for double");
            }
        }
        return roomType;
    }

    /**
     * Ask to add another room and perform actions accordingly
     *
     * @return          boolean of whether to keep adding rooms
     */
    private boolean readAddingAnotherRoom() {
        consolePrinter.print("Add another room? (y/n)");
        boolean keepAddingRooms = true;
        boolean isBadInput = true;
        while (isBadInput) {
            String input = scanner.nextLine();
            switch (input.toLowerCase()) {
                case "y" ->
                    // Restart inner while loop
                        isBadInput = false;
                case "n" -> {
                    // Exit both loops
                    isBadInput = false;
                    keepAddingRooms = false;
                }
                default -> // Keep inside inner loop
                        consolePrinter.print("Enter \"y\" for yes or \"n\" for no");
            }
        }
        return keepAddingRooms;
    }
}