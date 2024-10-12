package labs.lab02;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * CSE 2221 Lab #2.
 *
 * @author Faye Leigh
 *
 */
public final class HelloJack {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private HelloJack() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.println("What is your name?");
        //Prompt user to to enter name.
        String s = in.nextLine();
        //Store user input to variable s
        out.println("Hello, " + s);
        //Print to console "Hello <user>"

        in.close();
        out.close();
    }

}
