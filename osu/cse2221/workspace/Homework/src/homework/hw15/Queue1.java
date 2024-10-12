package homework.hw15;

import java.util.Arrays;

import components.queue.Queue;
import components.queue.Queue1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * CSE 2221 HW #15.
 *
 * @author Faye Leigh
 *
 */
public final class Queue1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Queue1() {
    }

    /**
     * Reports the smallest integer in the given {@code Queue<Integer>}.
     *
     * @param q
     *            the queue of integer
     * @return the smallest integer in the given queue
     * @requires q /= empty_string
     * @ensures <pre>
     * min is in entries(q) and
     * for all x: integer
     *     where (x is in entries(q))
     *   (min <= x)
     * </pre>
     */
    private static int min(Queue<Integer> q) {
        assert q.length() > 0 : "Violation of: q /= empty_string";

        Queue<Integer> tmp = q.newInstance();

        int result = q.dequeue();
        tmp.enqueue(result);

        while (q.length() != 0) {
            int entry = q.dequeue();

            if (entry < result) { // Find min value.
                result = entry;
            }

            tmp.enqueue(entry);
        }

        q.transferFrom(tmp); // Restore q.

        return result;
    }

    /**
     * Reports an array of two {@code int}s with the smallest and the largest
     * integer in the given {@code Queue<Integer>}.
     *
     * @param q
     *            the queue of integer
     * @return an array of two {@code int}s with the smallest and the largest
     *         integer in the given queue
     * @requires q /= empty_string
     * @ensures <pre>
     * { minAndMax[0], minAndMax[1] } is subset of entries(q) and
     * for all x: integer
     *     where (x in in entries(q))
     *   (minAndMax[0] <= x <= minAndMax[1])
     * </pre>
     */
    private static int[] minAndMax(Queue<Integer> q) {
        assert q.length() > 0 : "Violation of: q /= empty_string";

        Queue<Integer> tmp = q.newInstance();

        int[] result = { q.dequeue(), 0 }; // Initial { min, max } values.
        result[1] = result[0];
        tmp.enqueue(result[0]);

        while (q.length() != 0) {
            int entry = q.dequeue();

            if (entry < result[0]) { // Find min value.
                result[0] = entry;
            }

            if (entry > result[1]) { // Find max value.
                result[1] = entry;
            }

            tmp.enqueue(entry);
        }

        q.transferFrom(tmp); // Restore q.

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
        Queue<Integer> q = new Queue1L<Integer>();
        final int[] test = { 3, 1, 4, 1, 5 };
        for (int i : test) {
            q.enqueue(test[i - 1]);
        }

        out.println(min(q));
        out.println(Arrays.toString(minAndMax(q)));

        out.close();
    }
}
