import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 *
 */
public class NNMethodsPowNNTest {
    /**
     * Constant: integer 11.
     */
    private static final int ELEVEN = 11;
    /**
     * Constant: integer 12.
     */
    private static final int TWELVE = 12;
    /**
     * Constant: integer 16.
     */
    private static final int SIXTEEN = 16;
    /**
     * Constant: integer 2^11 = 2048.
     */
    private static final int TWO_TO_ELEVEN = 2048;
    /**
     * Constant: integer 2^12 = 4096.
     */
    private static final int TWO_TO_TWELVE = 4096;
    /**
     * Constant: integer 2^16 = 65536.
     */
    private static final int TWO_TO_SIXTEEN = 65536;

    /**
    *
    */
    @Test
    public void testPowNNZero() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NNMethods.powNN(n, p);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
    }

    /**
    *
    */
    @Test
    public void testPowNNOne() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        NaturalNumber p = new NaturalNumber2(1);
        NaturalNumber pExpected = new NaturalNumber2(1);
        NNMethods.powNN(n, p);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
    }

    /**
    *
    */
    @Test
    public void testPowNNTwos() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(TWO_TO_SIXTEEN);
        NaturalNumber p = new NaturalNumber2(SIXTEEN);
        NaturalNumber pExpected = new NaturalNumber2(SIXTEEN);
        NNMethods.powNN(n, p);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
    }

    /**
    *
    */
    @Test
    public void testPowNNEven() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(TWO_TO_TWELVE);
        NaturalNumber p = new NaturalNumber2(TWELVE);
        NaturalNumber pExpected = new NaturalNumber2(TWELVE);
        NNMethods.powNN(n, p);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
    }

    /**
    *
    */
    @Test
    public void testPowNNOdd() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(TWO_TO_ELEVEN);
        NaturalNumber p = new NaturalNumber2(ELEVEN);
        NaturalNumber pExpected = new NaturalNumber2(ELEVEN);
        NNMethods.powNN(n, p);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
    }
}
