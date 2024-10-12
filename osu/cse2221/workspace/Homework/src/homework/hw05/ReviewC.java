package homework.hw05;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class ReviewC {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ReviewC() {
    }

    /**
     * Tests whether 3 integers are in increasing order.
     *
     * @param x
     * @param y
     * @param z
     * @return true if all integers are in increasing order
     */
    private static boolean sorted(int x, int y, int z) {
        return x < y && y < z;
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

        out.println("Enter 3 integers");
        out.print("x = ");
        int x = in.nextInteger();
        out.print("y = ");
        int y = in.nextInteger();
        out.print("z = ");
        int z = in.nextInteger();

        if (sorted(x, y, z)) {
            out.println("Numbers are in increasing order.");
        } else {
            out.println("Numbers are not in increasing order.");
        }

        in.close();
        out.close();
    }

}
