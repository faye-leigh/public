package labs.lab02;

import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * CSE 2221 Lab #2.
 *
 * @author Faye Leigh
 */
public final class HelloWorldLab {

    /**
     * No-argument constructor--private to prevent instantiation.
     */
    private HelloWorldLab() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SimpleWriter out = new SimpleWriter1L();
        out.println("Hello World!");
        out.close();
    }

}
