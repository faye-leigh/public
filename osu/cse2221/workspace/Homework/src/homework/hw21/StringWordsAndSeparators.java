package homework.hw21;

import components.set.Set;
import components.set.Set1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * CSE 2221 HW #21.
 *
 * @author Faye Leigh
 */
public final class StringWordsAndSeparators {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private StringWordsAndSeparators() {
    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @param charSet
     *            the {@code Set} to be replaced
     * @replaces charSet
     * @ensures charSet = entries(str)
     */
    private static void generateElements(String str, Set<Character> charSet) {
        charSet.clear();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (!charSet.contains(c)) {
                charSet.add(c);
            }
        }
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
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
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert position >= 0 && position < text
                .length() : "Violation of: 0 <= position < |text|";

        char c = text.charAt(position);
        boolean isSeparator = separators.contains(c);
        int pos2 = position + 1;

        while (pos2 < text.length()
                && (separators.contains(text.charAt(pos2)) == isSeparator)) {
            pos2++;
        }
        String result = text.substring(position, pos2);

        return result;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        Set<Character> charSet = new Set1L<>();
        Set<Character> sepSet = new Set1L<>();
        String t = "'Welp, gotta go feed the cats'.\")-,*sigh.*";
        String s = " ',.!-*)(\"";

        generateElements(t, charSet);
        out.println(charSet);
        generateElements(s, sepSet);
        out.println(sepSet);
        out.println(nextWordOrSeparator(t, 1, sepSet));

        out.close();
    }
}
