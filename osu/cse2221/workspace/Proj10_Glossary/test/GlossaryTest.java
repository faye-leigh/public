import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

/**
 * CSE 2221 Project #10 JUnit test cases.
 *
 * @author Faye Leigh
 */
public class GlossaryTest {
    /**
     * String constants used frequently in test cases.
     */
    private static final String TEST_STRING = "(e.g., string separator)";
    private static final String LINKED_STRING = "(e.g., <a href=\"string.html\">string</a> <a href=\"separator.html\">separator</a>)";
    private static final String FILE = "test/glossary.txt";
    private static final String FILE_BLANK = "test/blank.txt";
    private static final String FOLDER = "test";

    /**
     * Returns a new instance of set of characters testing purposes.
     *
     * @return new set instance
     */
    private static Set<Character> stringChars() {
        Set<Character> chars = new Set1L<>();
        chars.add('(');
        chars.add('e');
        chars.add('.');
        chars.add('g');
        chars.add(',');
        chars.add(' ');
        chars.add('s');
        chars.add('t');
        chars.add('r');
        chars.add('i');
        chars.add('n');
        chars.add(')');
        chars.add('p');
        chars.add('a');
        chars.add('o');
        return chars;
    }

    /**
     * Returns a new instance of a glossary map containing two entries for
     * testing purposes.
     *
     * @return new map instance
     */
    private static Map<String, String> glossary() {
        Map<String, String> map = new Map1L<>();
        map.add("string", "map separator - map");
        map.add("separator", "string map");
        return map;
    }

    /**
     * Returns a new instance of queue containing two entries for testing
     * purposes.
     *
     * @return new queue instance
     */
    private static Queue<String> queue() {
        Queue<String> queue = new Queue1L<>();
        queue.enqueue("separator");
        queue.enqueue("string");
        return queue;
    }

    /*
     * Test cases for charSet method (edge and routine). No challenge cases
     * applicable.
     */

    /**
     * Edge case - input empty string. Expected method return - empty set.
     */
    @Test
    public void testCharSetEmpty() {
        Set<Character> charSet = Glossary.charSet("");
        Set<Character> charSetExpected = new Set1L<>();
        assertEquals(charSetExpected, charSet);
    }

    /**
     * Routine case - input string consisting of letters and separator
     * characters. Expected method return - set containing each unique character
     * preset in the input string.
     */
    @Test
    public void testCharSetRoutine() {
        Set<Character> charSet = Glossary.charSet(TEST_STRING);
        Set<Character> charSetExpected = stringChars();
        assertEquals(charSetExpected, charSet);
    }

    /*
     * Test cases for nextWordOrSeparator method (edge and routine). No
     * challenge cases applicable since it would be impractical and unnecessary
     * to test strings approaching Integer.MAX_VALUE in length.
     */

    /**
     * Edge case - input string consisting of single space at position 0.
     * Expected method return - string consisting of single space.
     */
    @Test
    public void testNextWordOrSeparatorEdge() {
        String word = Glossary.nextWordOrSeparator(" ", 0);
        String wordExpected = " ";
        assertEquals(wordExpected, word);
    }

    /**
     * Routine case - input blank string consisting of 4 at position 2. Expected
     * method return - string consisting 2 spaces.
     */
    @Test
    public void testNextWordOrSeparatorSpace() {
        String word = Glossary.nextWordOrSeparator("    ", 2);
        String wordExpected = "  ";
        assertEquals(wordExpected, word);
    }

    /**
     * Routine case - input TEST_STRING at position 8, which is within a word.
     * Expected method return - string of the word "tring".
     */
    @Test
    public void testNextWordOrSeparatorWord() {
        String word = Glossary.nextWordOrSeparator(TEST_STRING, 8);
        String wordExpected = "tring";
        assertEquals(wordExpected, word);
    }

    /**
     * Routine case - input TEST_STRING at position 4, which is within a string
     * of separators. Expected method return - string of separators "., ".
     */
    @Test
    public void testNextWordOrSeparatorSeparator() {
        String word = Glossary.nextWordOrSeparator(TEST_STRING, 4);
        String wordExpected = "., ";
        assertEquals(wordExpected, word);
    }

    /*
     * Test cases for addToGlossary method (edge and routine). No challenge
     * cases applicable.
     */

    /**
     * Edge case - input blank text file and a glossary map. Expected after
     * method - glossary map replaced with new map object with 0 entries.
     */
    @Test
    public void testAddToGlossaryBlank() {
        Map<String, String> glossary = new Map1L<>();
        Glossary.addToGlossary(FILE_BLANK, glossary);
        Map<String, String> glossaryExpected = new Map1L<>();
        assertEquals(glossaryExpected, glossary);
    }

