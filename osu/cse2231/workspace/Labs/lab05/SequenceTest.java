
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;

/**
 * JUnit test fixture for {@code Sequence<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class SequenceTest {

    /**
     * Invokes the appropriate {@code Sequence} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new sequence
     * @ensures constructorTest = <>
     */
    protected abstract Sequence<String> constructorTest();

    /**
     * Invokes the appropriate {@code Sequence} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new sequence
     * @ensures constructorRef = <>
     */
    protected abstract Sequence<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsTest = [entries in args]
     */
    private Sequence<String> createFromArgsTest(String... args) {
        Sequence<String> sequence = this.constructorTest();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsRef = [entries in args]
     */
    private Sequence<String> createFromArgsRef(String... args) {
        Sequence<String> sequence = this.constructorRef();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    /**
     * Test for no-argument constructor compared to reference constructor.
     */
    @Test
    public final void testNoArgConstructor() {
        /*
         * Set up variables and call method under test
         */
        Sequence<String> s = this.constructorTest();
        Sequence<String> sExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test method add - add string to empty sequence.
     */
    @Test
    public final void testAddToEmpty() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest();
        Sequence<String> sExpected = this.createFromArgsRef("a");
        /*
         * Call method under test
         */
        s.add(0, "a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test method add - add element to front of sequence.
     */
    @Test
    public final void testAddToFront() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest("a", "b", "c");
        Sequence<String> sExpected = this.createFromArgsRef("d", "a", "b", "c");
        /*
         * Call method under test
         */
        s.add(0, "d");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test method add - add element to middle of sequence.
     */
    @Test
    public final void testAddToMiddle() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest("a", "b", "c");
        Sequence<String> sExpected = this.createFromArgsRef("a", "b", "d", "c");
        /*
         * Call method under test
         */
        s.add(2, "d");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test method add - add element to end of sequence.
     */
    @Test
    public final void testAddToEnd() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest("a", "b", "c");
        Sequence<String> sExpected = this.createFromArgsRef("a", "b", "c", "d");
        /*
         * Call method under test
         */
        s.add(s.length(), "d");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test method remove - remove the sole element of a sequence.
     */
    @Test
    public final void testRemoveSoleElement() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest("a");
        Sequence<String> sExpected = this.createFromArgsRef();
        String strExpected = "a";
        /*
         * Call method under test
         */
        String str = s.remove(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(strExpected, str);
    }

    /**
     * Test method remove - remove the first element of a sequence.
     */
    @Test
    public final void testRemoveFirst() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest("a", "b", "c");
        Sequence<String> sExpected = this.createFromArgsRef("b", "c");
        String strExpected = "a";
        /*
         * Call method under test
         */
        String str = s.remove(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(strExpected, str);
    }

    /**
     * Test method remove - remove an element from middle of sequence.
     */
    @Test
    public final void testRemoveMiddle() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest("a", "b", "c");
        Sequence<String> sExpected = this.createFromArgsRef("a", "c");
        String strExpected = "b";
        /*
         * Call method under test
         */
        String str = s.remove(1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(strExpected, str);
    }

    /**
     * Test method remove - remove the last element of a sequence.
     */
    @Test
    public final void testRemoveLast() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest("a", "b", "c");
        Sequence<String> sExpected = this.createFromArgsRef("a", "b");
        String strExpected = "c";
        /*
         * Call method under test
         */
        String str = s.remove(s.length() - 1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(strExpected, str);
    }

    /**
     * Test method length - length of empty sequence.
     */
    @Test
    public final void testLengthEmpty() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest();
        Sequence<String> sExpected = this.createFromArgsRef();
        int iExpected = 0;
        /*
         * Call method under test
         */
        int i = s.length();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(iExpected, i);
    }

    /**
     * Test method length - length of sequence.
     */
    @Test
    public final void testLength() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest("a", "b");
        Sequence<String> sExpected = this.createFromArgsRef("a", "b");
        int iExpected = 2;
        /*
         * Call method under test
         */
        int i = s.length();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(iExpected, i);
    }

}
