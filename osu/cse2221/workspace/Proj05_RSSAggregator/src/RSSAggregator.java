import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * CSE 2221 Project #5. Program to convert an XML RSS (version 2.0) feed from a
 * given URL into the corresponding HTML output file.
 *
 * @author Faye Leigh
 *
 */
public final class RSSAggregator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSAggregator() {
    }

    /**
     * Processes one XML RSS (version 2.0) feed from a given URL converting it
     * into the corresponding HTML output file.
     *
     * @param url
     *            the URL of the RSS feed
     * @param file
     *            the name of the HTML output file
     * @param out
     *            the output stream to report progress or errors
     * @updates out.content
     * @requires out.is_open
     * @ensures <pre>
     * [reads RSS feed from url, saves HTML document with table of news items
     *   to file, appends to out.content any needed messages]
     * </pre>
     */
    private static void processFeed(String url, String file, SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        XMLTree xml = new XMLTree1(url);
        SimpleWriter html = new SimpleWriter1L(file);

        if (xml.label().equals("rss") && xml.hasAttribute("version")
                && xml.attributeValue("version").equals("2.0")) {
            int channelIndex = getChildElement(xml, "channel");
            XMLTree channel = xml.child(channelIndex);
            /*
             * Get indices of title, link, and description tags
             */
            int titleIndex = getChildElement(channel, "title"),
                    linkIndex = getChildElement(channel, "link"),
                    descriptionIndex = getChildElement(channel, "description"),
                    dateIndex, sourceIndex;
            /*
             * Get title, link, and description
             */
            String title, link, description;
            if (channel.child(titleIndex).numberOfChildren() > 0) {
                title = channel.child(titleIndex).child(0).label();
            } else {
                title = "No title";
            }
            if (channel.child(descriptionIndex).numberOfChildren() > 0) {
                description = channel.child(descriptionIndex).child(0).label();
            } else {
                description = "No description";
            }
            link = channel.child(linkIndex).child(0).label();
            /*
             * Print html header
             */
            html.println("<html><head><title>" + title + "</title></head>");
            /*
             * Print page title linked to feed link and feed description
             */
            html.println("<h1><a href=" + link + ">" + title + "</a></h1>");
            html.println("<p>" + description + "</p>");
            /*
             * Start table
             */
            html.println("<table border=\"1\">");
            html.println("<tr><th>Date</th><th>Source</th><th>News</th></tr>");
            /*
             * Process each item tag in channel
             */
            for (int i = 0; i < channel.numberOfChildren(); i++) {
                if (channel.child(i).label().equals("item")) {
                    XMLTree item = channel.child(i);
                    /*
                     * Get indices of title, description, link, publication
                     * date, and source tags
                     */
                    titleIndex = getChildElement(item, "title");
                    descriptionIndex = getChildElement(item, "description");
                    linkIndex = getChildElement(item, "link");
                    dateIndex = getChildElement(item, "pubDate");
                    sourceIndex = getChildElement(item, "source");

                    html.println("<tr>"); // Start of table row.
                    /*
                     * Check that there is a publication date and add
                     * publication date to table if available
                     */
                    if (dateIndex > -1) {
                        html.println(
                                "<td>" + item.child(dateIndex).child(0).label()
                                        + "</td>");
                    } else {
                        html.println("<td>No date available</td>");
                    }
                    /*
                     * Check that there is a source tag and that it has a text
                     * child. If it has both, add the text to table with
                     * hyperlink to url. If only url exists, add url to table as
                     * hyperlink
                     */
                    if (sourceIndex > -1) {
                        if (item.child(sourceIndex).numberOfChildren() > 0) {
                            html.println("<td><a href=\""
                                    + item.child(sourceIndex)
                                            .attributeValue("url")
                                    + "\">"
                                    + item.child(sourceIndex).child(0).label()
                                    + "</a></td>");
                        } else {
                            html.println(
                                    "<td><a href=\""
                                            + item.child(sourceIndex)
                                                    .attributeValue("url")
                                            + "\">"
                                            + item.child(sourceIndex)
                                                    .attributeValue("url")
                                            + "</a></td>");
                        }
                    } else {
                        html.println("<td>No source available</td>");
                    }
                    /*
                     * Check that there is a link and add link as an href
                     * attribute if available
                     */
                    if (linkIndex > -1) {
                        html.print("<td><a href=\""
                                + item.child(linkIndex).child(0).label()
                                + "\">");
                    } else {
                        html.print("<td>");
                    }
                    /*
                     * Check that there is a title and that it is not empty and
                     * add to table if available. If no title, check that there
                     * is a description and that it is not empty and add to
                     * table if available. If neither are available, add 'No
                     * title available' to table
                     */
                    if (titleIndex > -1
                            && item.child(titleIndex).numberOfChildren() > 0) {
                        html.print(item.child(titleIndex).child(0).label());
                    } else if (descriptionIndex > -1 && item
                            .child(descriptionIndex).numberOfChildren() > 0) {
                        html.print(
                                item.child(descriptionIndex).child(0).label());
                    } else {
                        html.print("No title available");
                    }
                    /*
                     * Finish line depending on whether link was available
                     */
                    if (linkIndex > -1) {
                        html.println("</a></td>");
                    } else {
                        html.println("</td>");
                    }
                }
            }
            html.println("</table></body></html>");
        } else {
            html.println("<html><head><title>Error</title></head>");
            html.println("<body><p>Invalid RSS feed</p></body></html>");
        }
        html.close();
    }

    /**
     * Processes XML file containing information on various RSS feeds and
     * converts it into an HTML file linking to corresponding HTML files for
     * each feed.
     *
     * @param xml
     *            the xml file containing RSS feed information
     * @param out
     *            the output html file
     * @updates out.content
     * @requires [the root of xml is a <feeds> tag] and out.is_open
     * @ensures <pre>
     * [reads RSS feed information from xml, saves HTML document with list of
     * links to HTML pages to file, appends to out.content any needed messages]
     * </pre>
     */
    private static void processIndex(XMLTree xml, SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";
        assert xml != null : "Violation of: xml is not null";
        assert xml.isTag() && xml.label().equals("feeds") : ""
                + "Violation of: the label root of xml is a <feeds> tag";

        String title = xml.attributeValue("title");

        /*
         * Print html header and title to file
         */
        out.println("<html><head><title>" + title + "</title></head>");
        out.println("<body><h2>" + title + "</h2><ul>");

        /*
         * Add list elements as links to each html page
         */
        for (int i = 0; i < xml.numberOfChildren(); i++) {
            out.println("<li><a href=\"" + xml.child(i).attributeValue("file")
                    + "\">" + xml.child(i).attributeValue("name")
                    + "</a></li>");
        }

        out.println("</ul></body></html>");
    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of type tag of the {@code XMLTree}
     *         or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of type tag of the {@code XMLTree} or
     *   -1 if not found]
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
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        /*
         * Get input and output file names
         */
        out.println(
                "Enter name of XML file containing RSS 2.0 URLs (include '.xml').");
        XMLTree xml = new XMLTree1(in.nextLine());
        out.println("Enter name of output HTML file (include '.html').");
        SimpleWriter html = new SimpleWriter1L(in.nextLine());

        processIndex(xml, html);

        for (int i = 0; i < xml.numberOfChildren(); i++) {
            processFeed(xml.child(i).attributeValue("url"),
                    xml.child(i).attributeValue("file"), out);
        }

        in.close();
        out.close();
        html.close();
    }
}
