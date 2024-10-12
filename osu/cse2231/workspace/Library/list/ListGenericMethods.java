
import components.list.List2;

/**
 * OSU Components {@code Queue} represented as {@link components.sequence
 * components.sequence} with implementations of primary methods.
 *
 * @param <T>
 *            type of {@code Queue} entries
 * @author Faye Leigh
 *
 */
public class ListGenericMethods<T> extends List2<T> {
    /**
     * Retreats the position in {@code this} by one.
     *
     * @updates this
     * @requires this.left /= <>
     * @ensures <pre>
     * this.left * this.right = #this.left * #this.right  and
     * |this.left| = |#this.left| - 1
     * </pre>
     */
    @Override
    public void retreat() {
        int left = this.leftLength() - 1;
        this.moveToStart();
        while (this.leftLength() > left) {
            this.advance();
        }
    }
}
