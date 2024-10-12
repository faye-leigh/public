package homework.hw18;

import java.util.Comparator;

import components.queue.Queue;
import components.queue.Queue1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * CSE 2221 HW #18.
 *
 * @author Faye Leigh
 *
 */
public final class Queue3 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Queue3() {
    }

    /**
     * Removes and returns the minimum value from {@code q} according to the
     * ordering provided by the {@code compare} method from {@code order}.
     *
     * @param q
     *            the queue
     * @param order
     *            ordering by which to compare entries
     * @return the minimum value from {@code q}
     * @updates q
     * @requires <pre>
     * q /= empty_string  and
     *  [the relation computed by order.compare is a total preorder]
     * </pre>
     * @ensures <pre>
     * perms(q * <removeMin>, #q)  and
     *  for all x: string of character
     *      where (x is in entries (q))
     *    ([relation computed by order.compare method](removeMin, x))
     * </pre>
     */
    private static String removeMin(Queue<String> q, Comparator<String> order) {
        assert q.length() > 0 : "Violation of: q /= empty_string";

        String min = q.dequeue();
        q.enqueue(min);
        int minPos = 0;

        /*
         * Find minimum value and restore queue order
         */
        for (int i = q.length() - 1; i > 0; i--) {
            String s = q.dequeue();
            q.enqueue(s);
            if (order.compare(s, min) < 0) {
                min = s;
                minPos = q.length() - i;
            }
        }

        /*
         * Remove minimum value and restore queue order
         */
        q.rotate(minPos);
        q.dequeue();
        q.rotate(q.length() - minPos);

        return min;
    }

    /**
     * Sorts {@code q} according to the ordering provided by the {@code compare}
     * method from {@code order}.
     *
     * @param q
     *            the queue
     * @param order
     *            ordering by which to sort
     * @updates q
     * @requires [the relation computed by order.compare is a total preorder]
     * @ensures q = [#q ordered by the relation computed by order.compare]
     */
    public static void sort(Queue<String> q, Comparator<String> order) {
        Queue<String> qSorted = q.newInstance();

        while (q.length() > 0) {
            qSorted.enqueue(removeMin(q, order));
        }

        q.transferFrom(qSorted);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        /**
         *
         */
        class StringLT implements Comparator<String> {
            @Override
            public int compare(String x, String y) {
                return x.compareTo(y);
            }
        }
        Comparator<String> order = new StringLT();
        SimpleWriter out = new SimpleWriter1L();
        Queue<String> x = new Queue1L<String>();
        x.enqueue("e");
        x.enqueue("b");
        x.enqueue("n");
        x.enqueue("k");
        x.enqueue("a");

        out.println(x);
        out.println(removeMin(x, order));
        out.println(x);

        sort(x, order);
        out.println(x);

        out.close();
    }
}
