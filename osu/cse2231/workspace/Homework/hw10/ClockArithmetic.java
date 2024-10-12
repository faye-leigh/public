

import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * OSU CSE 2231 HW #10. Static method mod that computes the modulo function
 * using clock arithmetic.
 *
 * @author Faye Leigh
 */
public final class ClockArithmetic {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ClockArithmetic() {
    }

    /**
     * Computes {@code a} mod {@code b} as % should have been defined to work.
     *
     * @param a
     *            the number being reduced
     * @param b
     *            the modulus
     * @return the result of a mod b, which satisfies 0 <= {@code mod} < b
     * @requires b > 0
     * @ensures <pre>
     * 0 <= mod  and  mod < b  and
     * there exists k: integer (a = k * b + mod)
     * </pre>
     */
    public static int mod(int a, int b) {
        int mod = a;
        if (a > 0) {
            while (mod >= b) {
                mod -= b;
            }
        } else if (a < 0) {
            while (mod < 0) {
                mod += b;
            }
        }
        return mod;
    }

    /**
     * Returns the last digit of the given {@code int}.
     *
     * @param a
     *            the given number
     * @return the last digit of a
     * @ensures <pre>
     * lastDigit = [last digit of a]
     * </pre>
     */
    public static int lastDigit(int a) {
        final int ten = 10;
        int lastDigit;
        if (a >= 0) {
            lastDigit = a % ten;
        } else {
            lastDigit = -a % ten;
        }
        return lastDigit;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        out.println(mod(1, 1));
        out.println(lastDigit(2));
        out.close();
    }

}
