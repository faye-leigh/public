

import components.sequence.Sequence;

/**
 * OSU CSE Lab #2. Implements method to smooth a {@code Sequence<Integer>}.
 *
 * @author Faye Leigh
 *
 */
public final class SequenceSmooth {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SequenceSmooth() {
    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @param s2
     *            the resulting sequence
     * @replaces s2
     * @requires |s1| >= 1
     * @ensures <pre>
     * |s2| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * < i > * < j > * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        s2 = c * <(i+j)/2> * d))
     * </pre>
     */
    public static void smooth(Sequence<Integer> s1, Sequence<Integer> s2) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s2 != null : "Violation of: s2 is not null";
        assert s1 != s2 : "Violation of: s1 is not s2";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";

        s2.clear();
        if (s1.length() > 1) {
            int a = s1.remove(0);
            int b = s1.entry(0);
            smooth(s1, s2);
            int c = a;
            if (a != b) {
                c = (a + b) / 2;
            }
            s2.add(0, c);
            s1.add(0, a);
        }

        /**
         * Smooths a given {@code Sequence<Integer>}.
         *
         * @param s1
         *            the sequence to smooth
         * @param s2
         *            the resulting sequence
         * @replaces s2
         * @requires |s1| >= 1
         * @ensures <pre>
         * |s2| = |s1| - 1  and
         *  for all i, j: integer, a, b: string of integer
         *      where (s1 = a * <i> * < j > * b)
         *    (there exists c, d: string of integer
         *       (|c| = |a|  and
         *        s2 = c * <(i+j)/2> * d))
         * </pre>
         */
//    public static void smooth(Sequence<Integer> s1, Sequence<Integer> s2) {
//        assert s1 != null : "Violation of: s1 is not null";
//        assert s2 != null : "Violation of: s2 is not null";
//        assert s1 != s2 : "Violation of: s1 is not s2";
//        assert s1.length() >= 1 : "Violation of: |s1| >= 1";
//
//        s2.clear();
//        if (s1.length() > 1) {
//            for (int i = 0; i < s1.length() - 1; i++) {
//                int a = s1.entry(i);
//                int b = s1.entry(i + 1);
//                int c = a;
//                if (a != b) {
//                    c = (a + b) / 2;
//                }
//                s2.add(s2.length(), c);
//            }
//        }

    }
}
