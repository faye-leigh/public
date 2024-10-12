

import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * OSU CSE 2231 HW #5. Testing homework function.
 *
 * @author Faye Leigh
 */
public final class JavaList {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private JavaList() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        out.close();
    }

}
