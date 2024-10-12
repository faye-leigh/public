package homework.hw19;

import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.stack.Stack;
import components.stack.Stack1L;

/**
 * CSE 2221 HW #19.
 *
 * @author Faye Leigh
 * @param <T>
 */
public final class StackFlip<T> extends Stack1L<T> {

    /**
     * Reverses ("flips") {@code this}.
     *
     * @updates this
     * @ensures this = rev(#this)
     */
    @Override
    public void flip() {
        Stack<T> flipped = this.newInstance();
        int length = this.length();
        for (int i = 0; i < length; i++) {
            flipped.push(this.pop());
        }
        this.transferFrom(flipped);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        Stack<Integer> s = new StackFlip<Integer>();
        final int val = 4;

        for (int i = 0; i < val; i++) {
            s.push(i);
        }

        out.println(s);
        s.flip();
        out.println(s);

        out.close();
    }
}
