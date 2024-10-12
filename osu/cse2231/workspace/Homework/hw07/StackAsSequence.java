

import java.util.Iterator;
import java.util.NoSuchElementException;

import components.sequence.Sequence1L;
import components.stack.Stack;
import components.stack.StackSecondary;

/**
 * OSU Components {@code Stack} represented as {@link components.sequence
 * components.sequence} with implementations of primary methods.
 *
 * @param <T>
 *            type of {@code Stack} entries
 * @author Faye Leigh
 */
public class StackAsSequence<T> extends StackSecondary<T> {

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
    public StackAsSequence() {
        this.createNewRep();
    }

    /*
     * Standard methods --------------------------------------------------------
     */
    @SuppressWarnings("unchecked")
    @Override
    public final Stack<T> newInstance() {
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
    public final void transferFrom(Stack<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof StackAsSequence<?> : ""
                + "Violation of: source is of dynamic type StackAsSeequence<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type
         * StackAsSequence<?>, and the ? must be T or the call would not have
         * compiled.
         */
        StackAsSequence<T> localSource = (StackAsSequence<T>) source;
        this.rep = localSource.rep;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ----------------------------------------------------------
     */

    @Override
    public final void push(T x) {
        assert x != null : "Violation of: x is not null";
        this.rep.add(0, x);
    }

    @Override
    public final T pop() {
        assert this.rep.length() > 0 : "Violation of: this /= <>";
        return this.rep.remove(0);
    }

    @Override
    public final int length() {
        return this.rep.length();
    }

    @Override
    public final Iterator<T> iterator() {
        return new StackAsSequenceIterator();
    }

    /**
     * Implementation of {@code Iterator} interface for {@code StackAsSequence}.
     */
    private final class StackAsSequenceIterator implements Iterator<T> {

        /**
         * Representation iterator.
         */
        private final Iterator<T> iterator;

        /**
         * No-argument constructor.
         */
        private StackAsSequenceIterator() {
            this.iterator = StackAsSequence.this.rep.iterator();
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

    /*
     * Other methods -----------------------------------------------------------
     */

    /**
     * Shifts entries between {@code leftStack} and {@code rightStack}, keeping
     * reverse of the former concatenated with the latter fixed, and resulting
     * in length of the former equal to {@code newLeftLength}.
     *
     * @param <T>
     *            type of {@code Stack} entries
     * @param leftStack
     *            the left {@code Stack}
     * @param rightStack
     *            the right {@code Stack}
     * @param newLeftLength
     *            desired new length of {@code leftStack}
     * @updates leftStack, rightStack
     * @requires <pre>
     * 0 <= newLeftLength  and
     * newLeftLength <= |leftStack| + |rightStack|
     * </pre>
     * @ensures <pre>
     * rev(leftStack) * rightStack = rev(#leftStack) * #rightStack  and
     * |leftStack| = newLeftLength}
     * </pre>
     */
    @SuppressWarnings("unused")
    private static <T> void setLengthOfLeftStack(Stack<T> leftStack,
            Stack<T> rightStack, int newLeftLength) {
        assert newLeftLength >= 0 : "Violation of: newLeftLength >= 0";
        assert newLeftLength <= leftStack.length() + rightStack
                .length() : "Violation of: newLeftLength <= |leftStack| + |rightStack|";

        if (newLeftLength > leftStack.length()) {
            for (int i = 0; i < leftStack.length() - newLeftLength; i++) {
                rightStack.push(leftStack.pop());
            }
        } else {
            for (int i = 0; i < rightStack.length() - newLeftLength; i++) {
                leftStack.push(rightStack.pop());
            }
        }
    }
}