    /**
     * Routine case - input text file containing 2 terms/definitions and a
     * glossary map. Expected after method - glossary map replaced with new map
     * object with 2 entries.
     */
    @Test
    public void testAddToGlossaryRoutine() {
        Map<String, String> glossary = new Map1L<>();
        Glossary.addToGlossary(FILE, glossary);
        Map<String, String> glossaryExpected = glossary();
        assertEquals(glossaryExpected, glossary);
    }

    /*
     * Test cases for sortTerms method (edge and routine). No challenge cases
     * applicable.
     */

    /**
     * Edge case - input empty map. Expected method return - queue object with
     * no entries.
     */
    @Test
    public void testSortTermsBlank() {
        Queue<String> terms = Glossary.sortTerms(new Map1L<String, String>());
        Queue<String> termsExpected = new Queue1L<>();
        assertEquals(termsExpected, terms);
    }

    /**
     * Routine case - input glossary map with 2 entries. Expected method return
     * - queue object with the two glossary terms as the only entries, in
     * alphabetical order.
     */
    @Test
    public void testSortTermsRoutine() {
        Queue<String> terms = Glossary.sortTerms(glossary());
        Queue<String> termsExpected = queue();
        assertEquals(termsExpected, terms);
    }

    /*
     * Test cases for linkedDef method (edge, routine, challenge).
     */

    /**
     * Edge case - input empty string and empty glossary map. Expected method
     * return - empty string.
     */
    @Test
    public void testLinkedDefEmpty() {
        Map<String, String> glossary = new Map1L<String, String>();
        Map<String, String> glossaryExpected = new Map1L<String, String>();
        String def = Glossary.linkedDef("", glossary);
        String defExpected = "";
        assertEquals(defExpected, def);
        assertEquals(glossaryExpected, glossary);
    }

    /**
     * Challenge case - input empty string and glossary map. Expected method
     * return - empty string.
     */
    @Test
    public void testLinkedDefStringEmpty() {
        Map<String, String> glossary = glossary();
        Map<String, String> glossaryExpected = glossary();
        String def = Glossary.linkedDef("", glossary);
        String defExpected = "";
        assertEquals(defExpected, def);
        assertEquals(glossaryExpected, glossary);
    }

    /**
     * Challenge case - input string and empty glossary map. Expected method
     * return - unchanged input string.
     */
    @Test
    public void testLinkedDefMapEmpty() {
        Map<String, String> glossary = new Map1L<String, String>();
        Map<String, String> glossaryExpected = new Map1L<String, String>();
        String def = Glossary.linkedDef(TEST_STRING, glossary);
        String defExpected = TEST_STRING;
        assertEquals(defExpected, def);
        assertEquals(glossaryExpected, glossary);
    }

    /**
     * Routine case - input string and glossary map. Expected method return -
     * input string modified with HTML link formatting.
     */
    @Test
    public void testLinkedDefMapRoutine() {
        Map<String, String> glossary = glossary();
        Map<String, String> glossaryExpected = glossary();
        String def = Glossary.linkedDef(TEST_STRING, glossary);
        String defExpected = LINKED_STRING;
        assertEquals(defExpected, def);
        assertEquals(glossaryExpected, glossary);
    }

    /*
     * Test case for termHTML method (routine). No edge cases since it would be
     * impractical to test for the absence of files or to create and test
     * millions of files. No challenge case applicable.
     */

    /**
     * Routine case - input folder name string and glossary map. Expected after
     * method - HTML files of a definition page in the given folder for each
     * term in glossary.
     */
    @Test
    public void testTermHTMLRoutine() {
        Map<String, String> glossary = glossary();
        Map<String, String> glossaryExpected = glossary();
        Glossary.termHTML(FOLDER, glossary);
        assertEquals(glossaryExpected, glossary);
        for (Map.Pair<String, String> p : glossary) {
            SimpleReader term = new SimpleReader1L("test/" + p.key() + ".html");
            SimpleReader termExpected = new SimpleReader1L(
                    "test/" + p.key() + "Expected.html");
            while (!term.atEOS() && !termExpected.atEOS()) {
                assertEquals(termExpected.nextLine(), term.nextLine());
            }
            term.close();
            termExpected.close();
        }
    }

    /*
     * Test case for indexHTML method (routine). No edge cases since it would be
     * impractical to test for the absence of files or to create and test
     * millions of files. No challenge case applicable.
     */

    /**
     * Routine case - input folder name string and glossary map. Expected after
     * method - HTML file of the index page if glossary in the given folder.
     */
    @Test
    public void testIndexHTMLRoutine() {
        Map<String, String> glossary = glossary();
        Map<String, String> glossaryExpected = glossary();
        Glossary.indexHTML(FOLDER, glossary);
        assertEquals(glossaryExpected, glossary);
        SimpleReader index = new SimpleReader1L("test/index.html");
        SimpleReader indexExpected = new SimpleReader1L(
                "test/indexExpected.html");
        while (!index.atEOS() && !indexExpected.atEOS()) {
            assertEquals(indexExpected.nextLine(), index.nextLine());
        }
        index.close();
        indexExpected.close();
    }

}
