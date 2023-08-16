package ui;
import java.util.*;

/**
 * Menu manager for admin menu UI
 *
 * @author rachellai
 */
public final class AdminMenuManager implements MenuManager {
    private final AdminMenu adminMenu;
    private final Scanner scanner;
    private final ConsolePrinter consolePrinter;

    /**
     * Constructor
     *
     * @param scanner           scanner object that reads user input
     * @param adminMenu         adminMenu object that serves as a menu with tasks a user can choose from
     * @param consolePrinter    consolePrinter object that prints text to the console
     */
    public AdminMenuManager(Scanner scanner, AdminMenu adminMenu, ConsolePrinter consolePrinter) {
        this.adminMenu = adminMenu;
        this.scanner = scanner;
        this.consolePrinter = consolePrinter;
    }

    /**
     * Prints the admin menu, reads input, and performs actions accordingly
     * @throws NumberFormatException            when user did not input a number to choose from menu
     * @throws IllegalArgumentException
     * @throws Exception                        anything else
     */
    @Override
    public void open() {
        boolean keepRunning = true;
        while (keepRunning) {
            try {
                adminMenu.printMenu();
                int input = Integer.parseInt(scanner.nextLine());
                switch (input) {
                    case 1 -> adminMenu.showAllCustomers();
                    case 2 -> adminMenu.showAllRooms();
                    case 3 -> adminMenu.showAllReservations();
                    case 4 -> adminMenu.addARoom();
                    case 5 -> {
                        consolePrinter.print("Returning to the main menu...");
                        keepRunning = false;
                    }
                    default -> consolePrinter.print("Please enter a number representing a menu option from above.");
                }
            } catch (NumberFormatException ex) {
                consolePrinter.print("Please enter a number.");
            } catch (IllegalArgumentException ex) {
                consolePrinter.print(ex.getLocalizedMessage());
            } catch (Exception ex) {
                consolePrinter.print("Unknown error occurred.");
                consolePrinter.print(ex.getLocalizedMessage());
            }
        }
    }
}
