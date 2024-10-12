package homework.hw14;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * CSE 2221 HW #14.
 *
 * @author Faye Leigh
 *
 */
public final class TestPlan {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private TestPlan() {
    }

    /**
     * Test low bound of possible inputs. <pre>
     * Input: n = 0, n to remain unchanged after method call
     * Output: s = "0" </pre>
     */
    @Test
    private void testLow() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        String s = toStringWithCommas(n);
        String sExpected = "0";
        assertEquals(nExpected, n);
        assertEquals(sExpected, s);
    }

    /**
     * Test high bound of possible inputs (greater than max int value). <pre>
    * Input: n = 2147483648, n to remain unchanged
    * after method call
    * Output: s = "2,147,483,648" </pre>
     */
    @Test
    private void testHigh() {
        NaturalNumber n = new NaturalNumber2("2147483648");
        NaturalNumber nExpected = new NaturalNumber2("2147483648");
        String s = toStringWithCommas(n);
        String sExpected = "2,147,483,648";
        assertEquals(nExpected, n);
        assertEquals(sExpected, s);
    }

    /**
     * Test routine input (no commas). <pre>
    * Input: n = 314, n to remain unchanged after method call
    * Output: s = "314" </pre>
     */
    @Test
    private void testRoutine1() {
        final int input = 314;
        NaturalNumber n = new NaturalNumber2(input);
        NaturalNumber nExpected = new NaturalNumber2(input);
        String s = toStringWithCommas(n);
        String sExpected = "314";
        assertEquals(nExpected, n);
        assertEquals(sExpected, s);
    }

    /**
     * Test routine input (1 comma). <pre>
    * Input: n = 31415, n to remain unchanged after method call
    * Output: s = "31,415" </pre>
     */
    @Test
    private void testRoutine2() {
        final int input = 31415;
        NaturalNumber n = new NaturalNumber2(input);
        NaturalNumber nExpected = new NaturalNumber2(input);
        String s = toStringWithCommas(n);
        String sExpected = "31,415";
        assertEquals(nExpected, n);
        assertEquals(sExpected, s);
    }

    /**
     * Test challenging input (1000). <pre>
    * Input: n = 1000, n to remain unchanged after method call
    * Output: s = "1,000" </pre>
     */
    @Test
    private void testChallenge() {
        final int input = 1000;
        NaturalNumber n = new NaturalNumber2(input);
        NaturalNumber nExpected = new NaturalNumber2(input);
        String s = toStringWithCommas(n);
        String sExpected = "1,000";
        assertEquals(nExpected, n);
        assertEquals(sExpected, s);
    }

    /**
     * Converts the given {@code NaturalNumber} to a {@code String} with commas.
     *
     * @param n
     *            the number
     * @return the {@code String} with commas
     * @ensures toStringWithCommas = [String representation of n with commas]
     */
    public static String toStringWithCommas(NaturalNumber n) {
        return "";
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {

    }

}
