import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    /**
     * Test for no-argument constructor compared to reference constructor.
     */
    @Test
    public final void testNoArgConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /**
     * Test for integer constructor compared to reference constructor for 0.
     */
    @Test
    public final void testIntConstructorOnZero() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected.toString(), n.toString());
    }

    /**
     * Test for integer constructor compared to reference constructor for 1.
     */
    @Test
    public final void testIntConstructorOnOne() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest(1);
        NaturalNumber nExpected = this.constructorRef(1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /**
     * Test for integer constructor compared to reference constructor for
     * maximum int value.
     */
    @Test
    public final void testIntConstructorOnMaxInt() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest(Integer.MAX_VALUE);
        NaturalNumber nExpected = this.constructorRef(Integer.MAX_VALUE);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /**
     * Test for integer constructor compared to reference constructor for
     * minimum value.
     */
    @Test
    public final void testStrConstructorOnZero() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest("0");
        NaturalNumber nExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected.toString(), n.toString());
    }

    /**
     * Test for string constructor compared to reference constructor for 1.
     */
    @Test
    public final void testStrConstructorOnOne() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest("1");
        NaturalNumber nExpected = this.constructorRef("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /**
     * Test for string constructor compared to reference constructor for value
     * larger than max int.
     */
    @Test
    public final void testStrConstructorOnLarge() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest("2147483648");
        NaturalNumber nExpected = this.constructorRef("2147483648");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /**
     * Test for natural number constructor compared to reference constructor for
     * minimum value.
     */
    @Test
    public final void testNNConstructorOnZero() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest(new NaturalNumber1L(0));
        NaturalNumber nExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected.toString(), n.toString());
    }

    /**
     * Test for natural number constructor compared to reference constructor for
     * 1.
     */
    @Test
    public final void testNNConstructorOnOne() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest(new NaturalNumber1L(1));
        NaturalNumber nExpected = this.constructorRef(new NaturalNumber1L(1));
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /**
     * Test for natural number constructor compared to reference constructor for
     * value larger than max int.
     */
    @Test
    public final void testNNConstructorOnLarge() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this
                .constructorTest(new NaturalNumber1L("2147483648"));
        NaturalNumber nExpected = this
                .constructorRef(new NaturalNumber1L("2147483648"));
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /**
     * Test for multiplyBy10 method - multiply 0 by 0.
     */
    @Test
    public final void testMultiplyBy10on0By0() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        /*
         * Call method under test
         */
        n.multiplyBy10(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /**
     * Test for multiplyBy10 method - multiply 1 by 0.
     */
    @Test
    public final void testMultiplyBy10on1By0() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(1);
        NaturalNumber nExpected = this.constructorRef("10");
        /*
         * Call method under test
         */
        n.multiplyBy10(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /**
     * Test for multiplyBy10 method - multiply value larger than max int by 1.
     */
    @Test
    public final void testMultiplyBy10onLargeBy1() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("2147483648");
        NaturalNumber nExpected = this.constructorRef("21474836481");
        /*
         * Call method under test
         */
        n.multiplyBy10(1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /**
     * Test for divideBy10 method on 0.
     */
    @Test
    public final void testdivideBy10on0() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        int i = n.divideBy10();
        int iExpected = 0;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(iExpected, i);
    }

    /**
     * Test for divideBy10 method on 1.
     */
    @Test
    public final void testdivideBy10on1() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest(1);
        NaturalNumber nExpected = this.constructorRef();
        int i = n.divideBy10();
        int iExpected = 1;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(iExpected, i);
    }

    /**
     * Test for divideBy10 method on value larger than max int.
     */
    @Test
    public final void testdivideBy10onLarge() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest("21474836481");
        NaturalNumber nExpected = this.constructorRef("2147483648");
        int i = n.divideBy10();
        int iExpected = 1;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(iExpected, i);
    }

    /**
     * Test for isZero method on 0.
     */
    @Test
    public final void testIsZeroOn0() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(n.isZero(), true);
    }

    /**
     * Test for isZero method on 1.
     */
    @Test
    public final void testIsZeroOn1() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest(1);
        NaturalNumber nExpected = this.constructorRef(1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(n.isZero(), false);
    }

    /**
     * Test for isZero method on value larger than max int.
     */
    @Test
    public final void testIsZeroOnLarge() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest("2147483648");
        NaturalNumber nExpected = this.constructorRef("2147483648");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(n.isZero(), false);
    }

}
