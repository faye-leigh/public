package homework.hw17;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.queue.Queue;

/**
 *
 */
public class Queue2Test {

    /**
     *
     */
    @Test
    public void testFlip() {
        Queue<String> x = new Queue2Flip<String>();
        x.enqueue("3");
        x.enqueue("1");
        x.enqueue("4");
        x.enqueue("1");
        x.enqueue("5");
        Queue<String> xExpected = x.newInstance();
        xExpected.enqueue("5");
        xExpected.enqueue("1");
        xExpected.enqueue("4");
        xExpected.enqueue("1");
        xExpected.enqueue("3");

        x.flip();
        assertEquals(xExpected, x);
    }

    /**
     *
     */
    @Test
    public void testFlipStatic() {
        Queue<String> x = new Queue2Flip<String>();
        x.enqueue("3");
        x.enqueue("1");
        x.enqueue("4");
        x.enqueue("1");
        x.enqueue("5");
        Queue<String> xExpected = x.newInstance();
        xExpected.enqueue("5");
        xExpected.enqueue("1");
        xExpected.enqueue("4");
        xExpected.enqueue("1");
        xExpected.enqueue("3");

        x.flip();
        assertEquals(xExpected, x);
    }

}
