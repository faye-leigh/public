import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Faye Leigh
 */
public class CryptoUtilitiesTest {

    /*
     * Constants
     */

    /**
     * Odd test value: 7.
     */
    private static final int PRIME = 7;
    /**
     * Even test value: 8.
     */
    private static final int EVEN = 8;
    /**
     * Odd test value: 9.
     */
    private static final int ODD = 9;
    /**
     * Big test value: 2147483648 (Integer.MAX_VALUE + 1).
     */
    private static final String BIG = "2147483648";
    /**
     * Big test value: 2147483648 (Integer.MAX_VALUE + 1).
     */
    private static final String BIGGER = "21474836482872";

    /*
     * Tests of reduceToGCD
     */

    /**
     *
     */
    @Test
    public void testReduceToGCDMin() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /**
     *
     */
    @Test
    public void testReduceToGCDPrimeOdd() {
        NaturalNumber n = new NaturalNumber2(PRIME);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(ODD);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /**
    *
    */
    @Test
    public void testReduceToGCDBig() {
        NaturalNumber n = new NaturalNumber2(BIG);
        NaturalNumber nExpected = new NaturalNumber2("8");
        NaturalNumber m = new NaturalNumber2(BIGGER);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    /**
     *
     */
    @Test
    public void testIsEvenMin() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /**
     *
     */
    @Test
    public void testIsEvenOne() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /**
    *
    */
    @Test
    public void testIsEvenBig() {
        NaturalNumber n = new NaturalNumber2(BIG);
        NaturalNumber nExpected = new NaturalNumber2(BIG);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /*
     * Tests of powerMod
     */

    /**
     *
     */
    @Test
    public void testPowerModMin() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /**
     *
     */
    @Test
    public void testPowerModPrimeEvenOdd() {
        NaturalNumber n = new NaturalNumber2(PRIME);
        NaturalNumber nExpected = new NaturalNumber2("4");
        NaturalNumber p = new NaturalNumber2(EVEN);
        NaturalNumber pExpected = new NaturalNumber2(EVEN);
        NaturalNumber m = new NaturalNumber2(ODD);
        NaturalNumber mExpected = new NaturalNumber2(ODD);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /**
    *
    */
    @Test
    public void testPowerModBig() {
        NaturalNumber n = new NaturalNumber2(BIG);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber p = new NaturalNumber2(BIGGER);
        NaturalNumber pExpected = new NaturalNumber2(BIGGER);
        NaturalNumber m = new NaturalNumber2(BIG);
        NaturalNumber mExpected = new NaturalNumber2(BIG);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of swap
     */

    /**
     *
     */
    @Test
    public void testSwapEven0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(EVEN);
        NaturalNumber m = new NaturalNumber2(EVEN);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.swap(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /**
    *
    */
    @Test
    public void testSwapEvenOdd() {
        NaturalNumber n = new NaturalNumber2(EVEN);
        NaturalNumber nExpected = new NaturalNumber2(ODD);
        NaturalNumber m = new NaturalNumber2(ODD);
        NaturalNumber mExpected = new NaturalNumber2(EVEN);
        CryptoUtilities.swap(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /**
    *
    */
    @Test
    public void testSwapEvenBig() {
        NaturalNumber n = new NaturalNumber2(BIGGER);
        NaturalNumber nExpected = new NaturalNumber2(BIG);
        NaturalNumber m = new NaturalNumber2(BIG);
        NaturalNumber mExpected = new NaturalNumber2(BIGGER);
        CryptoUtilities.swap(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /**
     *
     */
    @Test
    public void testIsWitnessToCompositenessMin() {
        NaturalNumber w = new NaturalNumber2(2);
        NaturalNumber wExpected = new NaturalNumber2(2);
        NaturalNumber n = new NaturalNumber2("4");
        NaturalNumber nExpected = new NaturalNumber2("4");
        boolean r = CryptoUtilities.isWitnessToCompositeness(w, n);
        boolean rExpected = true;
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(rExpected, r);
    }

    /**
    *
    */
    @Test
    public void testIsWitnessToCompositenessFirstPrime() {
        NaturalNumber w = new NaturalNumber2(2);
        NaturalNumber wExpected = new NaturalNumber2(2);
        NaturalNumber n = new NaturalNumber2("5");
        NaturalNumber nExpected = new NaturalNumber2("5");
        boolean r = CryptoUtilities.isWitnessToCompositeness(w, n);
        boolean rExpected = false;
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(rExpected, r);
    }

    /**
    *
    */
    @Test
    public void testIsWitnessToCompositenessEven() {
        NaturalNumber w = new NaturalNumber2(EVEN);
        NaturalNumber wExpected = new NaturalNumber2(EVEN);
        NaturalNumber n = new NaturalNumber2(EVEN * EVEN);
        NaturalNumber nExpected = new NaturalNumber2(EVEN * EVEN);
        boolean r = CryptoUtilities.isWitnessToCompositeness(w, n);
        boolean rExpected = true;
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(rExpected, r);
    }

    /**
    *
    */
    @Test
    public void testIsWitnessToCompositenessOdd() {
        NaturalNumber w = new NaturalNumber2(ODD);
        NaturalNumber wExpected = new NaturalNumber2(ODD);
        NaturalNumber n = new NaturalNumber2(ODD * ODD);
        NaturalNumber nExpected = new NaturalNumber2(ODD * ODD);
        boolean r = CryptoUtilities.isWitnessToCompositeness(w, n);
        boolean rExpected = true;
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(rExpected, r);
    }

    /**
    *
    */
    @Test
    public void testIsWitnessToCompositenessBig() {
        NaturalNumber w = new NaturalNumber2(BIG);
        NaturalNumber wExpected = new NaturalNumber2(BIG);
        NaturalNumber n = new NaturalNumber2(BIGGER);
        NaturalNumber nExpected = new NaturalNumber2(BIGGER);
        boolean r = CryptoUtilities.isWitnessToCompositeness(w, n);
        boolean rExpected = true;
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(rExpected, r);
    }

    /**
    *
    */
    @Test
    public void testIsPrime1Min() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        boolean r = CryptoUtilities.isPrime1(n);
        boolean rExpected = true;
        assertEquals(nExpected, n);
        assertEquals(rExpected, r);
    }

    /**
    *
    */
    @Test
    public void testIsPrime1Even() {
        NaturalNumber n = new NaturalNumber2(EVEN);
        NaturalNumber nExpected = new NaturalNumber2(EVEN);
        boolean r = CryptoUtilities.isPrime1(n);
        boolean rExpected = false;
        assertEquals(nExpected, n);
        assertEquals(rExpected, r);
    }

    /**
    *
    */
    @Test
    public void testIsPrime1Odd() {
        NaturalNumber n = new NaturalNumber2(ODD);
        NaturalNumber nExpected = new NaturalNumber2(ODD);
        boolean r = CryptoUtilities.isPrime1(n);
        boolean rExpected = false;
        assertEquals(nExpected, n);
        assertEquals(rExpected, r);
    }

    /**
    *
    */
    @Test
    public void testIsPrime1Prime() {
        NaturalNumber n = new NaturalNumber2(PRIME);
        NaturalNumber nExpected = new NaturalNumber2(PRIME);
        boolean r = CryptoUtilities.isPrime1(n);
        boolean rExpected = true;
        assertEquals(nExpected, n);
        assertEquals(rExpected, r);
    }

    /**
    *
    */
    @Test
    public void testIsPrime1Big() {
        NaturalNumber n = new NaturalNumber2(BIG);
        NaturalNumber nExpected = new NaturalNumber2(BIG);
        boolean r = CryptoUtilities.isPrime1(n);
        boolean rExpected = false;
        assertEquals(nExpected, n);
        assertEquals(rExpected, r);
    }

    /**
    *
    */
    @Test
    public void testIsPrime2Min() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        boolean r = CryptoUtilities.isPrime1(n);
        boolean rExpected = true;
        assertEquals(nExpected, n);
        assertEquals(rExpected, r);
    }

    /**
    *
    */
    @Test
    public void testIsPrime2Even() {
        NaturalNumber n = new NaturalNumber2(EVEN);
        NaturalNumber nExpected = new NaturalNumber2(EVEN);
        boolean r = CryptoUtilities.isPrime1(n);
        boolean rExpected = false;
        assertEquals(nExpected, n);
        assertEquals(rExpected, r);
    }

    /**
    *
    */
    @Test
    public void testIsPrime2Odd() {
        NaturalNumber n = new NaturalNumber2(ODD);
        NaturalNumber nExpected = new NaturalNumber2(ODD);
        boolean r = CryptoUtilities.isPrime1(n);
        boolean rExpected = false;
        assertEquals(nExpected, n);
        assertEquals(rExpected, r);
    }

    /**
    *
    */
    @Test
    public void testIsPrime2Prime() {
        NaturalNumber n = new NaturalNumber2(PRIME);
        NaturalNumber nExpected = new NaturalNumber2(PRIME);
        boolean r = CryptoUtilities.isPrime1(n);
        boolean rExpected = true;
        assertEquals(nExpected, n);
        assertEquals(rExpected, r);
    }

    /**
    *
    */
    @Test
    public void testIsPrime2Big() {
        NaturalNumber n = new NaturalNumber2(BIG);
        NaturalNumber nExpected = new NaturalNumber2(BIG);
        boolean r = CryptoUtilities.isPrime1(n);
        boolean rExpected = false;
        assertEquals(nExpected, n);
        assertEquals(rExpected, r);
    }

    /**
    *
    */
    @Test
    public void testGenerateNextLikelyPrimeMin() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2("3");
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    /**
    *
    */
    @Test
    public void testGenerateNextLikelyPrimeEven() {
        NaturalNumber n = new NaturalNumber2(EVEN);
        NaturalNumber nExpected = new NaturalNumber2("11");
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    /**
    *
    */
    @Test
    public void testGenerateNextLikelyPrimeOdd() {
        NaturalNumber n = new NaturalNumber2(ODD);
        NaturalNumber nExpected = new NaturalNumber2("11");
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    /**
    *
    */
    @Test
    public void testGenerateNextLikelyPrimeBig() {
        NaturalNumber n = new NaturalNumber2(BIG);
        NaturalNumber nExpected = new NaturalNumber2("2147483659");
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }
}
