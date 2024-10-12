package homework.hw11;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * CSE 2221 Homework #11.
 *
 * @author Faye Leigh
 *
 */
public final class InternalHalving {

  /**
  * No argument constructor--private to prevent instantiation.
  */
  private InternalHalving() {
  }

    /*
     * QUESTIONS
     *
     * 1. 4^3 is 64, and 5^3 is 125. Since 64 < 82 < 125, cube root of 82 is
     * between 4 and 5, which rounds down to 4
     *
     * 2. No. If root is negative, the root^2 = n, but sqr root of n is not root
     *
     * 3. Sqr root of a number cannot be negative, so g cannot be less than 0.
     * Similarly, the sqr root of a number cannot be larger than the number
     * itself, so g cannot be greater than n
     *
     * 4. Since the square root of a number cannot be negative, the lowEnough
     * value should be 0. Since the sqr root of n cannot be greater than n, the
     * tooHigh value should be n
     *
     * 5. Start with lowEnough = 0, and tooHigh = 47226. Find (tooHigh -
     * lowEnough)/2 = g. Check if g^5 is more or less than 47226. If g^5 is more
     * than 47226, set g = tooHigh. If g^5 is less than 47226, set g =
     * lowEnough. Repeat until tooHigh = lowEnough + 1, at which point lowEnough
     * = 5th root of 47226
     */

    /*
     * 6.
     */

    /**
     * Returns the {@code r}-th root of {@code n}.
     *
     * @param n
     *            the number to which we want to apply the root
     * @param r
     *            the root
     * @return the root of the number
     * @requires n >= 0 and r > 0
     * @ensures root ^ (r) <= n < (root + 1) ^ (r)
     */
    private static int root(int n, int r) {
        int lowEnough = 0, tooHigh = n, g = 0;
        boolean rootFound = false;

        while (!rootFound) {
            g = (tooHigh - lowEnough) / 2 + lowEnough;
            if ((power(g, r) == n)) {
                rootFound = true;
            } else if (power(g, r) > n) {
                tooHigh = g;
            } else {
                lowEnough = g;
            }
            if (tooHigh - lowEnough == 1) {
                g = lowEnough;
                rootFound = true;
            }
        }

        return g;
    }

    /**
     * Returns {@code n} to the power {@code p}.
     *
     * @param n
     *            the number to which we want to apply the power
     * @param p
     *            the power
     * @return the number to the power
     * @requires Integer.MIN_VALUE <= n ^ (p) <= Integer.MAX_VALUE and p >= 0
     * @ensures power = n ^ (p)
     */
    private static int power(int n, int p) {
        return (int) Math.pow(n, p);
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

        out.println(root(2, 1));

        in.close();
        out.close();
    }

}
