
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and SpotBugs warnings).
 *
 * @author P. Bucci
 */
public final class HashCodeMethods {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private HashCodeMethods() {
        // no code needed here
    }

    /**
     * Creates and returns a {@code Map<String, Integer>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the key entries for the map
     * @return the constructed map
     * @requires [entries of args are unique]
     * @ensures <pre>
     * createFromArgsRef = [entries of args -> index of entry in args + 2]
     * </pre>
     */
    private static Map<String, Integer> createFromArgs(String... args) {
        Map<String, Integer> map = new Map1L<String, Integer>();
        for (int i = 0; i < args.length; i++) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], i + 2);
        }
        return map;
    }

    /**
     *
     * @param c
     * @return digit
     */
    private static int getDigit(char c) {
        Map<String, Integer> m = createFromArgs("[a-cA-C]", "[d-fD-F]",
                "[g-iG-I]", "[j-lJ-L]", "[m-oM-O]", "[p-sP-S]", "[t-vT-V]",
                "[w-zW-Z]");
        int digit = 0;
        boolean found = false;
        while (!found) {
            Pair<String, Integer> p = m.removeAny();
            if (Pattern.matches(p.key(), "" + c)) {
                digit = p.value();
                found = true;
            }
        }
        return digit;
    }

    /**
     *
     * @param str
     * @return int
     */
    public static int hashCode(String str) {
        final int j = 4, ten = 10;
        String num = str;
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            if (Character.isLetter(c)) {
                num = num.replace(c, Character.forDigit(getDigit(c), ten));
            }
        }
        String s = num.substring(j);
        return Integer.parseInt(s);
    }

    /**
     *
     * @param str
     * @return int
     */
    public static int hashCode1(String str) {
        final int n1 = 3, n2 = 4, ten = 10;
        String num = str;
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            if (Character.isLetter(c)) {
                num = num.replace(c, Character.forDigit(getDigit(c), ten));
            }
        }
        String s = num.substring(0, n1) + num.substring(n2);
        return Integer.parseInt(s);
    }

    /**
     *
     * @param str
     * @return int
     */
    public static int hashCode2(String str) {
        final int ten = 10;
        int j = 0;
        String num = str;
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            if (Character.isLetter(c)) {
                num = num.replace(c, Character.forDigit(getDigit(c), ten));
            }
            c = num.charAt(i);
            String s = String.valueOf(c);
            if (Character.isDigit(c)) {
                j += Integer.parseInt(s);
            }
        }
        return j;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        Pattern p = Pattern.compile("[a-cA-C]");
        Matcher m = p.matcher("C");
        out.println(m.find());
        out.println(getDigit('A'));
        out.println(hashCode("1j3-6HiA"));
        out.println(hashCode1("1j3-6HiA"));
        out.println(hashCode2("1j3-6HiA"));
        out.println(Character.getNumericValue('a'));
        char c = 'F';
        int i = c;
        out.println(i);

        out.close();
    }

}
