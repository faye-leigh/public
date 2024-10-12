import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.naturalnumber.NaturalNumber2;

/**
 *
 */
public class Lab19NNtoStringWithCommasTest {

    /*
     * Test 1: pass
     *
     * Test 2: expected "", was different quotation mark
     *
     * Test 3: expected "0", was ""
     *
     * Test 4: challenge expected "1,000", was "1,0"
     *
     * Test 5: rounding errors
     *
     * Test 6:
     */

    /**
     * Calls the method under test.
     *
     * @param n
     *            the number to pass to the method under test
     * @return the {@code String} returned by the method under test
     * @ensures <pre>
     * redirectToMethodUnderTest = [String returned by the method under test]
     * </pre>
     */
    private static String redirectToMethodUnderTest(NaturalNumber n) {
        return NNtoStringWithCommas6.toStringWithCommas(n);
    }

    /**
     * Test low bound of possible inputs. <pre>
     * Input: n = 0, n to remain unchanged after method call
     * Output: s = "0" </pre>
     */
    @Test
    public void testLow() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        String s = redirectToMethodUnderTest(n);
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
    public void testHigh() {
        NaturalNumber n = new NaturalNumber1L("2147483648");
        NaturalNumber nExpected = new NaturalNumber2("2147483648");
        String s = redirectToMethodUnderTest(n);
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
    public void testRoutine1() {
        final int input = 314;
        NaturalNumber n = new NaturalNumber2(input);
        NaturalNumber nExpected = new NaturalNumber2(input);
        String s = redirectToMethodUnderTest(n);
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
    public void testRoutine2() {
        final int input = 31415;
        NaturalNumber n = new NaturalNumber2(input);
        NaturalNumber nExpected = new NaturalNumber2(input);
        String s = redirectToMethodUnderTest(n);
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
    public void testChallenge() {
        final int input = 1000;
        NaturalNumber n = new NaturalNumber2(input);
        NaturalNumber nExpected = new NaturalNumber2(input);
        String s = redirectToMethodUnderTest(n);
        String sExpected = "1,000";
        assertEquals(nExpected, n);
        assertEquals(sExpected, s);
    }

    /**
     * Test challenging input (1000). <pre>
    * Input: n = 1000, n to remain unchanged after method call
    * Output: s = "1,000" </pre>
     */
    @Test
    public void testNull() {
        final int input = 999;
        NaturalNumber n = new NaturalNumber2(input);
        NaturalNumber nExpected = new NaturalNumber2(input);
        String s = redirectToMethodUnderTest(n);
        String sExpected = "999";
        assertEquals(nExpected, n);
        assertEquals(sExpected, s);
    }
}
