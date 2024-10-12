package labs.lab08;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * CSE 2221 Lab #8.
 *
 * @author Faye Leigh
 */
public final class XMLTreeExploration {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private XMLTreeExploration() {
    }

    /**
     * Output information about the middle child of the given {@code XMLTree}.
     *
     * @param xt
     *            the {@code XMLTree} whose middle child is to be printed
     * @param out
     *            the output stream
     * @updates out.content
     * @requires <pre>
     * [the label of the root of xt is a tag]  and
     * [xt has at least one child]  and  out.is_open
     * </pre>
     * @ensures <pre>
     * out.content = #out.content * [the label of the middle child
     *  of xt, whether the root of the middle child is a tag or text,
     *  and if it is a tag, the number of children of the middle child]
     * </pre>
     */
    private static void printMiddleNode(XMLTree xt, SimpleWriter out) {
        int middleIndex = xt.numberOfChildren() / 2;

        out.println("Middle child label: " + xt.child(middleIndex));
        if (xt.child(middleIndex).isTag()) {
            out.println("Middle child is a tag.");
            out.println("Number of children: "
                    + xt.child(middleIndex).numberOfChildren());
        } else {
            out.println("Middle child is a text node.");
        }
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
        final int ten = 10;

        XMLTree xml = new XMLTree1(
                "http://web.cse.ohio-state.edu/software/2221/web-sw1/"
                        + "extras/instructions/xmltree-model/columbus-weather.xml");
        XMLTree results = xml.child(0), channel = results.child(0),
                title = channel.child(1), titleText = title.child(0),
                astronomy = channel.child(ten);

//        out.println(xml.toString());

        out.println("Root node is tag: " + xml.isTag());
        out.println("Root node label: " + xml.label());
        out.println(
                "Number of children of channel: " + channel.numberOfChildren());
        out.println("titleText tree label: " + titleText.label());
        out.println("titleText label from xml tree: "
                + xml.child(0).child(0).child(1).child(0).label());
        out.println("astronomy has sunset attribute: "
                + astronomy.hasAttribute("sunset"));
        out.println("astronomy has midday attribute: "
                + astronomy.hasAttribute("midday"));
        out.println("astronomy attribute sunrise value: "
                + astronomy.attributeValue("sunrise"));
        out.println("astronomy attribute sunset value: "
                + astronomy.attributeValue("sunset"));
        out.println();

        out.println("Info about <channel>");
        printMiddleNode(channel, out);
//        xml.display();

        out.println(xml.toString());

        in.close();
        out.close();
    }

}
