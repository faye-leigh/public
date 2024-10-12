package labs.lab11;

/**
 * CSE 2221 Lab #11.
 *
 * @author Faye Leigh
 */
public final class HailstoneRevisited1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private HailstoneRevisited1() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * {@code NaturalNumber}.
     *
     * @param n
     *            the starting natural number
     * @param out
     *            the output stream
     * @updates out.content
     * @requires n > 0 and out.is_open
     * @ensures out.content = #out.content * [the Hailstone series starting with
     *          n]
     */
//    private static void generateSeries(NaturalNumber n, SimpleWriter out) {
//        final NaturalNumber one = new NaturalNumber1L(1);
//        final NaturalNumber two = new NaturalNumber1L(2);
//        final NaturalNumber three = new NaturalNumber1L(3);
//        NaturalNumber x = new NaturalNumber1L(n);
//        NaturalNumber max = new NaturalNumber1L(one);
//        int seriesLength = 1;
//
//        out.print(x.toString());
//        while (!x.equals(one)) {
//            if (x.compareTo(max) == 1) {
//                max.copyFrom(x);
//            }
//            NaturalNumber compare = new NaturalNumber1L(x);
//            if (compare.divide(two).equals(one)) {
//                x.multiply(three);
//                x.increment();
//            } else {
//                x.divide(two);
//            }
//            out.print(", " + x.toString());
//            seriesLength++;
//        }
//        out.println();
//        out.println("Series length: " + seriesLength);
//        out.println("Max value: " + max.toString());
//    }
//
//    /**
//     *
//     * @param in
//     * @param out
//     * @return output natural number
//     */
//    private static NaturalNumber getNaturalNumber(SimpleReader in,
//            SimpleWriter out) {
//        NaturalNumber output = new NaturalNumber1L();
//        boolean flag = true;
//        String input;
//
//        return output;
//
//    }
//
//    /**
//     * Main method.
//     *
//     * @param args
//     *            the command line arguments
//     */
//    public static void main(String[] args) {
//        SimpleReader in = new SimpleReader1L();
//        SimpleWriter out = new SimpleWriter1L();
//        NaturalNumber num = new NaturalNumber1L(10);
//        final NaturalNumber three = new NaturalNumber1L(2);
//        final NaturalNumber fif = new NaturalNumber1L(15);
//
//        num.divide(three);
//        out.println(num.toString());
////        out.println(num.compareTo(fif));
////        generateSeries(num, out);
//
//        in.close();
//        out.close();
//    }
//
}
