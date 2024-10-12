

import components.queue.Queue;
import components.queue.Queue2;

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

        Queue<T> qTmp = q.newInstance();

        for (T t : q) {
            if (!t.equals(x)) {
                qTmp.enqueue(t);
            }
        }

        if (q.length() != qTmp.length()) {
            qTmp.enqueue(x);
        }

        q.transferFrom(qTmp);
    }
}
