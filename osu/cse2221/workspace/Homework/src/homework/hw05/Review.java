package homework.hw05;
import java.util.Arrays;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * CSE 2221 Homework 5.
 *
 * @author Faye Leigh
 *
 */
public final class Review {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Review() {
    }

    /**
     * 3. (a)
     *
     * @return sum of even numbers 2-100
     */
    private static int hw53a() {
        final int hundred = 100;
        int n = 2, sum = 0;
        while (n <= hundred) {
            sum += n;
            n += 2;
        }
        return sum;
    }

    /**
     * 3. (b)
     *
     * @return sum of squares 1-100
     */
    private static int hw53b() {
        final int hundred = 100;
        int n = 1, sum = 0;
        while (n * n <= hundred) {
            sum += n * n;
            n++;
        }
        return sum;
    }

    /**
     * 3. (c)
     *
     * @param out
     */
    private static void hw53c(SimpleWriter out) {
        final int twenty = 20;
        int n = 0;
        double x = 0;
        while (n <= twenty) {
            x = Math.pow(2.0, n);
            out.println(x);
            n++;
        }
    }

    /**
     * 3. (d)
     *
     * @return sum of odd numbers between a and b
     */
    private static int hw53d() {
        int a = 0, b = 0, sum = 0;
        if (a % 2 == 0) {
            a++;
        }
        while (a <= b) {
            sum += a;
            a += 2;
        }
        return sum;
    }

    /**
     * 3. (e)
     *
     * sum of all digits at odd positions right-to-left
     *
     * @param out
     */
    private static void hw53e(SimpleWriter out) {
        final int ten = 10;
        int input = 2, sum = 0, i = 0,
                length = Integer.toString(input).length();
        int[] output = new int[length];

        while (input != 0) {
            output[i] = input % ten;
            input /= ten;
            i++;
        }

        for (int j = 0; j < length; j += 2) {
            sum += output[j];
        }
        out.println(sum);
        out.println(Arrays.toString(output));
    }

    /**
     * 3. (f)
     *
     * sum of all digits at odd positions left-to-right
     *
     * @param out
     */
    private static void hw53f(SimpleWriter out) {
        final int ten = 10;
        int input = 2, sum = 0, length = Integer.toString(input).length();
        int[] output = new int[length];

        int i = length - 1;
        while (input != 0) {
            output[i] = input % ten;
            input /= ten;
            i--;
        }

        for (int j = 0; j < length; j += 2) {
            sum += output[j];
        }

        out.println(sum);
        out.println(Arrays.toString(output));
    }

    /*
     * 4.
     */

    /**
     * .
     *
     * @param x
     * @param y
     * @return .
     */
    private static int largerOf(int x, int y) {
        return 0;
    }

    /**
     * .
     *
     * @param x
     * @param y
     * @param z
     * @return .
     */
    private static int smallerOf(int x, int y, int z) {
        return 0;
    }

    /**
     * .
     *
     * @param n
     * @return .
     */
    private static boolean isPrime(int n) {
        return true;
    }

    /**
     * .
     *
     * @param inside
     * @param outside
     * @return .
     */
    private static boolean isStringInString(String inside, String outside) {
        return true;
    }

    /**
     * .
     *
     * @param initialBalance
     * @param interest
     * @param years
     * @return .
     */
    private static double balance(double initialBalance, double interest,
            double years) {
        return 0;
    }

    /**
     * .
     *
     * @param initialBalance
     * @param interest
     * @param years
     * @param out
     */
    private static void balance(double initialBalance, double interest,
            double years, SimpleWriter out) {
    }

    /**
     * .
     *
     * @param month
     * @param year
     * @param out
     */
    private static void calendar(int month, int year, SimpleWriter out) {
    }

    /**
     * .
     *
     * @param day
     * @param month
     * @param year
     * @return .
     */
    private static String dayOfWeek(int day, int month, int year) {
        return "";
    }

    /**
     * .
     *
     * @param n
     * @return .
     */
    private static int randInt(int n) {
        return 0;
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

        out.print(hw53a());
        out.print(hw53b());
        hw53c(out);
        out.print(hw53d());
        hw53e(out);
        hw53f(out);

        int x = 0, y = 0, z = 0, n = 0, day = 0, month = 0, year = 0;
        double initialBalance = 0, interest = 0, years = 0;
        String inside = "", outside = "";

        out.print(largerOf(x, y));

        out.print(smallerOf(x, y, z));

        out.print(isPrime(n));

        out.print(isStringInString(inside, outside));

        out.print(balance(initialBalance, interest, years));

        balance(initialBalance, interest, years, out);

        calendar(month, year, out);

        out.print(dayOfWeek(day, month, year));

        out.print(randInt(n));

        in.close();
        out.close();
    }

}
