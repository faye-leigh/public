package labs.lab22;

import java.util.Comparator;

import components.queue.Queue;
import components.queue.Queue1L;

/**
 * Layered implementations of secondary method {@code sort} for
 * {@code Queue<String>}.
 */
public final class Queue1LSort1 extends Queue1L<String> {

    /**
     * No-argument constructor.
     */
    public Queue1LSort1() {
        super();
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
        assert q != null : "Violation of: q is not null";
        assert order != null : "Violation of: order is not null";

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

    @Override
    public void sort(Comparator<String> order) {
        assert order != null : "Violation of: order is not null";

        Queue<String> qSorted = this.newInstance();

        while (this.length() > 0) {
            qSorted.enqueue(removeMin(this, order));
        }

        this.transferFrom(qSorted);
    }

}
