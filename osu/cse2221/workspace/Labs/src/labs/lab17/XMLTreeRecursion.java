package labs.lab17;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree2;

/**
 * CSE 2221 Lab #17. Program to practice recursion on {@code XMLTree}s.
 *
 * @author Faye Leigh
 *
 */
public final class XMLTreeRecursion {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeRecursion() {
    }

    /**
     * Returns the number of occurrences of the given tag in the given
     * {@code XMLTree}.
     *
     * @param xml
     *            the {@code XMLTree}
     * @param tag
     *            the tag name
     * @return the number of occurrences of the given tag in the given
     *         {@code XMLTree}
     * @ensures <pre>
     * tagCount =
     *    [the number of occurrences of the given tag in the given {@code XMLTree}]
     * </pre>
     */
    private static int tagCount(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";

        int count = 0;

        if (xml.isTag()) {
            if (xml.label().equals(tag)) {
                count++;
            }
            if (xml.numberOfChildren() > 0) {
                for (int i = 0; i < xml.numberOfChildren(); i++) {
                    count += tagCount(xml.child(i), tag);
                }
            }

        }

        return count;
    }

    /**
     * Outputs the text nodes in the given {@code XMLTree} on separate lines.
     *
     * @param xml
     *            the {@code XMLTree}
     * @param out
     *            the output stream
     * @updates out.content
     * @requires out.is_open
     * @ensures <pre>
     * out.content = #out.content * [the text nodes of xml on separate lines]
     * </pre>
     */
    private static void outputTextNodes(XMLTree xml, SimpleWriter out) {
        assert xml != null : "Violation of: xml is not null";
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        if (xml.isTag()) {
            for (int i = 0; i < xml.numberOfChildren(); i++) {
                outputTextNodes(xml.child(i), out);
            }
        } else {
            out.println(xml);
        }

    }

    /**
     * Outputs n spaces.
     *
     * @param n
     *            the number of spaces
     * @param out
     *            the output stream
     * @updates out.content
     * @requires out.is_open and n >= 0
     * @ensures out.content = #out.content * [n spaces]
     */
    private static void outputSpaces(int n, SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";
        assert n >= 0 : "Violation of: n >= 0";

        for (int i = 0; i < n; i++) {
            out.print("  ");
        }
    }

    /**
     * Outputs the attributes ( name="value") of the given {@code XMLTree}'s
     * root node to the given output stream.
     *
     * @param xml
     *            the {@code XMLTree}
     * @param out
     *            the output stream
     * @updates out.content
     * @requires out.is_open and [the label of the root of xml is a tag]
     * @ensures <pre>
     * out.content =
     *    #out.content * [the attributes ( name="value") of the root of xml]
     * </pre>
     */
    private static void outputAttributes(XMLTree xml, SimpleWriter out) {
        assert xml != null : "Violation of: xml is not null";
        assert out != null : "Violation of: out is not null";
        assert xml
                .isTag() : "Violation of: the label of the root of xml is a tag";
        assert out.isOpen() : "Violation of: out.is_open";

        Iterable<String> attributeList = xml.attributeNames();
        for (String attribute : attributeList) {
            out.print(" " + attribute + "=\"" + xml.attributeValue(attribute)
                    + "\"");
        }
    }

    /**
     * Output the XML textual representation of the given {@code XMLTree}.
     *
     * @param xml
     *            the {@code XMLTree}
     * @param out
     *            the output stream
     * @param indentationLevel
     *            the level of indentation
     * @updates out.content
     * @requires out.is_open and indentationLevel >= 0
     * @ensures <pre>
     * out.content = #out.content * [the XML textual representation of xml]
     * </pre>
     */
    private static void outputXML(XMLTree xml, SimpleWriter out,
            int indentationLevel) {
        assert xml != null : "Violation of: xml is not null";
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";
        assert indentationLevel >= 0 : "Violation of: indentationLevel >= 0";

        outputSpaces(indentationLevel, out);
        if (xml.isTag()) {
            out.print("<" + xml.label());
            outputAttributes(xml, out);
            if (xml.numberOfChildren() > 0) {
                out.print(">");
                if (xml.numberOfChildren() == 1 && !xml.child(0).isTag()) {
                    out.println(xml.child(0) + "</" + xml.label() + ">");
                } else {
                    out.println();
                    for (int i = 0; i < xml.numberOfChildren(); i++) {
                        XMLTree childTree = xml.child(i);
                        outputXML(childTree, out, indentationLevel + 1);
                    }
                    outputSpaces(indentationLevel, out);
                    out.println("</" + xml.label() + ">");
                }
            } else {
                out.println(" />");
            }
        } else {
            out.println(xml);
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

        XMLTree xml = new XMLTree2("ExampleXML.xml");

        out.print("Enter the name of a tag: ");
        String tag = in.nextLine();
        while (!tag.equals("")) {
            int count = tagCount(xml, tag);
            out.println("The tag <" + tag + "> appears " + count + " times.");
            out.println();
            out.print("Enter the name of a tag: ");
            tag = in.nextLine();
        }

        out.println();
        out.println("The text nodes:");
        outputTextNodes(xml, out);

        out.println();
        out.println("The XML:");
        outputXML(xml, out, 0);

        in.close();
        out.close();
    }

}
