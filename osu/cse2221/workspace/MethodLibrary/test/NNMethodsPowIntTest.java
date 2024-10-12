import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 *
 */
public class NNMethodsPowIntTest {
    /**
     * Constant: integer 4.
     */
    private static final int FOUR = 4;
    /**
     * Constant: integer 5.
     */
    private static final int FIVE = 5;
    /**
     * Constant: integer 6.
     */
    private static final int SIX = 6;
    /**
     * Constant: integer 16.
     */
    private static final int SIXTEEN = 16;
    /**
     * Constant: integer 32.
     */
    private static final int THIRTY_TWO = 32;
    /**
     * Constant: integer 64.
     */
    private static final int SIXTY_FOUR = 64;

    /**
     *
     */
    @Test
    public void testPowIntZero() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NNMethods.powInt(n, 0);
        assertEquals(nExpected, n);
    }

    /**
    *
    */
    @Test
    public void testPowIntOne() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        NNMethods.powInt(n, 1);
        assertEquals(nExpected, n);
    }

    /**
    *
    */
    @Test
    public void testPowIntTwos() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(SIXTEEN);
        NNMethods.powInt(n, FOUR);
        assertEquals(nExpected, n);
    }

    /**
    *
    */
    @Test
    public void testPowIntEven() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(SIXTY_FOUR);
        NNMethods.powInt(n, SIX);
        assertEquals(nExpected, n);
    }

    /**
    *
    */
    @Test
    public void testPowIntOdd() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(THIRTY_TWO);
        NNMethods.powInt(n, FIVE);
        assertEquals(nExpected, n);
    }
}
