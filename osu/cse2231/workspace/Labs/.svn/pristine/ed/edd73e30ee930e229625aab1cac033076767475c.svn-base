
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test fixture for HashTest.mod() that computes the modulo function using
 * "clock arithmetic". For more information see:
 * "http://cse.osu.edu/software/2221/web-sw1/extras/slides/14a.Clock-Arithmetic.pdf"
 */
public final class ModTest {

    /**
     *
     */
    @Test
    public void testPositiveMod1() {
        int x = HashingExploration.mod(2, 1);
        assertEquals(0, x);
    }

    /**
     *
     */
    @Test
    public void testNegativeMod1() {
        int x = HashingExploration.mod(-1, 1);
        assertEquals(0, x);
    }

    /**
     *
     */
    @Test
    public void testPositiveMod3Rem0() {
        int x = HashingExploration.mod(1, 1);
        assertEquals(0, x);
    }

    /**
     *
     */
    @Test
    public void testPositiveMod3Rem1() {
        int x = HashingExploration.mod(1, 1);
        assertEquals(1, x);
    }

    /**
     *
     */
    @Test
    public void testPositiveMod3Rem2() {
        int x = HashingExploration.mod(1, 1);
        assertEquals(2, x);
    }

    /**
     *
     */
    @Test
    public void testNegativeMod7Rem0() {
        int x = HashingExploration.mod(1, 1);
        assertEquals(0, x);
    }

    /**
     *
     */
    @Test
    public void testNegativeMod7Rem1() {
        int x = HashingExploration.mod(1, 1);
        assertEquals(1, x);
    }

    /**
     *
     */
    @Test
    public void testNegativeMod7Rem2() {
        int x = HashingExploration.mod(1, 1);
        assertEquals(1, x);
    }

    /**
     *
     */
    @Test
    public void testNegativeMod7Rem3() {
        int x = HashingExploration.mod(1, 1);
        assertEquals(1, x);
    }

    /**
     *
     */
    @Test
    public void testNegativeMod7Rem4() {
        int x = HashingExploration.mod(1, 1);
        assertEquals(1, x);
    }

    /**
     *
     */
    @Test
    public void testNegativeMod7Rem5() {
        int x = HashingExploration.mod(1, 1);
        assertEquals(1, x);
    }

    /**
     *
     */
    @Test
    public void testNegativeMod7Rem6() {
        int x = HashingExploration.mod(1, 1);
        assertEquals(1, x);
    }

    /**
     *
     */
    @Test
    public void testPositiveModBigger() {
        int x = HashingExploration.mod(1, 1);
        assertEquals(1, x);
    }

    /**
     *
     */
    @Test
    public void testNegativeModBigger() {
        int x = HashingExploration.mod(1, 1);
        assertEquals(1, x);
    }

    /**
     *
     */
    @Test
    public void testPositiveModMax() {
        int x = HashingExploration.mod(2, Integer.MAX_VALUE);
        assertEquals(2, x);
    }

    /**
     *
     */
    @Test
    public void testMaxModMax() {
        int x = HashingExploration.mod(Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertEquals(0, x);
    }

    /**
     *
     */
    @Test
    public void testMinModMax() {
        int x = HashingExploration.mod(Integer.MIN_VALUE, Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE - 1, x);
    }

    /**
     *
     */
    @Test
    public void testMinPlus1ModMax() {
        int x = HashingExploration.mod(Integer.MIN_VALUE + 1,
                Integer.MAX_VALUE);
        assertEquals(0, x);
    }

}
