import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Collection of input validation methods.
 *
 * @author Faye Leigh
 *
 */
public final class InputValidation {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private InputValidation() {
    }

    /**
     * Repeatedly asks the user for an integer until the user enters one.
     * Returns the positive integer.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return an integer entered by the user
     */
    private static int getInt(SimpleReader in, SimpleWriter out) {
        int output = 0;
        boolean flag = true;
        String input;

        /*
         * Run until user input satisfies conditions
         */
        while (flag) {
            out.print("Please enter a integer: ");
            input = in.nextLine();

            /*
             * Checks that input contains an integer
             */
            if (FormatChecker.canParseInt(input)) {
                output = Integer.parseInt(input);
                flag = false;
            } else {
                out.println("Input was not an integer.");
            }
        }
        return output;
    }

    /**
     * Repeatedly asks the user for a positive integer until the user enters
     * one. Returns the positive integer.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive integer entered by the user
     */
    private static int getPosInt(SimpleReader in, SimpleWriter out) {
        int output = 0;
        boolean flag = true;
        String input;

        /*
         * Run until user input satisfies conditions
         */
        while (flag) {
            out.print("Please enter a positive integer: ");
            input = in.nextLine();

            /*
             * Checks that input contains an integer
             */
            if (FormatChecker.canParseInt(input)) {
                output = Integer.parseInt(input);
                if (output > 0) {
                    flag = false;
                } else {
                    out.println("Input was not a positive integer.");
                }
            } else {
                out.println("Input was not an integer.");
            }
        }
        return output;
    }

    /**
     * Repeatedly asks the user for a real number until the user enters one.
     * Returns the real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a real number entered by the user
     */
    private static double getDouble(SimpleReader in, SimpleWriter out) {
        double output = 0.0;
        boolean flag = true;
        String input;

        /*
         * Run until user input satisfies conditions
         */
        while (flag) {
            out.print("Please enter a number: ");
            input = in.nextLine();

            /*
             * Checks that input contains a number
             */
            if (FormatChecker.canParseDouble(input)) {
                output = Double.parseDouble(input);
                flag = false;
            } else {
                out.println("Input was not an number.");
            }
        }

        return output;
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPosDouble(SimpleReader in, SimpleWriter out) {
        double output = 0.0;
        boolean flag = true;
        String input;

        /*
         * Run until user input satisfies conditions
         */
        while (flag) {
            out.print("Please enter a positive number: ");
            input = in.nextLine();

            /*
             * Checks that input contains a number and is positive
             */
            if (FormatChecker.canParseDouble(input)) {
                output = Double.parseDouble(input);
                if (output > 0) {
                    flag = false;
                } else {
                    out.println("Number was not positive.");
                }
            } else {
                out.println("Input was not an number.");
            }
        }
        return output;
    }

    /**
     * Tests whether a double is 0. Returns a boolean.
     *
     * @param n
     *            number to be tested
     * @return true if input is 0, false otherwise
     */
    private static boolean isZero(double n) {
        final double eps = 1E-12;
        return Math.abs(eps) < eps;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        getInt(in, out);
        getPosInt(in, out);
        getDouble(in, out);
        getPosDouble(in, out);
        if (isZero(1)) {
            out.println();
        }

        in.close();
        out.close();
    }

}
