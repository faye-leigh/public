
import java.util.regex.Pattern;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;

/**
 * OSU CSE HW #11. Simple class representing a 7-digit phone number in the form
 * "XXX-XXXX" for a phone in the immediate OSU area.
 *
 * @author Faye Leigh
 */
public class PhoneNumber {

    /**
     * The phone number representation.
     */
    private String rep;

    /**
     * Constructor. {@code pNum} must be in the form "XXX-XXXX" where each "X"
     * is a digit '0'-'9'.
     *
     * @param pNum
     */
    public PhoneNumber(String pNum) {
        this.rep = pNum;
    }

    /**
     * @return i
     */
    public int hashCode1() {
        final int j = 4, ten = 10;
        for (int i = 0; i < this.rep.length(); i++) {
            char c = this.rep.charAt(i);
            if (Character.isLetter(c)) {
                this.rep = this.rep.replace(c,
                        Character.forDigit(getDigit(c), ten));
            }
        }
        String s = this.rep.substring(j);
        int i = Integer.parseInt(s);
        return i;
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
     * Returns the {@code int} that corresponds to the given {@code char}.
     *
     * @param c
     *            the char
     * @return the corresponding int
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
}
