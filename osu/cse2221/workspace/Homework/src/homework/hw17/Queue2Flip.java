package homework.hw17;

import components.queue.Queue;
import components.queue.Queue1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * CSE 2221 HW #17.
 *
 * @author Faye Leigh
 * @param <T>
 */
public final class Queue2Flip<T> extends Queue1L<T> {

    /**
     * 1. Reverses ("flips") {@code this}.
     *
     * @updates this
     * @ensures this = rev(#this)
     */
    @Override
    public void flip() {
        if (this.length() > 1) {
            Queue<T> thisFlipped = this.newInstance();
            this.rotate(this.length() - 1);
            thisFlipped.enqueue(this.dequeue());
            this.flip();
            thisFlipped.append(this);
            this.transferFrom(thisFlipped);
        }
    }

    /**
     * 2. Reverses ("flips") {@code x}.
     *
     * @param x
     *            Queue<String> to be flipped
     * @updates x
     * @ensures x = rev(#x)
     */
    public static void flipStatic(Queue<String> x) {
        if (x.length() > 1) {
            Queue<String> xFlipped = x.newInstance();
            x.rotate(x.length() - 1);
            xFlipped.enqueue(x.dequeue());
            x.flip();
            xFlipped.append(x);
            x.transferFrom(xFlipped);
        }
    }

    /**
     * 3. <pre>
     *  *TATACAT  **AGCTGTTTTCGTT   CACTCCATTTTA    CATTTTAGCTGTT
     *      *TTTCGTTATACAT   **CTGTTTTCGTTA
     *
     *  TTTCGTTATACAT
     *  +     TATACAT = TTTCGT'TATACAT'*
     *  AGCTGTTTTCGTT
     *  + CTGTTTTCGTTA = AG'CTGTTTTCGTT'A**
     *
     *  CACTCCATTTTA    CATTTTAGCTGTT   *TTTCGTTATACAT   *AGCTGTTTTCGTTA
     *
     *   CATTTTAGCTGTT
     *   +     AGCTGTTTTCGTTA = CATTTT'AGCTGTT'TTCGTTA
     *  AGCTGTTTTCGTTA
     *  +     TTTCGTTATACAT = AGCTGT'TTTCGTTA'TACAT*
     *    CACTCCATTTTA
     *    +    CATTTTAGCTGTT = CACTC'CATTTTA'GCTGTT
     *
     *  CACTCCATTTTA    CATTTTAGCTGTT   AGCTGTTTTCGTTATACAT
     *
     *   CACTCCATTTTA
     *        CATTTTAGCTGTT
     *   +          AGCTGTTTTCGTTATACAT = CACTC'CATTTT"A'GCTGTT"TTCGTTATACAT
     *
     *   = CACTCCATTTTAGCTGTTTTCGTTATACAT
     * </pre>
     */

    /**
     * Main method.
     *
     * @param args
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        Queue<String> x = new Queue2Flip<String>();
        x.enqueue("3");
        x.enqueue("1");
        x.enqueue("4");
        x.enqueue("1");
        x.enqueue("5");

        out.println(x);
        x.flip();
        out.println(x);
        flipStatic(x);
        out.println(x);

        out.close();
    }
}
