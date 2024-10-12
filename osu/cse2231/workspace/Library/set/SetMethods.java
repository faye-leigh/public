

import components.set.Set;
import components.set.Set1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Method bodies for the {@code Set} class.
 *
 * @author Faye Leigh
 */
public final class SetMethods {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private SetMethods() {
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

        Set<String> s = new Set1L<>();
        out.println(s.contains(""));

        out.close();
    }

}
