import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.stack.Stack;

/**
 * JUnit test fixture for {@code Stack<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class StackTest {

    /**
     * Invokes the appropriate {@code Stack} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new stack
     * @ensures constructorTest = <>
     */
    protected abstract Stack<String> constructorTest();

    /**
     * Invokes the appropriate {@code Stack} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new stack
     * @ensures constructorRef = <>
     */
    protected abstract Stack<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Stack<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsTest = [entries in args]
     */
    private Stack<String> createFromArgsTest(String... args) {
        Stack<String> stack = this.constructorTest();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    /**
     *
     * Creates and returns a {@code Stack<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsRef = [entries in args]
     */
    private Stack<String> createFromArgsRef(String... args) {
        Stack<String> stack = this.constructorRef();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    // TODO - add test cases for constructor, push, pop, and length

    /**
     * Test for no-argument constructor compared to reference constructor.
     */
    @Test
    public final void testNoArgConstructor() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.constructorTest();
        Stack<String> sExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test for push method on empty stack.
     */
    @Test
    public final void testPushOnEmpty() {
        /*
         * Set up variables
         */
        Stack<String> s = this.constructorTest();
        Stack<String> sExpected = this.createFromArgsRef("a");
        /*
         * Call method under test
         */
        s.push("a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test for push method on stack containing single entry.
     */
    @Test
    public final void testPushOnSingleEntry() {
        /*
         * Set up variables
         */
        Stack<String> s = this.createFromArgsTest("a");
        Stack<String> sExpected = this.createFromArgsRef("b", "a");
        /*
         * Call method under test
         */
        s.push("b");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test for push method on non empty stack.
     */
    @Test
    public final void testPushOnNonEmpty() {
        /*
         * Set up variables
         */
        Stack<String> s = this.createFromArgsTest("a", "c", "b");
        Stack<String> sExpected = this.createFromArgsRef("d", "a", "c", "b");
        /*
         * Call method under test
         */
        s.push("d");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test for pop method on stack containing single entry.
     */
    @Test
    public final void testPopToEmpty() {
        /*
         * Set up variables
         */
        Stack<String> s = this.createFromArgsTest("a");
        Stack<String> sExpected = this.constructorRef();
        /*
         * Call method under test
         */
        String str = s.pop();
        String strExpected = "a";
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(strExpected, str);
    }

    /**
     * Test for pop method on non empty stack.
     */
    @Test
    public final void testPopOnNonEmpty() {
        /*
         * Set up variables
         */
        Stack<String> s = this.createFromArgsTest("a", "c", "b", "d");
        Stack<String> sExpected = this.createFromArgsRef("c", "b", "d");
        /*
         * Call method under test
         */
        String str = s.pop();
        String strExpected = "a";
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(strExpected, str);
    }

    /**
     * Test for length method on empty stack.
     */
    @Test
    public final void testLengthOnEmpty() {
        /*
         * Set up variables
         */
        Stack<String> s = this.constructorTest();
        Stack<String> sExpected = this.constructorRef();
        /*
         * Call method under test
         */
        int i = s.length();
        int iExpected = 0;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(iExpected, i);
    }

    /**
     * Test for length method on non empty stack.
     */
    @Test
    public final void testLengthOnNonEmpty() {
        /*
         * Set up variables
         */
        Stack<String> s = this.createFromArgsTest("a", "c", "b", "d");
        Stack<String> sExpected = this.createFromArgsRef("a", "c", "b", "d");
        /*
         * Call method under test
         */
        int i = s.length();
        int iExpected = sExpected.length();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(iExpected, i);
    }

}
