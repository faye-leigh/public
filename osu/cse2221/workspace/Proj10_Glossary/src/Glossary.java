import java.util.Comparator;

import components.map.Map;
import components.map.Map.Pair;
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
 * CSE 2221 Project #10. Generates a glossary of terms and definitions from an
 * input file, an HTML index page, and HTML pages for each term.
 *
 * @author Faye Leigh
 */
public final class Glossary {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Glossary() {
    }

    /**
     * Inputs a "glossary" of terms and their definitions from the given file
     * and stores them in the given map.
     *
     * @param fileName
     *            name of the input file
     * @param glossary
     *            the term -> definition map
     * @updates glossary
     * @requires <pre>
     * [file named fileName exists but is not open and
     *  file contains at least one entry of the format:
     *      one unique single word "term" on the first line,
     *      its definition on the following line(s), and
     *      the term/definition pair is terminated by new line,
     *
     *      repeated for each unique term/definition, and
     *      file ends in a new line]
     * </pre>
     * @ensures <pre>
     * [glossary contains term -> definition mapping from file fileName] </pre>
     */
    public static void addToGlossary(String fileName,
            Map<String, String> glossary) {
        assert fileName != null : "Violation of: fileName is not null";
        assert glossary != null : "Violation of: glossary is not null";

        SimpleReader termsFile = new SimpleReader1L(fileName);
        StringBuilder def = new StringBuilder();

        while (!termsFile.atEOS()) {
            String term = termsFile.nextLine();

            /*
             * If term is not blank, it is a glossary term, and the following
             * line(s) until blank line are its definition
             */
            if (!term.isBlank()) {
                boolean endDef = false;

                /*
                 * Add each line following the term line to def until blank line
                 * is reached
                 */
                while (!termsFile.atEOS() && !endDef) {
                    String tmp = termsFile.nextLine();

                    if (tmp.isBlank()) {
                        endDef = true;
                    } else {
                        def.append(tmp);
                    }
                }

                glossary.add(term, def.toString());
                def.delete(0, def.length());
            }
        }

        termsFile.close();
    }

    /**
     * Returns an alphabetical queue of all terms (keys) in the given map.
     *
     * @param glossary
     *            the term -> definition map
     * @return queue of terms
     * @ensures <pre>
     * [sortTerms contains all terms of glossary and
     *  is in alphabetical order] </pre>
     */
    public static Queue<String> sortTerms(Map<String, String> glossary) {
        assert glossary != null : "Violation of: glossary is not null";

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
        for (Map.Pair<String, String> p : glossary) {
            terms.enqueue(p.key());
        }
        terms.sort(cs);

        return terms;
    }

    /**
     * Checks the given definition for presence of glossary terms, and returns
     * the definition string modified to include an HTML hyperlink to each term
     * present.
     *
     * @param definition
     *            the string to check for glossary terms
     * @param glossary
     *            the term -> definition map
     * @return definition string with term hyperlinks
     * @requires |definition| < Integer.MAX_VALUE
     * @ensures <pre>
     * [linkedDef = the given definition but all glossary terms present
     *  in #definition are replaced with an HTML hyperlink to that term
     *  of the form:
     *      <a href="[term].html">[term]</a>
     * </pre>
     */
    public static String linkedDef(String definition,
            Map<String, String> glossary) {
        assert definition != null : "Violation of: definition is not null";
        assert glossary != null : "Violation of: glossary is not null";

        StringBuilder linkedDef = new StringBuilder();
        StringBuilder string = new StringBuilder();
        int pos = 0;

        while (pos < definition.length()) {
            string.replace(0, string.length(),
                    nextWordOrSeparator(definition, pos));

            /*
             * Move pos to next word/separator in the definition
             */
            pos += string.length();

            /*
             * If the string is a glossary term, add the HTML formatting to make
             * the term a hyperlink to that term's definition page
             */
            if (glossary.hasKey(string.toString())) {
                string.append(string);
                string.insert(string.length() / 2, ".html\">");
                string.insert(0, "<a href=\"");
                string.append("</a>");
            }

            linkedDef.append(string);
        }

        return linkedDef.toString();
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
     * </pre>
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
     * Generates an html page for each term in the given map that shows the term
     * and its definition.
     *
     * @param folder
     *            path the html file is to be created in
     * @param glossary
     *            the term -> definition map
     * @ensures <pre>
     * [reads term and definition from glossary, saves HTML document
     *  with term and definition in the given folder]
     * </pre>
     */
    public static void termHTML(String folder, Map<String, String> glossary) {
        assert folder != null : "Violation of: folder is not null";
        assert glossary != null : "Violation of: glossary is not null";

        for (Pair<String, String> p : glossary) {
            /*
             * Create new HTML file for each term in glossary
             */
            SimpleWriter html = new SimpleWriter1L(
                    folder + "/" + p.key() + ".html");
            String def = linkedDef(p.value(), glossary);

            /*
             * Print HTML code to file
             */
            html.println("<html>");
            html.println("<head>");
            html.println("<title>" + p.key() + "</title>");
            html.println("</head>");
            html.println("<body>");
            html.println("<h2><b>" + p.key() + "</b></h2>");
            html.println("<blockquote>" + def + "</blockquote>");
            html.println("<hr />");
            html.println("<p>Return to <a href=\"index.html\">index</a>.</p>");
            html.println("</body>");
            html.println("</html>");
            html.println();

            html.close();
        }
    }

    /**
     * Generates the html index page linking to each term (key) in the given
     * glossary (map).
     *
     * @param folder
     *            path the html file is to be created in
     * @param glossary
     *            the term -> definition map
     * @requires |glossary| < Integer.MAX_VALUE
     * @ensures <pre>
     * [reads each term from glossary, saves HTML document with an
     *  alphabetical hyperlinked list of terms in glossary]
     * </pre>
     */
    public static void indexHTML(String folder, Map<String, String> glossary) {
        assert folder != null : "Violation of: folder is not null";
        assert glossary != null : "Violation of: glossary is not null";

        Queue<String> terms = sortTerms(glossary);
        SimpleWriter html = new SimpleWriter1L(folder + "/index.html");

        /*
         * Print HTML code to file
         */
        html.println("<html>");
        html.println("<head>");
        html.println("<title>Glossary</title>");
        html.println("</head>");
        html.println("<body>");
        html.println("<h2><b>Glossary</b></h2>");
        html.println("<h3>Index</h3>");
        html.println("<ul>");

        /*
         * Add a hyperlinked alphabetical list item for each term in glossary
         */
        int length = terms.length();
        for (int i = 0; i < length; i++) {
            String term = terms.dequeue();
            html.println(
                    "<li><a href=\"" + term + ".html\">" + term + "</a></li>");
        }

        html.println("</ul>");
        html.println("</body>");
        html.println("</html>");
        html.println();

        html.close();
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

        out.println("Please enter the names (including path) of the");
        out.println("input file containing the terms/definitions and the");
        out.println("output folder (i.e. 'data/terms.txt' and 'data').");
        out.println();
        out.print("Input file: ");
        String termsFileName = in.nextLine();
//        String termsFileName = "data/terms.txt";
//        out.println(termsFileName);
        out.print("Output folder: ");
        String outputFolderName = in.nextLine();
//        String outputFolderName = "data";
//        out.println(outputFolderName);

        Map<String, String> glossary = new Map1L<>();
        addToGlossary(termsFileName, glossary);

        termHTML(outputFolderName, glossary);
        indexHTML(outputFolderName, glossary);

        in.close();
        out.close();
    }

}
