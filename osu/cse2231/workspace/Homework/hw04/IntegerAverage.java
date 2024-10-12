
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * OSU CSE 2231 HW #4. Implementations of an integer average method.
 *
 * @author Faye Leigh
 *
 */
public final class IntegerAverage {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private IntegerAverage() {
    }

    /*
     * 1. The average of two values will never be more than the larger value or
     * less than the smaller value. Since the values of j and k will never
     * exceed Integer.MAX_VALUE or Integer.MIN_VALUE, the average value will
     * also never exceed those bounds. Thus, any valid integer inputs will
     * always result in a valid integer output.
     */

    /**
     * 2. Returns the integer average of two given {@code int}s.
     *
     * @param j
     *            the first of two integers to average
     * @param k
     *            the second of two integers to average
     * @return the integer average of j and k
     * @ensures average = (j+k)/2
     */
    @SuppressWarnings("unused")
    private static int average(int j, int k) {
        int avg = 0;

        if (j == k) {
            avg = j;
        } else if (j >= 0 && k >= 0) {
            if (j >= k) {
                avg = (j - k) / 2 + k;
            } else {
                avg = (k - j) / 2 + j;
            }
        } else if (j < 0 && k < 0) {
            if (j >= k) {
                avg = j - (j - k) / 2;
            } else {
                avg = k - (k - j) / 2;
            }
        } else {
            avg = (j + k) / 2;
        }

        return avg;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
//
//        int a = Integer.MAX_VALUE;
//        int b = Integer.MAX_VALUE - 1;
//        out.println("a:   " + a);
//        out.println("b:   " + b);
//        out.println("avg: " + average(a, b));
//        a = Integer.MIN_VALUE;
//        b = Integer.MIN_VALUE + 1;
//        out.println("a:   " + a);
//        out.println("b:   " + b);
//        out.println("avg: " + average(a, b));
//        a = Integer.MIN_VALUE;
//        b = Integer.MIN_VALUE;
//        out.println("a:   " + a);
//        out.println("b:   " + b);
//        out.println("avg: " + average(a, b));
//        a = Integer.MAX_VALUE;
//        b = Integer.MAX_VALUE;
//        out.println("a:   " + a);
//        out.println("b:   " + b);
//        out.println("avg: " + average(a, b));
//        a = 5;
//        b = 8;
//        out.println("a:   " + a);
//        out.println("b:   " + b);
//        out.println("avg: " + average(a, b));
//        a = -5;
//        b = -8;
//        out.println("a:   " + a);
//        out.println("b:   " + b);
//        out.println("avg: " + average(a, b));
//        a = 11;
//        b = -4;
//        out.println("a:   " + a);
//        out.println("b:   " + b);
//        out.println("avg: " + average(a, b));
//        a = -3;
//        b = 2;
//        out.println("a:   " + a);
//        out.println("b:   " + b);
//        out.println("avg: " + average(a, b));
//        a = 3;
//        b = 5;
//        out.println("a:   " + a);
//        out.println("b:   " + b);
//        out.println("avg: " + average(a, b));
//        a = -3;
//        b = -5;
//        out.println("a:   " + a);
//        out.println("b:   " + b);
//        out.println("avg: " + average(a, b));
//        a = Integer.MAX_VALUE;
//        b = Integer.MIN_VALUE;
//        out.println("a:   " + a);
//        out.println("b:   " + b);
//        out.println("avg: " + average(a, b));

        out.close();
    }

}
