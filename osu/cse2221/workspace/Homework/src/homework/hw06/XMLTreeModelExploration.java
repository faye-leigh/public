package homework.hw06;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and SpotBugs warnings).
 *
 * @author P. Bucci
 */
public final class XMLTreeModelExploration {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private XMLTreeModelExploration() {
        // no code needed here
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
         * 1. (a)
         */
//        String input = in.nextLine();
//        for (int i = 0; i < input.length(); i++) {
//            if (Character.isUpperCase(input.charAt(i))) {
//                out.print(input.charAt(i));
//            }
//        }
        /*
         * 1. (b)
         */
//        String input = in.nextLine();
//        for (int i = 1; i < input.length(); i += 2) {
//            out.print(input.charAt(i));
//        }
        /*
         * 1. (c)
         */
//        final String vowels = "aeiouAEIOU";
//        boolean matchFound = false;
//        String input = in.nextLine();
//        /*
//         * Check each character the input string
//         */
//        for (int i = 0; i < input.length(); i++) {
//            matchFound = false; //Reset flag for every letter
//            int j = 0;
//
//            /*
//             * Check if char is a vowel and print an underscore if true
//             */
//            while (!matchFound && j < vowels.length()) {
//                if (input.charAt(i) == vowels.charAt(j)) {
//                    out.print("_");
//                    matchFound = true; //Exit loop when vowel is found
//                }
//                j++;
//            }
//            /*
//             * Print original char if it is not a vowel
//             */
//            if (!matchFound) {
//                out.print(input.charAt(i));
//            }
//        }
        /*
         * 1. (d)
         */
//        final String vowels = "aeiouAEIOU";
//        boolean matchFound = false;
//        String input = in.nextLine();
//        int vowelCount = 0;
//
//        /*
//         * Check each character the input string
//         */
//        for (int i = 0; i < input.length(); i++) {
//            matchFound = false; //Reset flag for every letter
//            int j = 0;
//
//            /*
//             * Check if char is a vowel and increment counter if true
//             */
//            while (!matchFound && j < vowels.length()) {
//                if (input.charAt(i) == vowels.charAt(j)) {
//                    vowelCount++;
//                    matchFound = true; //Exit loop when vowel is found
//                }
//                j++;
//            }
//        }
//        out.println(vowelCount);
        /*
         * 1. (e)
         */
//        final String vowels = "aeiouAEIOU";
//        boolean matchFound = false;
//        String input = in.nextLine();
//
//        out.print("Vowel positions:");
//        /*
//         * Check each character the input string
//         */
//        for (int i = 0; i < input.length(); i++) {
//            matchFound = false; //Reset flag for every letter
//            int j = 0;
//
//            /*
//             * Check if char is a vowel and print position if true
//             */
//            while (!matchFound && j < vowels.length()) {
//                if (input.charAt(i) == vowels.charAt(j)) {
//                    out.print(" " + i);
//                    matchFound = true; //Exit loop when vowel is found
//                }
//                j++;
//            }
//        }
        /*
         * 2.
         */
//        int[] a = { 1, 2, 3, 4, 5, 4, 3, 2, 1, 0 };
//
//        out.println(Arrays.toString(a));
//
//        int i = 1;
//        while (i < 10) {
//            a[i] = a[i] + a[i - 1];
//            i++;
//        }
//
//        out.println(Arrays.toString(a));
        /*
         * 3.
         */
//        final int[] a = { 1, 2, 3, 4, 5, -1 };
//        int min = a[0], max = a[0];
//
//        for (int i = 0; i < a.length; i++) {
//            if (a[i] < min) {
//                min = a[i];
//            }
//            if (a[i] > max) {
//                max = a[i];
//            }
//        }
//        out.println(min + " " + max);
        /*
         * 4.
         */
        final int[] a = { 1, 2, 3, 3, 4, 5 };
        boolean isOrdered = true;
        int i = 1;

        while (isOrdered && i < a.length) {
            if (a[i] < a[i - 1]) {
                isOrdered = false;
            }
            i++;
        }
        out.println(isOrdered);

        in.close();
        out.close();
    }

}
