

import java.util.Comparator;

import components.queue.Queue;
import components.queue.Queue2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * OSU Components {@code Queue} represented as {@link components.sequence
 * components.sequence} with implementations of primary methods.
 *
 * @param <T>
 *            type of {@code Queue} entries
 * @author Faye Leigh
 *
 */
public class QueueGenericMethods<T> extends Queue2<T> {

    /**
     * Reports the front of {@code this}.
     *
     * @return the front entry of {@code this}
     * @aliases reference returned by {@code front}
     * @requires this /= <>
     * @ensures <front> is prefix of this
     */
    @Override
    public T front() {
        assert this.length() > 0 : "Violation of: this /= <>";

        T x = this.dequeue();
        Queue<T> tmp = this.newInstance();
        tmp.enqueue(x);
        for (int i = 0; i < this.length(); i++) {
            tmp.enqueue(this.dequeue());
        }
        this.transferFrom(tmp);

        return x;
    }

    /**
     * Finds {@code x} in {@code q} and, if such exists, moves it to the front
     * of {@code q}.
     *
     * @param <T>
     *            type of {@code Queue} entries
     * @param q
     *            the {@code Queue} to be searched
     * @param x
     *            the entry to be searched for
     * @updates q
     * @ensures <pre>
     * perms(q, #q)  and
     * if <x> is substring of q
     *  then <x> is prefix of q
     * </pre>
     */
    @SuppressWarnings("unused")
    private static <T> void moveToFront(Queue<T> q, T x) {
        assert x != null : "Violation of: x is not null";

        int location = 0, i = 0;
        for (T t : q) {
            if (t.equals(x)) {
                location = i;
            }
            i++;
        }
        q.rotate(location);
    }

    /**
     * Inserts the given {@code T} in the {@code Queue<T>} sorted according to
     * the given {@code Comparator<T>} and maintains the {@code Queue<T>}
     * sorted.
     *
     * @param <T>
     *            type of {@code Queue} entries
     * @param q
     *            the {@code Queue} to insert into
     * @param x
     *            the {@code T} to insert
     * @param order
     *            the {@code Comparator} defining the order for {@code T}
     * @updates q
     * @requires <pre>
     * IS_TOTAL_PREORDER([relation computed by order.compare method])  and
     * IS_SORTED(q, [relation computed by order.compare method])
     * </pre>
     * @ensures <pre>
     * perms(q, #q * <x>)  and
     * IS_SORTED(q, [relation computed by order.compare method])
     * </pre>
     */
    @SuppressWarnings("unused")
    private static <T> void insertInOrder(Queue<T> q, T x,
            Comparator<T> order) {
        if (q.length() > 0) {
            if (order.compare(x, q.front()) < 1) {
                q.enqueue(x);
            } else {
                T t = q.dequeue();
                insertInOrder(q, x, order);
                q.enqueue(t);
            }
            q.rotate(-1);
        } else {
            q.enqueue(x);
        }
    }

//    /**
//     * Sorts {@code this} according to the ordering provided by the
//     * {@code compare} method from {@code order}.
//     *
//     * @param order
//     *            ordering by which to sort
//     * @updates this
//     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
//     * @ensures <pre>
//     * perms(this, #this)  and
//     * IS_SORTED(this, [relation computed by order.compare method])
//     * </pre>
//     */
//    @Override
//    public void sort(Comparator<T> order) {
//        Queue<T> tmp = this.newInstance();
//        while (this.length() > 0) {
//            insertInOrder(tmp, this.dequeue(), order);
//        }
//        this.transferFrom(tmp);
//    }

    /**
     * Partitions {@code q} into two parts: entries no larger than
     * {@code partitioner} are put in {@code front}, and the rest are put in
     * {@code back}.
     *
     * @param <T>
     *            type of {@code Queue} entries
     * @param q
     *            the {@code Queue} to be partitioned
     * @param partitioner
     *            the partitioning value
     * @param front
     *            upon return, the entries no larger than {@code partitioner}
     * @param back
     *            upon return, the entries larger than {@code partitioner}
     * @param order
     *            ordering by which to separate entries
     * @clears q
     * @replaces front, back
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * perms(#q, front * back)  and
     * for all x: T where (<x> is substring of front)
     *  ([relation computed by order.compare method](x, partitioner))  and
     * for all x: T where (<x> is substring of back)
     *  (not [relation computed by order.compare method](x, partitioner))
     * </pre>
     */
    private static <T> void partition(Queue<T> q, T partitioner, Queue<T> front,
            Queue<T> back, Comparator<T> order) {
        assert partitioner != null : "Violation of: partitioner is not null";

        front.clear();
        back.clear();
        while (q.length() > 0) {
            if (order.compare(q.front(), partitioner) < 1) {
                front.enqueue(q.dequeue());
            } else {
                back.enqueue(q.dequeue());
            }
        }
    }

    /**
     * Sorts {@code this} according to the ordering provided by the
     * {@code compare} method from {@code order}. (quicksort algorithm).
     *
     * @param order
     *            ordering by which to sort
     * @updates this
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * perms(this, #this)  and
     * IS_SORTED(this, [relation computed by order.compare method])
     * </pre>
     */
    @Override
    public void sort(Comparator<T> order) {
        if (this.length() > 1) {
            /*
             * Dequeue the partitioning entry from this
             */
            T t = this.dequeue();
            /*
             * Partition this into two queues as discussed above (you will need
             * to declare and initialize two new queues)
             */
            Queue<T> left = this.newInstance();
            Queue<T> right = this.newInstance();
            partition(this, t, left, right, order);
            /*
             * Recursively sort the two queues
             */
            left.sort(order);
            right.sort(order);
            /*
             * Reconstruct this by combining the two sorted queues and the
             * partitioning entry in the proper order
             */
            this.append(left);
            this.enqueue(t);
            this.append(right);
        }
    }

    /**
     * Main method.
     *
     * @param args
     */
    public static void main(String... args) {
        SimpleWriter out = new SimpleWriter1L();

        Queue<String> q = new Queue2<String>();
        q.enqueue("a");
        q.enqueue("b");
        q.enqueue("c");
        q.rotate(-1);
        out.println(q);
        out.close();
    }
}
