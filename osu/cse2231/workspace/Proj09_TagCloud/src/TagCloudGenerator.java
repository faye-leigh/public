import java.util.Comparator;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * From a given input file, this program outputs a corresponding tag cloud.
 *
 * @author Karan Tandra, Faye Leigh
 */
public final class TagCloudGenerator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private TagCloudGenerator() {
    }

    /**
     * Seperators.
     */
    private static final String SEPARATORS = " \t\n\r,-.!?[]`'\";:/()_";

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code SEPARATORS}) or "separator string" (maximal length string of
     * characters in {@code SEPARATORS}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures
     *
     *          <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection entries(SEPARATORS) = {}
     * then
     *   entries(nextWordOrSeparator) intersection entries(SEPARATORS) = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection entries(SEPARATORS) /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of entries(SEPARATORS)  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of entries(SEPARATORS))
     *          </pre>
     */
    private static String nextWordOrSeparator(String text, int pos1) {
        boolean isSeparator = SEPARATORS.indexOf(text.charAt(pos1)) >= 0;
        int pos2 = pos1 + 1;
        while (pos2 < text.length() && ((SEPARATORS
                .indexOf(text.charAt(pos2)) >= 0) == isSeparator)) {
            pos2++;
        }
        return text.substring(pos1, pos2);
    }

    /**
     * Reads input file and stores each word and their respective counts as a
     * map.
     *
     * @param file
     *            the given input
     * @param wordCount
     *            the given {@code Map}
     * @replaces wordCount
     * @requires file.is_open
     */
    private static void processFile(SimpleReader file,
            Map<String, Integer> wordCount) {
        wordCount.clear();
        String line = "";
        while (!file.atEOS()) {
            line = file.nextLine();
            int length = line.length();
            int pos = 0;
            /*
             * Add each word in line to map, ignoring single character non-words
             */
            while (pos < length) {
                String word = nextWordOrSeparator(line, pos);
                pos += word.length();
                if (SEPARATORS.indexOf(word.charAt(0)) < 0) {
                    if (wordCount.hasKey(word)) {
                        wordCount.replaceValue(word, wordCount.value(word) + 1);
                    } else if (word.length() > 1 || word.equals("I")
                            || word.compareToIgnoreCase("a") == 0) {
                        wordCount.add(word, 1);
                    }
                }
            }
        }
    }

    /**
     * Sorting comparator for queue - alphabetical.
     */
    private static class StrSort implements Comparator<Pair<String, Integer>> {
        @Override
        public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
            return o1.key().compareToIgnoreCase(o2.key());
        }
    }

    /**
     * Sorting comparator for queue - numerical
     */
    private static class IntSort implements Comparator<Pair<String, Integer>> {
        @Override
        public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
            return o2.value().compareTo(o1.value());
        }
    }

    /**
     * Finds the font size value for the top {@code n} words in
     * {@code wordCount} and stores each word and size in {@code wordSize} as a
     * {@code Queue} of {@code Pair}s sorted alphabetically.
     *
     * @param wordCount
     *            the given {@code Map}
     * @param wordSize
     *            the given {@code Queue}
     * @param n
     *            number of words
     * @replaces {@code wordSize}
     * @requires n > 0
     */
    private static void getSize(Map<String, Integer> wordCount,
            Queue<Pair<String, Integer>> wordSize, int n) {
        assert n > 0 : "Violation of: n > 0";
        Comparator<Pair<String, Integer>> intSort = new IntSort();
        Comparator<Pair<String, Integer>> strSort = new StrSort();
        Map<String, Integer> tmp = wordCount.newInstance();
        wordSize.clear();
        /*
         * Sort all words in order of decreasing count, using wordSize as temp
         * queue.
         */
        for (Pair<String, Integer> p : wordCount) {
            wordSize.enqueue(p);
        }
        wordSize.sort(intSort);
        /*
         * Store top n words and their font sizes to new temporary map.
         */
        final double sizes = 38.0;
        int max = wordSize.front().value();
        wordSize.rotate(-1);
        int min = wordSize.front().value();
        wordSize.rotate(1);
        double countPerSize = (max - min) / sizes;
        while (tmp.size() < n) {
            Pair<String, Integer> p = wordSize.dequeue();
            int count = p.value();
            count = (int) ((count - min) / countPerSize) + 10;
            tmp.add(p.key(), count);
        }
        wordSize.clear();
        /*
         * Store words and sizes to queue and sort alphabetically.
         */
        for (Pair<String, Integer> p : tmp) {
            wordSize.enqueue(p);
        }
        wordSize.sort(strSort);

    }

    /**
     * Generates a simple HTML page which contains a tag cloud generated from
     * the given inputs.
     *
     * @param in
     *            the given input file name
     * @param out
     *            the given output file
     * @param wordCount
     *            the given {@code Map}
     * @param wordSize
     *            the given {@code Queue}
     * @param n
     *            the given number of words
     */
    public static void outputHTML(String in, SimpleWriter out,
            Map<String, Integer> wordCount,
            Queue<Pair<String, Integer>> wordSize, int n) {
        /*
         * Print head.
         */
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Top " + n + " words in " + in + "</title>");
        out.println(
                "<link href=\"http://web.cse.ohio-state.edu/software/2231/web-sw2/assignments/projects/tag-cloud-generator/data/tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">");
        out.println(
                "<link href=\"tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">");
        out.println("</head>");
        /*
         * Print body.
         */
        out.println("<body>");
        out.println("<h2>Top " + n + " words in " + in + "</h2>");
        out.println("<hr>");
        out.println("<div class=\"cdiv\">");
        out.println("<p class=\"cbox\">");
        for (Pair<String, Integer> p : wordSize) {
            out.println("<span style=\"cursor:default\" class=\"f" + p.value()
                    + "\" title=\"count: " + wordCount.value(p.key()) + "\">"
                    + p.key() + "</span>");
        }
        out.println("</p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

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

        // String inputFileName = "Assignments/projects/Proj09_TagCloud/Alice.txt";
        // String outputFileName = "Assignments/projects/Proj09_TagCloud/Alice.html";
        // final int count = 100;

        out.print("Enter the input file: ");
        String inputFileName = in.nextLine();
        out.print("Enter the output file: ");
        String outputFileName = in.nextLine();
        out.print("Enter the number of words: ");
        int count = in.nextInteger();

        SimpleReader inputFile = new SimpleReader1L(inputFileName);
        SimpleWriter outputFile = new SimpleWriter1L(outputFileName);
        Map<String, Integer> wordCount = new Map1L<>();
        processFile(inputFile, wordCount);
        Queue<Pair<String, Integer>> wordSize = new Queue1L<>();
        getSize(wordCount, wordSize, count);
        outputHTML(inputFileName, outputFile, wordCount, wordSize, count);

        inputFile.close();
        outputFile.close();
        in.close();
        out.close();

    }

}
