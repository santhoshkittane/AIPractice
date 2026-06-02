/**
 * Simple utility class to accumulate numeric values.
 *
 * Usage examples:
 *  - Instance usage:
 *      Adder a = new Adder();
 *      a.add(1.5);
 *      a.addAll(2, 3.25);
 *      double total = a.getTotal();
 *
 *  - Static convenience method:
 *      double s = Adder.sum(1, 2, 3.5);
 *
 * Command-line demo (main): pass numbers as arguments and it prints the sum.
 */
public class Adder {

    private double total;

    /**
     * Creates a new Adder with total initialized to 0.
     */
    public Adder() {
        this.total = 0.0;
    }

    /**
     * Adds a single number to the accumulator.
     * @param value number to add
     */
    public void add(double value) {
        this.total += value;
    }

    /**
     * Adds multiple numbers to the accumulator.
     * @param values numbers to add
     */
    public void addAll(double... values) {
        for (double v : values) {
            this.total += v;
        }
    }

    /**
     * Returns the current accumulated total.
     * @return the total
     */
    public double getTotal() {
        return this.total;
    }

    /**
     * Resets the accumulator to zero.
     */
    public void reset() {
        this.total = 0.0;
    }

    /**
     * Convenience static method to sum a list of numbers without creating an Adder.
     * @param values numbers to sum
     * @return sum of values
     */
    public static double sum(double... values) {
        double s = 0.0;
        for (double v : values) s += v;
        return s;
    }

    /**
     * Demo main: parse command-line arguments as numbers and print the sum.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Adder <num1> <num2> ...");
            System.out.println("Example: java Adder 1 2.5 3");
            return;
        }

        Adder a = new Adder();
        for (String arg : args) {
            try {
                double v = Double.parseDouble(arg);
                a.add(v);
            } catch (NumberFormatException nfe) {
                System.out.println("Warning: skipping non-numeric argument '" + arg + "'");
            }
        }

        System.out.println("Sum: " + a.getTotal());
    }

}
