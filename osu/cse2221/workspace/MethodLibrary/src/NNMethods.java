import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * CSE 2221 Project #8. Utilities that could be used with RSA cryptosystems.
 *
 * @author Faye Leigh
 */
public final class NNMethods {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NNMethods() {
    }

    /**
     * Swaps n with m.
     *
     * @param n
     *            one number
     * @param m
     *            the other number
     * @replaces n
     * @replaces m
     * @ensures n = #m and m = #n
     */
    public static void swap(NaturalNumber n, NaturalNumber m) {
        NaturalNumber tmp = new NaturalNumber2(n);
        n.transferFrom(m);
        m.transferFrom(tmp);
    }

    /**
     * Reports whether n is even.
     *
     * @param n
     *            the number to be checked
     * @return true iff n is even
     * @ensures isEven = (n mod 2 = 0)
     */
    public static boolean isEven(NaturalNumber n) {
        boolean result = true;
        int digit = n.divideBy10();

        if (digit % 2 != 0) {
            result = false;
        }

        n.multiplyBy10(digit); // Restore n.

        return result;
    }

    /**
     * Updates n to its p-th power where p is an integer.
     *
     * @param n
     *            number to be raised to a power
     * @param p
     *            the power
     * @updates n
     * @requires p >= 0
     * @ensures n = #n ^ p
     */
    public static void powInt(NaturalNumber n, int p) {
        assert p >= 0 : "Violation of: p >= 0";

        if (p == 0) {
            n.setFromInt(1);

        } else if (p > 1) {

            if (p % 2 == 0) {
                powInt(n, p / 2);
                n.multiply(new NaturalNumber2(n));

            } else {
                NaturalNumber nCopy = new NaturalNumber2(n);
                powInt(n, p - 1);
                n.multiply(nCopy);
            }
        }

    }

    /**
     * Updates n to its p-th power where p is a natural number.
     *
     * @param n
     *            number to be raised to a power
     * @param p
     *            the power
     * @updates n
     * @ensures n = #n ^ p
     */
    public static void powNN(NaturalNumber n, NaturalNumber p) {

        /*
         * If p = 0, n = 1. If p = 1, n = #n. If p > 1, n = #n ^ p
         */
        if (p.isZero()) {
            n.setFromInt(1);

        } else if (p.compareTo(new NaturalNumber2(1)) > 0) {

            if (isEven(p)) {
                // If p is even, n^p = (n ^ p/2)^2.
                p.divide(new NaturalNumber2(2));
                powNN(n, p);
                p.multiply(new NaturalNumber2(2));

                n.multiply(new NaturalNumber2(n)); // Restore p.

            } else {
                // If p is odd, n^p = n(n ^ p-1).
                NaturalNumber nCopy = new NaturalNumber2(n);
                p.decrement();
                powNN(n, p);
                n.multiply(nCopy);

                p.increment(); // Restore p.
            }
        }
    }

    /**
     * Updates n to its p-th power modulo m.
     *
     * @param n
     *            number to be raised to a power
     * @param p
     *            the power
     * @param m
     *            the modulus
     * @updates n
     * @requires m > 1
     * @ensures n = #n ^ (p) mod m
     */
    public static void powerMod(NaturalNumber n, NaturalNumber p,
            NaturalNumber m) {
        assert m.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: m > 1";

        /*
         * Use the fast-powering algorithm as previously discussed in class,
         * with the additional feature that every multiplication is followed
         * immediately by "reducing the result modulo m"
         */
        /*
         * If p = 0, n = 1. If p = 1, n = #n. If p > 1, n = (#n ^ p) mod m
         */
        if (p.isZero()) {
            n.setFromInt(1);

        } else if (p.compareTo(new NaturalNumber2(1)) > 0) {

            if (isEven(p)) {
                // If n is even, n = [(#n ^ #p/2) ^ 2] mod m.
                p.divide(new NaturalNumber2(2));
                powerMod(n, p, m);
                n.multiply(new NaturalNumber2(n));

                // Set n to remainder of n / m
                NaturalNumber r = n.divide(m);
                n.transferFrom(r);

                p.multiply(new NaturalNumber2(2)); // Restore P.

            } else {
                // If n is odd, n = [(#n ^ #p-1) * n] mod m/
                NaturalNumber nCopy = new NaturalNumber2(n);
                p.decrement();
                powerMod(n, p, m);
                n.multiply(nCopy);

                // Set n to remainder of n / m
                NaturalNumber r = n.divide(m);
                n.transferFrom(r);

                p.increment(); // Restore p.
            }
        }
    }
}
