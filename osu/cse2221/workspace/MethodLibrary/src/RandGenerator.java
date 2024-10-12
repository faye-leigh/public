import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Collection of random generators.
 *
 * @author Faye Leigh
 */
public final class RandGenerator {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private RandGenerator() {
    }

    /**
     * Tests whether a double is 0.
     *
     * @param n
     *            Number to be tested
     * @return boolean
     */
    private static boolean isZero(double n) {
        final double eps = 1E-12;
        return Math.abs(eps) < eps;
    }

    /**
     * Generates a random integer between a lower and upper boundary. Can return
     * negative integers.
     *
     * @param lowBound
     *            Lowest value the method can return
     * @param highBound
     *            Highest value method can return
     * @return Random integer in the given range
     */
    private static int randInt(int lowBound, int highBound) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        Random rnd = new Random1L(); //Create pseudo-random number generator
        int n = 0, range = 0;

        range = highBound - lowBound;

        /*
         * If low and high bound are both 0, return 0
         */
        if (lowBound == 0 && highBound == 0) {
            n = 0;

            /*
             * If low bound is negative and high bound is positive, find the
             * ratio of negative numbers to total. Use this ratio to generate a
             * negative or positive integer.
             */
        } else if (lowBound < 0 && highBound > 0) {
            double ratio = (double) lowBound / range * -1;
            double posOrNeg = rnd.nextDouble();

            if (posOrNeg > ratio) {
                n = (int) Math.round(rnd.nextDouble() * highBound);
            } else {
                n = (int) Math.round(rnd.nextDouble() * lowBound);
            }

            /*
             * If low bound is positive, generate a number between 0 and range,
             * then add the low bound number.
             */
        } else if (lowBound > 0 && highBound > 0) {
            n = lowBound + (int) Math.round(rnd.nextDouble() * range);

            /*
             * Same as case above but negative.
             */
        } else if (lowBound < 0 && highBound < 0) {
            n = lowBound + (int) Math.round(rnd.nextDouble() * range * -1);

            /*
             * If low bound is 0 and high bound is positive, use high bound.
             */
        } else if (lowBound == 0 && highBound > 0) {
            n = (int) Math.round(rnd.nextDouble() * highBound);

            /*
             * If low bound is negative and high bound is 0, use low bound.
             */
        } else if (lowBound < 0 && highBound == 0) {
            n = (int) Math.round(rnd.nextDouble() * lowBound);
        }

        in.close();
        out.close();
        return n;
    }

    /**
     * Generates a random double between a lower and upper boundary. Can return
     * negative numbers.
     *
     * @param lowBound
     *            Lowest value the method can return
     * @param highBound
     *            Highest value method can return
     * @return Random double in the given range
     */
    private static double randDouble(double lowBound, double highBound) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        Random rnd = new Random1L(); //Create pseudo-random number generator
        double n = 0, range = 0;

        range = highBound - lowBound;

        /*
         * If low and high bound are both 0, return 0
         */
        if (isZero(lowBound) && isZero(highBound)) {
            n = 0;

            /*
             * If low bound is negative and high bound is positive, find the
             * ratio of negative numbers to total. Use this ratio to generate a
             * negative or positive number.
             */
        } else if (lowBound < 0 && highBound > 0) {
            double ratio = lowBound / range * -1;
            double posOrNeg = rnd.nextDouble();

            if (posOrNeg > ratio) {
                n = rnd.nextDouble() * highBound;
            } else {
                n = rnd.nextDouble() * lowBound;
            }

            /*
             * If low bound is positive, generate a number between 0 and range,
             * then add the low bound number.
             */
        } else if (lowBound > 0 && highBound > 0) {
            n = lowBound + rnd.nextDouble() * range;

            /*
             * Same as case above but negative.
             */
        } else if (lowBound < 0 && highBound < 0) {
            n = lowBound + rnd.nextDouble() * range * -1;

            /*
             * If low bound is 0 and high bound is positive, use high bound.
             */
        } else if (lowBound == 0 && highBound > 0) {
            n = rnd.nextDouble() * highBound;

            /*
             * If low bound is negative and high bound is 0, use low bound.
             */
        } else if (lowBound < 0 && highBound == 0) {
            n = rnd.nextDouble() * lowBound;
        }

        in.close();
        out.close();
        return n;
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

        randInt(0, 2);
        randDouble(0, 2);

        in.close();
        out.close();
    }

}
