package homework.hw19;

import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * CSE 2221 HW #19.
 *
 * @author Faye Leigh
 * @param <T>
 */
public final class SequenceFlip<T> extends Sequence1L<T> {

    /**
     * Reverses ("flips") {@code this}.
     *
     * @updates this
     * @ensures this = rev(#this)
     */
    @Override
    public void flip() {
        if (this.length() > 1) {
            T t = this.remove(0);
            this.flip();
            this.add(this.length(), t);
        }
    }

    /**
     * Reverses ("flips") {@code this}.
     *
     * @updates this
     * @ensures this = rev(#this)
     */
    public void flip2() {
        int length = this.length();

        for (int i = length - 1; i >= 0; i--) {
            this.add(i, this.remove(0));
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        Sequence<Integer> s = new SequenceFlip<Integer>();
        final int val = 4;

        for (int i = 0; i < val; i++) {
            s.add(i, i);
        }

        out.println(s);
        s.flip();
        out.println(s);
        ((SequenceFlip<Integer>) s).flip2();
        out.println(s);

        out.close();
    }
}
