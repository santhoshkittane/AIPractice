import java.util.Scanner;

/**
 * Prints the first 10 multiples of a given integer number.
 *
 * Usage:
 *  - java -cp bin Multiples 5      (uses command-line argument)
 *  - java -cp bin Multiples        (prompts the user to enter a number)
 */
public class Multiples {

    public static void main(String[] args) {
        long n;

        if (args.length > 0) {
            try {
                n = Long.parseLong(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid number provided: " + args[0]);
                return;
            }
        } else {
            System.out.print("Enter a number: ");
            Scanner sc = new Scanner(System.in);
            if (!sc.hasNextLong()) {
                System.err.println("Invalid input. Please enter an integer.");
                sc.close();
                return;
            }
            n = sc.nextLong();
            sc.close();
        }

        System.out.println("First 10 multiples of " + n + ":");
        for (int i = 1; i <= 10; i++) {
            System.out.println(n + " x " + i + " = " + (n * i));
        }
    }

}
