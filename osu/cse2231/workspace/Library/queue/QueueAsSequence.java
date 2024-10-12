

import java.util.Iterator;
import java.util.NoSuchElementException;

import components.queue.Queue;
import components.queue.QueueSecondary;
import components.sequence.Sequence1L;

/**
 * OSU Components {@code Queue} represented as {@link components.sequence
 * components.sequence} with implementations of primary methods.
 *
 * @param <T>
 *            type of {@code Queue} entries
 * @author Faye Leigh
 *
 */
public class QueueAsSequence<T> extends QueueSecondary<T> {

    /*
     * Private members ---------------------------------------------------------
     */

    /**
     * Representation of {@code this}.
     */
    private Sequence1L<T> rep;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.rep = new Sequence1L<T>();
    }

    /*
     * Constructors ------------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public QueueAsSequence() {
        this.createNewRep();
    }

    /*
     * Standard methods --------------------------------------------------------
     */

    @SuppressWarnings("unchecked")
    @Override
    public final Queue<T> newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(Queue<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof QueueGenericMethods<?> : ""
                + "Violation of: source is of dynamic type QueueAsSequence<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type
         * QueueAsSequence<?>, and the ? must be T or the call would not have
         * compiled.
         */
        QueueAsSequence<T> localSource = (QueueAsSequence<T>) source;
        this.rep = localSource.rep;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ----------------------------------------------------------
     */

    @Override
    public final void enqueue(T x) {
        assert x != null : "Violation of: x is not null";
        this.rep.add(this.rep.length(), x);
    }

    @Override
    public final T dequeue() {
        assert this.rep.length() > 0 : "Violation of: this /= <>";
        return this.rep.remove(0);
    }

    @Override
    public final int length() {
        return this.rep.length();
    }

    @Override
    public final Iterator<T> iterator() {
        return new QueueAsSequenceIterator();
    }

    /**
     * Implementation of {@code Iterator} interface for {@code QueueAsSequence}.
     */
    private final class QueueAsSequenceIterator implements Iterator<T> {

        /**
         * Representation iterator.
         */
        private final Iterator<T> iterator;

        /**
         * No-argument constructor.
         */
        private QueueAsSequenceIterator() {
            this.iterator = QueueAsSequence.this.rep.iterator();
        }

        @Override
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override
        public T next() {
            assert this.hasNext() : "Violation of: ~this.unseen /= <>";
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            return this.iterator.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException(
                    "remove operation not supported");
        }
    }

}
