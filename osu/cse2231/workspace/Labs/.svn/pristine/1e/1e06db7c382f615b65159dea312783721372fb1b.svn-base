
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Test for no-argument constructor compared to reference constructor.
     */
    @Test
    public final void testNoArgConstructor() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.constructorTest();
        Set<String> sExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test method add - add string to empty set.
     */
    @Test
    public final void testAddToEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef("a");
        /*
         * Call method under test
         */
        s.add("a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test method add - add entry to set.
     */
    @Test
    public final void testAddToNonEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("a", "b", "c");
        Set<String> sExpected = this.createFromArgsRef("a", "b", "c", "d");
        /*
         * Call method under test
         */
        s.add("d");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test method remove - remove the sole entry of a set.
     */
    @Test
    public final void testRemoveOnlyEntry() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("a");
        Set<String> sExpected = this.createFromArgsRef();
        String strExpected = "a";
        /*
         * Call method under test
         */
        String str = s.remove("a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(strExpected, str);
    }

    /**
     * Test method remove - remove an entry from populated set.
     */
    @Test
    public final void testRemoveEntry() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("a", "b", "c");
        Set<String> sExpected = this.createFromArgsRef("b", "c");
        String strExpected = "a";
        /*
         * Call method under test
         */
        String str = s.remove("a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(strExpected, str);
    }

    /**
     * Test method removeAny - remove any entry from set containing a single
     * entry.
     */
    @Test
    public final void testRemoveAnyOnlyEntry() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("a");
        Set<String> sExpected = this.createFromArgsRef();
        String strExpected = "a";
        /*
         * Call method under test
         */
        String str = s.removeAny();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(strExpected, str);
    }

    /**
     * Test method removeAny - remove any entry from populated set.
     */
    @Test
    public final void testRemoveAnyEntry() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("a", "b", "c");
        Set<String> sExpected = this.createFromArgsRef("a", "b", "c");
        /*
         * Call method under test
         */
        String str = s.removeAny();
        sExpected.remove(str);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test method contains - empty set.
     */
    @Test
    public final void testContainsEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();
        /*
         * Call method under test and assert that values of variables match
         * expectations
         */
        assertEquals(sExpected, s);
        assertEquals(false, s.contains("a"));
    }

    /**
     * Test method contains - empty set.
     */
    @Test
    public final void testContainsNonEmptyTrue() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("a", "b", "c", "d");
        Set<String> sExpected = this.createFromArgsRef("a", "b", "c", "d");
        /*
         * Call method under test and assert that values of variables match
         * expectations
         */
        assertEquals(sExpected, s);
        assertEquals(true, s.contains("a"));
    }

    /**
     * Test method contains - empty set.
     */
    @Test
    public final void testContainsNonEmptyFalse() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("a", "b", "c", "d");
        Set<String> sExpected = this.createFromArgsRef("a", "b", "c", "d");
        /*
         * Call method under test and assert that values of variables match
         * expectations
         */
        assertEquals(sExpected, s);
        assertEquals(false, s.contains("e"));
    }

    /**
     * Test method size - size of empty set.
     */
    @Test
    public final void testSizeEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();
        int iExpected = 0;
        /*
         * Call method under test
         */
        int i = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(iExpected, i);
    }

    /**
     * Test method size - size of set.
     */
    @Test
    public final void testSizeNonEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("a", "b");
        Set<String> sExpected = this.createFromArgsRef("a", "b");
        int iExpected = 2;
        /*
         * Call method under test
         */
        int i = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(iExpected, i);
    }

}
