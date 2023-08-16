package ui;

import java.util.Scanner;

/**
 * Menu manager for main menu UI
 *
 * @author rachellai
 */
public final class MainMenuManager implements MenuManager {

    private final MenuManager adminMenuManager;
    private final MainMenu mainMenu;
    private final Scanner scanner;
    private final ConsolePrinter consolePrinter;

    /**
     * Constructor
     *
     * @param adminMenuManager  adminMenuManager object
     * @param mainMenu          mainMenu object that serves as a menu with tasks a user can choose from
     * @param scanner           scanner object that reads user input
     * @param consolePrinter    consolePrinter object that prints text to the console
     */
    public MainMenuManager(MenuManager adminMenuManager, MainMenu mainMenu, Scanner scanner,
                           ConsolePrinter consolePrinter) {
        this.adminMenuManager = adminMenuManager;
        this.mainMenu = mainMenu;
        this.scanner = scanner;
        this.consolePrinter = consolePrinter;
    }

    /**
     * Prints the main menu, reads input, and performs actions accordingly.
     * @throws NumberFormatException            when user did not input a number to choose from menu
     * @throws IllegalArgumentException
     * @throws Exception                        anything else
     */
    @Override
    public void open() {
        boolean keepRunning = true;
        while (keepRunning) {
            try {
                mainMenu.printMenu();
                int input = Integer.parseInt(scanner.nextLine());
                switch (input) {
                    case 1 -> mainMenu.findAndReserveARoom();
                    case 2 -> mainMenu.showCustomersReservations();
                    case 3 -> mainMenu.createNewAccount();
                    case 4 -> goToAdminMenu();
                    case 5 -> {
                        consolePrinter.print("Exiting the app...");
                        keepRunning = false;
                        scanner.close();
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

    private void goToAdminMenu() {
        adminMenuManager.open();
    }
}