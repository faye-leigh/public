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
public final class ReviewB {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ReviewB() {
    }

    /**
     * Tests whether 3 integers are different.
     *
     * @param x
     * @param y
     * @param z
     * @return true if all integers are different
     */
    private static boolean allDifferent(int x, int y, int z) {
        return x != y && x != z && y != z;
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

        if (allDifferent(x, y, z)) {
            out.println("All 3 numbers are different.");
        } else {
            out.println("Not all 3 numbers are different.");
        }

        in.close();
        out.close();
    }

}
