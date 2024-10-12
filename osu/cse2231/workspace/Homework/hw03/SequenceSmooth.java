

import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * OSU CSE 2231 HW #3. Two implementations of the method SequenceSmooth.
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
     * Constructs and returns a sequence of the integers provided as arguments.
     *
     * @param args
     *            0 or more integer arguments
     * @return the sequence of the given arguments
     * @ensures createFromArgs= [the sequence of integers in args]
     */
    private static Sequence<Integer> createFromArgs(Integer... args) {
        Sequence<Integer> s = new Sequence1L<Integer>();
        for (Integer x : args) {
            s.add(s.length(), x);
        }
        return s;
    }

    /**
     * Smooths a given {@code Sequence<Integer>} and returns as a new
     * {@code Sequence<Integer>} instance.
     *
     * @param s
     *            the sequence to smooth
     * @return the smoothed sequence
     * @requires |s| >= 1
     * @ensures <pre>
     * smooth= [sequence smoothed from s] such that:
     * |smooth| = |s| - 1 and
     *     for all i, j: integer, a, b: string of integer
     *         where (s = a * < i > * < j > * b)
     *     (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        smooth = c * <(i+j)/2> * d))
     * </pre>
     */
    public static Sequence<Integer> smooth(Sequence<Integer> s) {
        Sequence<Integer> result = new Sequence1L<Integer>();

        if (s.length() > 1) {
            for (int i = 0; i < s.length() - 1; i++) {
                int a = s.entry(i);
                int b = s.entry(i + 1);
                int c = a;
                if (a != b) {
                    c = (a + b) / 2;
                }
                result.add(result.length(), c);
            }
        }

        return result;
    }

    /**
     * Smooths a given {@code Sequence<Integer>} and returns as a new
     * {@code Sequence<Integer>} instance.
     *
     * @param s
     *            the sequence to smooth
     * @return the smoothed sequence*
     * @requires |s| >= 1
     * @ensures <pre>
     * smooth= [sequence smoothed from s] such that:
     *      |smooth| = |s| - 1 and
     *         for all i, j:integer,a,b: string of integer
     *             where (s = a * < i > * < j > * b)
     *      (there exists c, d: string of integer
     *          (|c| = |a|  and
     *          smooth = c * <(i+j)/2> * d))
     * </pre>
     */
    public static Sequence<Integer> smoothRecursive(Sequence<Integer> s) {
        Sequence<Integer> result = new Sequence1L<Integer>();

        if (s.length() > 1) {
            int a = s.remove(0);
            int b = s.entry(0);
            result = smoothRecursive(s);
            int c = a;
            if (a != b) {
                c = (a + b) / 2;
            }
            result.add(0, c);
            s.add(0, a);
        }

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

        Sequence<Integer> seq1 = createFromArgs(4, 8, 12, 16);
        Sequence<Integer> seq2 = smooth(seq1);
        Sequence<Integer> seq3 = smoothRecursive(seq1);

        out.println(seq1);
        out.println(seq2);
        out.println(seq3);

        out.close();
    }

}
