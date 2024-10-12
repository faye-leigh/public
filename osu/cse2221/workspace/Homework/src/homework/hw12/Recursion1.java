package homework.hw12;
import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * CSE 2221 HW #12.
 *
 * @author Faye Leigh
 *
 */
public final class Recursion1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Recursion1() {
    }

    /**
     * Returns the number of digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to count
     * @return the number of digits of {@code n}
     * @ensures numberOfDigits = [number of digits of n]
     */
    private static int numberOfDigits(NaturalNumber n) {
        final NaturalNumber ten = new NaturalNumber2(10);
        int digitCount = 1;

        if (n.compareTo(ten) > -1) {
            int digit = n.divideBy10();
            digitCount = numberOfDigits(n) + 1;
            n.multiplyBy10(digit);
        }

        return digitCount;
    }

    /**
     * Returns the sum of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to add
     * @return the sum of the digits of {@code n}
     * @ensures sumOfDigits = [sum of the digits of n]
     */
    private static int sumOfDigits(NaturalNumber n) {
        final NaturalNumber ten = new NaturalNumber2(10);
        int sum = 0;

        if (n.compareTo(ten) > -1) {
            int digit = n.divideBy10();
            sum = sumOfDigits(n) + digit;
            n.multiplyBy10(digit);
        } else {
            sum = n.toInt();
        }

        return sum;
    }

    /**
     * Returns the sum of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to add
     * @return the sum of the digits of {@code n}
     * @ensures sumOfDigits = [sum of the digits of n]
     */
    private static NaturalNumber sumOfDigitsAdd(NaturalNumber n) {
        final NaturalNumber ten = new NaturalNumber2(10);
        NaturalNumber sum = new NaturalNumber2(n);

        if (n.compareTo(ten) > -1) {
            int digit = n.divideBy10();
            sum = sumOfDigitsAdd(n);
            sum.add(new NaturalNumber2(digit));
            n.multiplyBy10(digit);
        }

        return sum;
    }

    /**
     * Divides {@code n} by 2.
     *
     * @param n
     *            {@code NaturalNumber} to be divided
     * @updates n
     *
     * @ensures 2 * n <= #n < 2 * (n + 1)
     */
    private static void divideBy2(NaturalNumber n) {
        final int halfOfTen = 5;
        if (!n.isZero()) {
            int ones = n.divideBy10();
            ones /= 2;
            int tens = n.divideBy10();
            if (tens % 2 != 0) {
                ones += halfOfTen;
            }
            n.multiplyBy10(tens);
            divideBy2(n);
            n.multiplyBy10(ones);
        }
    }

    /**
     * Checks whether a {@code String} is a palindrome.
     *
     * @param s
     *            {@code String} to be checked
     * @return true if {@code s} is a palindrome, false otherwise
     * @ensures isPalindrome = (s = rev(s))
     */
    private static boolean isPalindrome(String s) {
        boolean result = false;
        String middle = "";
        for (int i = 1; i < s.length() - 1; i++) {
            middle += s.charAt(i);
        }

        if (s.length() < 2 || (s.charAt(0) == s.charAt(s.length() - 1)
                && isPalindrome(middle))) {
            result = true;
        }

        return result;
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

        final NaturalNumber test = new NaturalNumber2(12345);
        String palindrome = "stressed desserts";

        out.println("Number before: " + test);
        out.println("Number of digits: " + numberOfDigits(test));
        out.println("Number after: " + test);
        out.println();
        out.println("Number before: " + test);
        out.println("Sum of digits: " + sumOfDigits(test));
        out.println("Number after: " + test);
        out.println();
        out.println("Number before: " + test);
        out.println("Sum of digits: " + sumOfDigitsAdd(test));
        out.println("Number after: " + test);
        out.println();
        out.println("Number before: " + test);
        divideBy2(test);
        out.println("Number after: " + test);
        out.println();
        out.println("String: " + palindrome);
        out.println("Is palindrome: " + isPalindrome(palindrome));

        in.close();
        out.close();
    }

}
