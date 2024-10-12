import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Karan Tandra, Faye Leigh
 *
 */
public abstract class SortingMachineTest {
    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
    * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
    * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
    * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
    * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }
    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */
    /**
     * Test for constructor compared to reference constructor.
     */
    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    /**
     * Test for add method on empty sorting machine.
     */
    @Test
    public final void testAddToEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    /**
     * Test for add method on single entry.
     */
    @Test
    public final void testAddToSingleEntry() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "b");
        m.add("b");
        assertEquals(mExpected, m);
    }

    /**
     * Test for add method on multiple entries.
     */
    @Test
    public final void testAddToMultipleEntries() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "b",
                "a", "c");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "b", "a", "c", "d");
        m.add("d");
        assertEquals(mExpected, m);
    }

    /**
     * Test for add method with duplicate entry..
     */
    @Test
    public final void testAddDuplicate() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "a");
        m.add("a");
        assertEquals(mExpected, m);
    }

    /**
     * Test for changeToExtrationMode method on single entry.
     */
    @Test
    public final void testChangeToExtractionModeSingleEntry() {
        SortingMachine<String> sm = this.createFromArgsTest(ORDER, true, "a");
        SortingMachine<String> smExpected = this.createFromArgsRef(ORDER, false,
                "a");
        sm.changeToExtractionMode();
        assertEquals(smExpected, sm);
    }

    /**
     * Test for changeToExtrationMode method on multiple entries.
     */
    @Test
    public final void testChangeToExtractionModeOnMultipleEntries() {
        SortingMachine<String> sm = this.createFromArgsTest(ORDER, true, "b",
                "a", "c", "d");
        SortingMachine<String> smExpected = this.createFromArgsRef(ORDER, false,
                "b", "a", "c", "d");
        sm.changeToExtractionMode();
        assertEquals(smExpected, sm);
    }

    /**
     * Test for removeFirst method on single entry.
     */
    @Test
    public final void testRemoveFirstToSingleEntry() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        String str = m.removeFirst();
        String strExpected = "a";
        assertEquals(mExpected, m);
        assertEquals(strExpected, str);
    }

    /**
     * Test for removeFirst method on multiple entries.
     */
    @Test
    public final void testRemoveFirstToMultipleEntries() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "b",
                "a", "c", "d");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "b", "a", "c", "d");
        String str = m.removeFirst();
        String strExpected = mExpected.removeFirst();
        assertEquals(mExpected, m);
        assertEquals(strExpected, str);
    }

    /**
     * Test for removeFirst method on duplicate entries.
     */
    @Test
    public final void testRemoveFirstToDuplicateEntries() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "b",
                "b", "c", "b");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "b", "b", "c", "b");
        String str = m.removeFirst();
        String strExpected = mExpected.removeFirst();
        assertEquals(mExpected, m);
        assertEquals(strExpected, str);
    }

    /**
     * Test for isInInsertionMode method on empty sorting machine.
     */
    @Test
    public final void testIsInInsertionModeOnEmpty() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        boolean b = m.isInInsertionMode();
        boolean bExpected = true;
        assertEquals(mExpected, m);
        assertEquals(bExpected, b);
    }

    /**
     * Test for isInInsertionMode method on single entry, expected true.
     */
    @Test
    public final void testIsInInsertionModeOnSingleEntryTrue() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "b",
                "b", "c", "b");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "b", "b", "c", "b");
        boolean b = m.isInInsertionMode();
        boolean bExpected = true;
        assertEquals(mExpected, m);
        assertEquals(bExpected, b);
    }

    /**
     * Test for isInInsertionMode method on single entry, expected false.
     */
    @Test
    public final void testIsInInsertionModeOnSingleEntryFalse() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "b",
                "b", "c", "b");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "b", "b", "c", "b");
        boolean b = m.isInInsertionMode();
        boolean bExpected = false;
        assertEquals(mExpected, m);
        assertEquals(bExpected, b);
    }

    /**
     * Test for isInInsertionMode method on multiple entries, expected true.
     */
    @Test
    public final void testIsInInsertionModeOnMultipleEntriesTrue() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a");
        boolean b = m.isInInsertionMode();
        boolean bExpected = true;
        assertEquals(mExpected, m);
        assertEquals(bExpected, b);
    }

    /**
     * Test for isInInsertionMode method on multiple entries, expected false.
     */
    @Test
    public final void testIsInInsertionModeOnMultipleEntriesFalse() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "a");
        boolean b = m.isInInsertionMode();
        boolean bExpected = false;
        assertEquals(mExpected, m);
        assertEquals(bExpected, b);
    }

    /**
     * Test for order method on empty sorting machine.
     */
    @Test
    public final void testOrderEmpty() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected.order(), m.order());
        assertEquals(mExpected, m);
    }

    /**
     * Test for order method on non empty sorting machine.
     */
    @Test
    public final void testOrderNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "b",
                "b", "c", "b");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "b", "b", "c", "b");
        assertEquals(mExpected.order(), m.order());
        assertEquals(mExpected, m);
    }

    /**
     * Test for size method on empty sorting machine.
     */
    @Test
    public final void testSizeEmpty() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected.size(), m.size());
        assertEquals(mExpected, m);
    }

    /**
     * Test for size method on single entry.
     */
    @Test
    public final void testOrderSingleEntry() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "a");
        assertEquals(mExpected.size(), m.size());
        assertEquals(mExpected, m);
    }

    /**
     * Test for size method on multiple entries.
     */
    @Test
    public final void testOrderMultipleEntries() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "b",
                "b", "c", "b");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "b", "b", "c", "b");
        assertEquals(mExpected.size(), m.size());
        assertEquals(mExpected, m);
    }
}
