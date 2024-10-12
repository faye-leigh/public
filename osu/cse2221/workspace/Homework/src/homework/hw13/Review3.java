package homework.hw13;
import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * CSE 2221 HW #13.
 *
 * @author Faye Leigh
 *
 */
public final class Review3 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Review3() {
    }

    /**
     * Problem 1(a). Returns the product of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to multiply
     * @return the product of the digits of {@code n}
     * @clears n
     * @ensures productOfDigits1 = [product of the digits of n]
     */
    private static NaturalNumber productOfDigits1(NaturalNumber n) {
        final NaturalNumber ten = new NaturalNumber2(10);
        NaturalNumber result = new NaturalNumber2(n);

        if (n.compareTo(ten) < 0) {
            n.clear();
        } else {
            result = new NaturalNumber2(n.divideBy10());
            result.multiply(productOfDigits1(n));
        }

        return result;
    }

    /**
     * Problem 1(b). Returns the product of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to multiply
     * @return the product of the digits of {@code n}
     * @ensures productOfDigits2 = [product of the digits of n]
     */
    private static NaturalNumber productOfDigits2(NaturalNumber n) {
        final NaturalNumber ten = new NaturalNumber2(10);
        NaturalNumber sum = new NaturalNumber2(n);
        NaturalNumber result = new NaturalNumber2(n);

        if (n.compareTo(ten) > -1) {
            result = new NaturalNumber2(sum.divideBy10());
            result.multiply(productOfDigits2(sum));
        }

        return result;
    }

    /**
     * Problem 2. Reports the value of {@code n} as an {@code int}, when
     * {@code n} is small enough.
     *
     * @param n
     *            the given {@code NaturalNumber}
     * @return the value
     * @requires n <= Integer.MAX_VALUE
     * @ensures toInt = n
     */
    private static int toInt(NaturalNumber n) {
        assert n.canConvertToInt() : "Violation of: n <= Integer.MAX_VALUE";

        final int ten = 10;
        int result = 0;

        if (!n.isZero()) {
            int digit = n.divideBy10();
            result = digit + toInt(n) * ten;
            n.multiplyBy10(digit);
        }

        return result;
    }

    /**
     * Problem 3. Reports whether the given tag appears in the given
     * {@code XMLTree}.
     *
     * @param xml
     *            the {@code XMLTree}
     * @param tag
     *            the tag name
     * @return true if the given tag appears in the given {@code XMLTree}, false
     *         otherwise
     * @ensures <pre>
     * findTag =
     *    [true if the given tag appears in the given {@code XMLTree}, false otherwise]
     * </pre>
     */
    private static boolean findTag(XMLTree xml, String tag) {
        boolean tagFound = false;
        int index = 0;
//        xml.label(); // debug

        if (xml.isTag()) {
            if (xml.label().equals(tag)) {
                tagFound = true;
            } else {
                while (!tagFound && index < xml.numberOfChildren()) {
                    XMLTree child = xml.child(index);
                    if (findTag(child, tag)) {
                        tagFound = true;
                    }
                    index++;
                }
            }
        }

        return tagFound;
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

        final NaturalNumber test1 = new NaturalNumber2(1234);
        out.println("Before: " + test1);
        out.println("Product: " + productOfDigits1(test1));
        out.println("After: " + test1);

        final NaturalNumber test2 = new NaturalNumber2(123);
        out.println();
        out.println("Before: " + test2);
        out.println("Product: " + productOfDigits2(test2));
        out.println("After: " + test2);

        final NaturalNumber test3 = new NaturalNumber2(1);
        out.println();
        out.println("Before: " + test3);
        out.println("Int: " + toInt(test3));
        out.println("After: " + test3);

        XMLTree xml = new XMLTree1("ExampleXML.xml");
        out.println();
        out.println("Tag found: " + findTag(xml, "child"));
        out.println(xml.child(0).label());
        in.close();
        out.close();
    }

}
