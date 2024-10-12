package labs.lab09;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * CSE 2221 Lab #9. This program inputs an XML RSS (version 2.0) feed from a
 * given URL and outputs various elements of the feed to the console.
 *
 * @author Faye Leigh
 */
public final class RSSProcessing {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSProcessing() {
    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of the {@code XMLTree} matching the
     *         given tag or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of the {@code XMLTree} matching the
     *   given tag or -1 if not found]
     * </pre>
     */
    private static int getChildElement(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";

        int index = -1, i = 0;
        boolean childFound = false;

        /*
         * Checks each child of xml and stops when matching tag label is found
         */
        while (i < xml.numberOfChildren() && !childFound) {
            if (xml.child(i).isTag() && xml.child(i).label().equals(tag)) {
                index = i;
                childFound = true;
            }
            i++;
        }
        return index;
    }

    /**
     * Processes one news item and outputs the title, or the description if the
     * title is not present, and the link (if available) with appropriate
     * labels.
     *
     * @param item
     *            the news item
     * @param out
     *            the output stream
     * @requires [the label of the root of item is an <item> tag] and
     *           out.is_open
     * @ensures out.content = #out.content * [the title (or description) and
     *          link]
     */
    private static void processItem(XMLTree item, SimpleWriter out) {
        assert item != null : "Violation of: item is not null";
        assert out != null : "Violation of: out is not null";
        assert item.isTag() && item.label().equals("item") : ""
                + "Violation of: the label root of item is an <item> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        int titleIndex = getChildElement(item, "title"),
                linkIndex = getChildElement(item, "link"),
//                dateIndex = getChildElement(item, "pubDate"),
//                sourceIndex = getChildElement(item, "source"),
                descriptionIndex = getChildElement(item, "description");

//        out.println("<tr>"); //Start of table row

        /*
         * Checks that there is a publication date tag and that it is not empty.
         * Adds publication date to table if available
         */
//        if (dateIndex > -1 && item.child(dateIndex).numberOfChildren() > 0) {
//            out.println(
//                    "<td>" + item.child(dateIndex).child(0).label() + "</td>");
//        } else {
//            out.println("<td>No date available</td>");
//        }

        /*
         * Checks that there is a source tag and that it has a text child. If it
         * has both, adds the text to table with hyperlink to url. If only url
         * exists, adds url to table
         */
//        if (sourceIndex > -1) {
//            if (item.child(sourceIndex).numberOfChildren() > 0) {
//                out.println("<td><a href=\""
//                        + item.child(sourceIndex).attributeValue("url") + "\">"
//                        + item.child(sourceIndex).child(0).label()
//                        + "</a></td>");
//            } else {
//                out.println(
//                        "<td>\"" + item.child(sourceIndex).attributeValue("url")
//                                + "\"</td>");
//            }
//        } else {
//            out.println("<td>No source available</td>");
//        }

        /*
         * Check that there is a title and that it is not empty
         */
        if (titleIndex > -1 && item.child(titleIndex).numberOfChildren() > 0) {

            /*
             * If title label is available, print title
             */
            out.println("Title: " + item.child(titleIndex).child(0).label());

            /*
             * If no title, check that there is a description and that it is not
             * empty
             */
        } else if (descriptionIndex > -1
                && item.child(descriptionIndex).numberOfChildren() > 0) {
            out.println(
                    "Title: " + item.child(descriptionIndex).child(0).label());
        } else {
            out.println("Title: No title available.");
        }

        /*
         * If link is available, print link
         */
        if (linkIndex > -1 && item.child(linkIndex).numberOfChildren() > 0) {
            out.println("Link: " + item.child(linkIndex).child(0).label());
        } else {
            out.println("Link: No link available.");
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        /*
         * Open I/O streams.
         */
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Input the source URL.
         */
        out.print("Enter the URL of an RSS 2.0 news feed: ");
        String url = in.nextLine();
        /*
         * Read XML input and initialize XMLTree. If input is not legal XML,
         * this statement will fail.
         */
        XMLTree xml = new XMLTree1(url);
        /*
         * Extract <channel> element.
         */
        XMLTree channel = xml.child(0);

        String title, description, link;
        int titleIndex = getChildElement(channel, "title"),
                descriptionIndex = getChildElement(channel, "description"),
                linkIndex = getChildElement(channel, "link");

        if (titleIndex > -1
                && channel.child(titleIndex).numberOfChildren() > 0) {
            title = channel.child(titleIndex).child(0).label();
        } else {
            title = "No title available.";
        }

        if (descriptionIndex > -1
                && channel.child(descriptionIndex).numberOfChildren() > 0) {
            description = channel.child(descriptionIndex).child(0).label();
        } else {
            description = "No description available.";
        }

        if (linkIndex > -1) {
            link = channel.child(titleIndex).child(0).label();
        } else {
            link = "No link available.";
        }

        out.println();
        out.println("Title: " + title);
        out.println("Description: " + description);
        out.println("Link: " + link);
        out.println();

        /*
         * #4 - for each item, output title (or description, if title is not
         * available) and link (if available)
         */
        out.println("Items:");
        for (int i = 0; i < channel.numberOfChildren(); i++) {
            if (channel.child(i).label().equals("item")) {
                out.println();
                processItem(channel.child(i), out);
            }
        }

        /*
         * Close I/O streams.
         */
        in.close();
        out.close();
    }

}
