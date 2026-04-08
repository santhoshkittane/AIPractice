import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Simple program that prints the current date and time.
 */
public class DateTime {

    public static void main(String[] args) {
        // Use java.time (available in Java 8+) to get current date/time with zone
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

        System.out.println("Current date and time: " + now.format(fmt));
    }

}
