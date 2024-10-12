
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * OSU CSE 2231 Project 10. From a given input file, this program outputs a
 * corresponding tag cloud.
 *
 * @author Karan Tandra, Faye Leigh
 */
public final class TagCloudGeneratorStandard {

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
     * @param in
     *            the given input
     * @param wordCount
     *            the given {@code SortedMap}
     * @replaces wordCount
     * @requires file.is_open
     */
    private static void processInput(BufferedReader in,
            Map<String, Integer> wordCount) {
        wordCount.clear();
        String line = "";
        try {
            line = in.readLine();
        } catch (Exception e) {
            System.err.println("Error reading line");
        }
        while (line != null) {
            int length = line.length();
            int pos = 0;
            while (pos < length) {
                String s = nextWordOrSeparator(line, pos);
                pos += s.length();
                if (SEPARATORS.indexOf(s.charAt(0)) < 0) {
                    if (wordCount.containsKey(s)) {
                        wordCount.replace(s, wordCount.get(s) + 1);
                    } else if (s.length() > 1 || s.equals("I")
                            || s.compareToIgnoreCase("a") == 0) {
                        wordCount.put(s, 1);
                    }
                }
            }
            try {
                line = in.readLine();
            } catch (Exception e) {
                System.err.println("Error reading line");
            }
        }
    }

    /**
     * Generates a simple HTML page which contains a tag cloud generated from
     * the given inputs.
     *
     * @param inputFileName
     *            the given input file name
     * @param html
     *            the given html file
     * @param wordCount
     *            the given {@code SortedMap}
     * @param n
     *            the given number of words
     * @throws IOException
     */
    private static void outputHTML(String inputFileName, PrintWriter html,
            Map<String, Integer> wordCount, int n) throws IOException {
        /*
         * Print header.
         */
        html.println("<html>");
        html.println("<head>");
        html.println(
                "<title>Top " + n + " words in " + inputFileName + "</title>");
        html.println(
                "<link href=\"http://web.cse.ohio-state.edu/software/2231/web-sw2/assignments/projects/tag-cloud-generator/data/tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">");
        html.println(
                "<link href=\"tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">");
        html.println("</head>");
        /*
         * Print body.
         */
        html.println("<body>");
        html.println("<h2>Top " + n + " words in " + inputFileName + "</h2>");
        html.println("<hr>");
        html.println("<div class=\"cdiv\">");
        html.println("<p class=\"cbox\">");
        /*
         * Print data.
         */
        outputCloud(html, wordCount, n);
        /*
         * Print footer.
         */
        html.println("</p>");
        html.println("</div>");
        html.println("</body>");
        html.println("</html>");

    }

    /**
     * Generates the HTML code for the tag cloud data. Lists the top n words by
     * word count in alphabetical order.
     *
     * @param html
     *            the given html file
     * @param wordCount
     *            the given {@code SortedMap}
     * @param n
     *            the given number of words
     */
    private static void outputCloud(PrintWriter html,
            Map<String, Integer> wordCount, int n) {
        final double sizes = 37.0;
        Comparator<Entry<String, Integer>> highLow = new Comparator<Entry<String, Integer>>() {
            @Override
            public int compare(Entry<String, Integer> o1,
                    Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        };
        Comparator<Entry<String, Integer>> alphabetical = new Comparator<Entry<String, Integer>>() {
            @Override
            public int compare(Entry<String, Integer> o1,
                    Entry<String, Integer> o2) {
                return o1.getKey().compareToIgnoreCase(o2.getKey());
            }
        };

        List<Entry<String, Integer>> values = new ArrayList<>(
                wordCount.entrySet());
        values.sort(highLow);
        values.subList(n, values.size()).clear();
        int max = values.get(0).getValue();
        int min = values.get(values.size() - 1).getValue();
        double countPerSize = (max - min) / sizes;
        values.sort(alphabetical);
        for (Entry<String, Integer> entry : values) {
            try {
                html.println("<span style=\"cursor:default\" class=\"f"
                        + (((int) ((entry.getValue() - min) / countPerSize))
                                + 11)
                        + "\" title=\"count: " + entry.getValue() + "\">"
                        + entry.getKey() + "</span>");
            } catch (Exception e) {
                System.err.println("Error writing to file");
            }
        }

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));
        // String inputFileName = "Assignments/projects/Proj10_TagCloudStandard/Alice.txt";
        // String outputFileName = "Assignments/projects/Proj10_TagCloudStandard/Alice.html";
        // final int count = 20;

        System.out.print("Enter the input file: ");
        String inputFileName = in.readLine();
        System.out.print("Enter the output file: ");
        String outputFileName = in.readLine();
        System.out.print("Enter the number of words: ");
        int count = Integer.parseInt(in.readLine());

        BufferedReader inputFile = new BufferedReader(
                new FileReader(inputFileName));
        PrintWriter outputFile = new PrintWriter(
                new BufferedWriter(new FileWriter(outputFileName)));

        Map<String, Integer> wordCount = new TreeMap<String, Integer>();
        processInput(inputFile, wordCount);
        System.out.println(wordCount);
        outputHTML(inputFileName, outputFile, wordCount, count);

        inputFile.close();
        outputFile.close();
        in.close();
    }
}
