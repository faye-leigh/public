import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * OSU CSE 2231 Project #1. Program that counts word occurrences in a given
 * input file and outputs an HTML document with a table of the words and counts
 * listed in alphabetical order.
 *
 * <pre>
 * Read input file line by line - combine to one StringBuilder instance
 * Add each unique word to map with its count
 *  StringBuilder charAt each pos (natural number),
 * Add all words to Queue and sort
 * Add all words to HTML table in alphabetical order
 * </pre>
 *
 * @author Faye Leigh
 *
 */
public final class WordCounter {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private WordCounter() {
    }

    /**
     * Opens and reads an input file given by the given {@code String} and adds
     * each unique word and its count of occurrences to the given {@code Map}.
     *
     * @param inputFile
     *            the given {@code String}
     * @param wordCount
     *            the given {@code Map}
     * @replaces wordCount
     * @ensures <pre>
     * [keys of {@code wordCount} = all unique words in input file  and
     *  values of each key = total number of occurrences of that word in file]
     * </pre>
     */
    public static void parseInput(String inputFile,
            Map<String, Integer> wordCount) {
        assert inputFile != null : "Violation of: inputFile is not null";

        wordCount.clear();
        SimpleReader in = new SimpleReader1L(inputFile);

        while (!in.atEOS()) {
            String line = in.nextLine();
            addToMap(line, wordCount);
        }

        in.close();
    }

    /**
     * Adds all unique words within the given {@code String} to the given
     * {@code Map} along with their number of occurrences.
     *
     * @param text
     *            the given {@code String}
     * @param wordCount
     *            the given {@code Map}
     * @updates wordCount
     * @ensures <pre>
     * entries(separators) = entries(.?!,:;_-~[]{}()'"/)  and
     * entries([each word]) intersection separators = {}  and
     * [if the terms of {@code wordCount} does not contain a word
     *    found in {@code text}
     *  then that word is added as a key to {@code wordCount}  and
     *    its value = number of occurrences in {@code text}
     *  if the terms of {@code wordCount} does contain a word
     *    found in {@code text}
     *  then the number of its occurrences is added to the existing
     *    count in {@code wordCound}]
     * </pre>
     */
    public static void addToMap(String text, Map<String, Integer> wordCount) {
        assert text != null : "Violation of: text is not null";

        Set<Character> separators = charSet(" .?!,:;_-~[]{}()'\"/");
        int pos = 0;
        while (pos < text.length()) {
            String str = nextWordOrSeparator(text, pos);
            pos += str.length();
            if (!separators.contains(str.charAt(0))) {
                if (wordCount.hasKey(str)) {
                    wordCount.replaceValue(str, wordCount.value(str) + 1);
                } else {
                    wordCount.add(str, 1);
                }
            }
        }
    }

    /**
     * Generates an HTML document named after the given {@code fileName},
     * containing a table with a list of all words and their respective counts
     * in the given {@code Map} .
     *
     * @param wordCount
     *            the given {@code Map} of word -> count
     * @param fileName
     *            the given {@code String} containing the name to be used for
     *            the HTML file
     * @ensures <pre>
     * [an HTML file whose name = fileName is created  and
     *  HTML document contains a table]
     * </pre>
     */
    public static void generateHTML(Map<String, Integer> wordCount,
            String fileName) {
        assert fileName != null : "Voilation of: fileName is not null";

        SimpleWriter out = new SimpleWriter1L(fileName);
        Queue<String> words = sortTerms(wordCount);

        out.println("<html>");
        out.println("<head>");
        out.print("<title>Words Counted in " + fileName + "</title>");
        out.println("</head>");

        out.println("<body>");
        out.println("<h2>Words Counted in " + fileName + "</h2>");
        out.println("<hr />");

        out.println("<table border=\"1\">");
        out.println("<tr>");
        out.println("<th>Words</th>");
        out.println("<th>Count</th>");
        out.println("</tr>");

        for (String word : words) {
            out.println("<tr>");
            out.println("<td>" + word + "</td>");
            out.println("<td>" + wordCount.value(word) + "</td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }

    /**
     * Returns an alphabetical {@code Queue} of all terms (keys) in the given
     * {@code Map}.
     *
     * @param map
     *            the given {@code Map}
     * @return queue of terms
     * @ensures <pre>
     * [sortTerms contains all terms of {@code terms} and
     *  is in alphabetical order] </pre>
     */
    public static Queue<String> sortTerms(Map<String, Integer> map) {
        assert map != null : "Violation of: glossary is not null";

        /**
         * Sorting comparator for queue.
         */
        class StringSort implements Comparator<String> {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        }
        Comparator<String> cs = new StringSort();
        Queue<String> terms = new Queue1L<>();

        /*
         * Add all glossary terms to a queue and sort by alphabetical order
         */
        for (Map.Pair<String, Integer> p : map) {
            terms.enqueue(p.key());
        }
        terms.sort(cs);

        return terms;
    }

    /**
     * Returns the first "word" (maximal length string of characters not in a
     * set of separator characters) or "separator string" (maximal length string
     * of separator characters) in the given {@code text} starting at the given
     * {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text| and text is not empty and |text| <
     *           Integer.MAX_VALUE
     * @ensures <pre>
     * separators = { .?!,:;_-~[]{}()'"/}
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * <pre></pre>
     */
    public static String nextWordOrSeparator(String text, int position) {
        assert text != null : "Violation of: text is not null";
        assert position >= 0 && position < text
                .length() : "Violation of: 0 <= position < |text|";
        assert !text.isEmpty() : "Violation of: text is not empty";

        Set<Character> separators = charSet(" .?!,:;_-~[]{}()'\"/");
        char c = text.charAt(position);
        boolean isSeparator = separators.contains(c);
        int pos2 = position + 1;

        /*
         * Add all sequential characters of same type (separator/not separator)
         * to result string
         */
        while (pos2 < text.length()
                && (separators.contains(text.charAt(pos2)) == isSeparator)) {
            pos2++;
        }
        String result = text.substring(position, pos2);

        return result;
    }

    /**
     * Returns the {@code Set} of characters in the given {@code String}.
     *
     * @param str
     *            the given {@code String}
     * @return set of characters
     * @requires |str| <= Integer.MAX_VALUE
     * @ensures charSet = entries(str)
     */
    public static Set<Character> charSet(String str) {
        assert str != null : "Violation of: str is not null";

        Set<Character> charSet = new Set1L<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!charSet.contains(c)) {
                charSet.add(c);
            }
        }
        return charSet;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.println(
                "Please enter the absolute or relative path of the input file");
        String inputFile = in.nextLine();
        out.println(
                "Please enter the absolute or relative path of the output file");
        String outputFile = in.nextLine();

        Map<String, Integer> wordCount = new Map1L<String, Integer>();
        parseInput(inputFile, wordCount);
        generateHTML(wordCount, outputFile);

        in.close();
        out.close();
    }

}
