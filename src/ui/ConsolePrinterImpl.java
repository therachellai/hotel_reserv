package ui;

/**
 * Prints to console
 *
 * @author rachellai
 */
public final class ConsolePrinterImpl implements ConsolePrinter {

    /**
     * Prints to console
     *
     * @param text  object to print
     * @param <T>   type of object
     */
    @Override
    public <T> void print(T text) {
        System.out.println(text);
    }
}
