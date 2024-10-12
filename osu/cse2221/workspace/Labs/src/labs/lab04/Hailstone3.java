package labs.lab04;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * CSE 2221 Lab #4.
 *
 * @author Faye Leigh
 */
public final class Hailstone3 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone3() {
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
        int x = n, seriesLength = 0, max = 0;
        final int three = 3;

        out.print(x + ", ");
        while (x != 1) {
            if (x > max) {
                max = x;
            }
            if (x % 2 != 0) {
                x = x * three + 1;
            } else {
                x /= 2;
            }
            out.print(x + ", ");
            seriesLength++;
        }
        out.println();
        out.println("Series length: " + seriesLength);
        out.println("Max value: " + max);
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

        out.print("Enter any positive integer: ");
        int n = in.nextInteger();
        generateSeries(n, out);

        in.close();
        out.close();
    }
}
