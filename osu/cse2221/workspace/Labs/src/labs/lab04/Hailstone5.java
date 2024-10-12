package labs.lab04;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * CSE 2221 Lab #4.
 *
 * @author Faye Leigh
 */
public final class Hailstone5 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone5() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * integer. Also counts the length of the series and finds maximum value.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     */
    private static void generateSeries(int n, SimpleWriter out) {
        int x = n, seriesLength = 1, max = 0;
        final int three = 3;

        out.print(x);
        while (x != 1) {
            if (x > max) {
                max = x;
            }
            if (x % 2 != 0) {
                x = x * three + 1;
            } else {
                x /= 2;
            }
            out.print(", " + x);
            seriesLength++;
        }
        out.println();
        out.println("Series length: " + seriesLength);
        out.println("Max value: " + max);
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
    private static int getPositiveInteger(SimpleReader in, SimpleWriter out) {
        int output = 0;
        boolean flag = true;
        String input;

        while (flag) {
            out.println("Please enter a positive integer");
            input = in.nextLine();
            if (FormatChecker.canParseInt(input)) {
                output = Integer.parseInt(input);
                if (output > 0) {
                    flag = false;
                } else {
                    out.println("Please enter only positive integers");
                    out.println("(e.g. 1, 2, 3, ...)");
                }
            } else {
                out.println("Please enter only positive integers");
                out.println("(e.g. 1, 2, 3, ...)");
            }
        }

        return output;
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

        boolean flag = true;

        while (flag) {
            int n = getPositiveInteger(in, out);
            if (n == 1) {
                out.println(n);
                out.println();
                out.println("Series length: 1");
                out.println("Max value: 1");
            } else {
                generateSeries(n, out);
            }
            out.println();
            out.println("Would you like to enter another number?");
            String yn = in.nextLine();
            if (!yn.equals("y")) {
                flag = false;
            }
        }
        in.close();
        out.close();
    }
}
