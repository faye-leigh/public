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
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
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

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size

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
     * Test for add method to empty left subtree.
     */
    @Test
    public final void testAddToEmptyLeft() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("c", "d");
        Set<String> sExpected = this.createFromArgsRef("c", "d", "a");
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
     * Test for add method to empty right subtree.
     */
    @Test
    public final void testAddToEmptyRight() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("b", "a");
        Set<String> sExpected = this.createFromArgsRef("b", "a", "d");
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
     * Test for add method to empty left subtree.
     */
    @Test
    public final void testAddToNonEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("c", "d", "a");
        Set<String> sExpected = this.createFromArgsRef("c", "d", "a", "b");
        /*
         * Call method under test
         */
        s.add("b");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test for add method on empty set.
     */
    @Test
    public final void testAddToEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.constructorTest();
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
     * Test for remove method on set containing single entry.
     */
    @Test
    public final void testRemoveToEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("a");
        Set<String> sExpected = this.constructorRef();
        /*
         * Call method under test
         */
        String str = s.remove("a");
        String strExpected = "a";
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(strExpected, str);
    }

    /**
     * Test for remove method from left subtree resulting in set with single
     * entry.
     */
    @Test
    public final void testRemoveFromLeft() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("b", "a");
        Set<String> sExpected = this.createFromArgsRef("b");
        /*
         * Call method under test
         */
        String str = s.remove("a");
        String strExpected = "a";
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(strExpected, str);
    }

    /**
     * Test for remove method from right subtree resulting in set with single
     * entry.
     */
    @Test
    public final void testRemoveFromRight() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("a", "b");
        Set<String> sExpected = this.createFromArgsRef("a");
        /*
         * Call method under test
         */
        String str = s.remove("b");
        String strExpected = "b";
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(strExpected, str);
    }

    /**
     * Test for remove method from root with only left subtree.
     */
    @Test
    public final void testRemoveRootWithLeft() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("b", "a");
        Set<String> sExpected = this.createFromArgsRef("a");
        /*
         * Call method under test
         */
        String str = s.remove("b");
        String strExpected = "b";
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(strExpected, str);
    }

    /**
     * Test for remove method from root with only right subtree.
     */
    @Test
    public final void testRemoveRootWithRight() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("a", "b");
        Set<String> sExpected = this.createFromArgsRef("b");
        /*
         * Call method under test
         */
        String str = s.remove("a");
        String strExpected = "a";
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(strExpected, str);
    }

    /**
     * Test for remove method from root with both subtree.
     */
    @Test
    public final void testRemoveRoot() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("c", "d", "a", "b");
        Set<String> sExpected = this.createFromArgsRef("d", "a", "b");
        /*
         * Call method under test
         */
        String str = s.remove("c");
        String strExpected = "c";
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(strExpected, str);
    }

    /**
     * Test for remove method from non empty set.
     */
    @Test
    public final void testRemoveFromNonEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("c", "d", "a", "b");
        Set<String> sExpected = this.createFromArgsRef("c", "d", "b");
        /*
         * Call method under test
         */
        String str = s.remove("a");
        String strExpected = "a";
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(strExpected, str);
    }

    /**
     * Test for removeAny method on set containing single entry.
     */
    @Test
    public final void testRemoveAnyToEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("a");
        Set<String> sExpected = this.constructorRef();
        /*
         * Call method under test
         */
        String str = s.removeAny();
        String strExpected = "a";
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(strExpected, str);
    }

    /**
     * Test for removeAny method resulting in set with single entry.
     */
    @Test
    public final void testRemoveAnyToSingleEntry() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("b", "a");
        Set<String> sExpected = this.createFromArgsRef("b", "a");
        /*
         * Call method under test
         */
        String str = s.removeAny();
        String strExpected = sExpected.remove(str);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(strExpected, str);
    }

    /**
     * Test for removeAny method resulting in non empty set.
     */
    @Test
    public final void testRemoveAnyToNonEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("c", "b", "a", "d");
        Set<String> sExpected = this.createFromArgsRef("c", "b", "a", "d");
        /*
         * Call method under test
         */
        String str = s.removeAny();
        String strExpected = sExpected.remove(str);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(strExpected, str);
    }

    /**
     * Test for contains method on empty set.
     */
    @Test
    public final void testContainsOnEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.constructorTest();
        Set<String> sExpected = this.constructorRef();
        /*
         * Call method under test
         */
        boolean b = s.contains("a");
        boolean bExpected = false;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(bExpected, b);
    }

    /**
     * Test for contains method on set containing single entry (true).
     */
    @Test
    public final void testContainsOnSingleEntryTrue() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("a");
        Set<String> sExpected = this.createFromArgsRef("a");
        /*
         * Call method under test
         */
        boolean b = s.contains("a");
        boolean bExpected = true;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(bExpected, b);
    }

    /**
     * Test for contains method set containing single entry (false).
     */
    @Test
    public final void testContainsOnSingleEntryFalse() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("a");
        Set<String> sExpected = this.createFromArgsRef("a");
        /*
         * Call method under test
         */
        boolean b = s.contains("c");
        boolean bExpected = false;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(bExpected, b);
    }

    /**
     * Test for contains method on non empty set (true).
     */
    @Test
    public final void testContainsOnNonEmptyTrue() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("c", "b", "a", "d");
        Set<String> sExpected = this.createFromArgsRef("c", "b", "a", "d");
        /*
         * Call method under test
         */
        boolean b = s.contains("c");
        boolean bExpected = true;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(bExpected, b);
    }

    /**
     * Test for contains method on non empty set(false).
     */
    @Test
    public final void testContainsOnNonEmptyFalse() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("c", "b", "a", "d");
        Set<String> sExpected = this.createFromArgsRef("c", "b", "a", "d");
        /*
         * Call method under test
         */
        boolean b = s.contains("e");
        boolean bExpected = false;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(bExpected, b);
    }

    /**
     * Test for size method on empty set.
     */
    @Test
    public final void testSizeOnEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.constructorTest();
        Set<String> sExpected = this.constructorRef();
        /*
         * Call method under test
         */
        int i = s.size();
        int iExpected = 0;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(iExpected, i);
    }

    /**
     * Test for size method on set containing single entry.
     */
    @Test
    public final void testSizeOnSingleEntry() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("a");
        Set<String> sExpected = this.createFromArgsRef("a");
        /*
         * Call method under test
         */
        int i = s.size();
        int iExpected = 1;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(iExpected, i);
    }

    /**
     * Test for size method on empty right subtree.
     */
    @Test
    public final void testSizeOnEmptyRight() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("b", "a");
        Set<String> sExpected = this.createFromArgsRef("b", "a");
        /*
         * Call method under test
         */
        int i = s.size();
        int iExpected = sExpected.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(iExpected, i);
    }

    /**
     * Test for size method on empty left subtree.
     */
    @Test
    public final void testSizeOnEmptyLeft() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("a", "b");
        Set<String> sExpected = this.createFromArgsRef("a", "b");
        /*
         * Call method under test
         */
        int i = s.size();
        int iExpected = sExpected.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(iExpected, i);
    }

    /**
     * Test for size method on non empty set.
     */
    @Test
    public final void testSizeOnNonEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("b", "a", "c");
        Set<String> sExpected = this.createFromArgsRef("b", "a", "c");
        /*
         * Call method under test
         */
        int i = s.size();
        int iExpected = sExpected.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(iExpected, i);
    }

}
