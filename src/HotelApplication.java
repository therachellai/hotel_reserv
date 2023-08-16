
import api.AdminResource;
import api.HotelResource;
import service.CustomerService;
import service.ReservationService;
import model.ReservationFactory;
import ui.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public final class HotelApplication {


    public static void main(String[] args) {
        // Instantiate classes
        CustomerService customerService = CustomerService.getInstance();
        ReservationService reservationService = ReservationService.getInstance(new ReservationFactory());
        AdminResource adminResource = new AdminResource(customerService, reservationService);
        Scanner scanner = new Scanner(System.in);
        DateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        ConsolePrinter consolePrinter = new ConsolePrinterImpl();
        AdminMenu adminMenuService = new AdminMenu(adminResource, scanner, consolePrinter);
        MenuManager adminMenuManager = new AdminMenuManager(scanner, adminMenuService, consolePrinter);
        HotelResource hotelResource = new HotelResource(customerService,
                reservationService);
        Date now = new Date();
        MainMenu mainMenuService = new MainMenu(now, hotelResource, scanner, simpleDateFormat,
                consolePrinter);
        MenuManager mainMenuManager = new MainMenuManager(adminMenuManager, mainMenuService, scanner,
                consolePrinter);

        // Run the app
        mainMenuManager.open();
    }
}
