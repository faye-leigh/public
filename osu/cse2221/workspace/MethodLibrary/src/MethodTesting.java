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
public final class MethodTesting {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private MethodTesting() {
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

        StringBuilder sentence = new StringBuilder();
        sentence.append("This is second ");
        String the = "the ";
        sentence.insert(8, the);
        sentence.append("sentence and " + the + "last " + the);
        out.println(sentence);

        in.close();
        out.close();
    }

}
