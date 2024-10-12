package homework.hw10;
import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * CSE 2221 Homework #10.
 *
 * @author Faye Leigh
 *
 */
public final class Swapping {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Swapping() {
    }

    /**
     * Swaps the two given {@code NaturalNumber}s.
     *
     * @param n1
     *            the first {@code NaturalNumber}
     * @param n2
     *            the second {@code NaturalNumber}
     * @updates n1
     * @updates n2
     * @ensures n1 = #n2 and n2 = #n1
     */
    private static void swapNN(NaturalNumber n1, NaturalNumber n2) {
        NaturalNumber tmp = new NaturalNumber2(n1);
        n1.copyFrom(n2);
        n2.copyFrom(tmp);
    }

    /**
     * Swaps the two given {@code NaturalNumber}s.
     *
     * @param n1
     *            the first {@code NaturalNumber}
     * @param n2
     *            the second {@code NaturalNumber}
     * @updates n1
     * @updates n2
     * @ensures n1 = #n2 and n2 = #n1
     */
    private static void transferNN(NaturalNumber n1, NaturalNumber n2) {
        NaturalNumber tmp = new NaturalNumber2(n1);
        n1.transferFrom(n2);
        n2.transferFrom(tmp);
    }

    /**
     * Squares a given {@code NaturalNumber}.
     *
     * @param n
     *            the number to square
     * @updates n
     * @ensures n = #n * #n
     */
    private static void square(NaturalNumber n) {
        NaturalNumber tmp = new NaturalNumber2(n);
        n.multiply(tmp);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        NaturalNumber a = new NaturalNumber2(1), b = new NaturalNumber2(2);
        out.println(a + " " + b);
        swapNN(a, b);
        out.println(a + " " + b);

        NaturalNumber c = new NaturalNumber2(1), d = new NaturalNumber2(2);
        out.println(c + " " + d);
        transferNN(c, d);
        out.println(c + " " + d);

        NaturalNumber e = new NaturalNumber2(2);
        out.println(e);
        square(e);
        out.println(e);

        out.close();
    }

}
