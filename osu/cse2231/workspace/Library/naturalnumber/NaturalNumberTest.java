
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

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
        NaturalNumber nExpected = this.constructorRef(0);
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
        NaturalNumber nExpected = this.constructorRef(Integer.MAX_VALUE);
        NaturalNumber n = this.constructorTest(Integer.MAX_VALUE);
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
        NaturalNumber nExpected = this.constructorRef("0");
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
    public final void testStrConstructorOnGreaterThanMaxInt() {
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

}
