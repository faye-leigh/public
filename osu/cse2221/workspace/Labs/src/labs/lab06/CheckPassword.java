package labs.lab06;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * CSE 2221 Lab #6.
 *
 * @author Faye Leigh
 */
public final class CheckPassword {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CheckPassword() {
    }

    /**
     * Checks whether the given String satisfies the OSU criteria for a valid
     * password. Prints an appropriate message to the given output stream.
     *
     * @param passwordCandidate
     *            the String to check
     * @param out
     *            the output stream
     */
    private static void checkPassword(String passwordCandidate,
            SimpleWriter out) {
        final int minLength = 8, criteriaMin = 2;
        int criteriaCount = 0;

        if (passwordCandidate.length() >= minLength) {
            out.println("Password is long enough.");
            out.println();

            if (containsLowerCaseLetter(passwordCandidate)) {
                out.println("Password contains a lower case letter.");
                criteriaCount++;
            } else {
                out.println(
                        "Password must contain at least one lower case letter.");
            }

            if (containsUpperCaseLetter(passwordCandidate)) {
                out.println("Password contains an upper case letter.");
                criteriaCount++;
            } else {
                out.println(
                        "Password must contain at least one upper case letter.");
            }

            if (containsDigit(passwordCandidate)) {
                out.println("Password contains a digit.");
                criteriaCount++;
            } else {
                out.println("Password must contain at least one digit.");
            }

            out.println();
            if (criteriaCount >= criteriaMin) {
                out.println("Password accepted.");
            } else {
                out.println("Password rejected.");
            }
        } else {
            out.println("Password must be at least 8 characters long.");
            out.println();
            out.println("Password rejected.");
        }
    }

    /**
     * Checks if the given String contains a lower case letter.
     *
     * @param str
     *            the String to check
     * @return true if str contains a lower case letter, false otherwise
     */
    private static boolean containsLowerCaseLetter(String str) {
        boolean lowerCaseFound = false;
        int i = 0;

        while (i < str.length() && !lowerCaseFound) {
            if (Character.isLowerCase(str.charAt(i))) {
                lowerCaseFound = true;
            }
            i++;
        }
        return lowerCaseFound;
    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param str
     *            the String to check
     * @return true if str contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String str) {
        boolean upperCaseFound = false;
        int i = 0;

        while (i < str.length() && !upperCaseFound) {
            if (Character.isUpperCase(str.charAt(i))) {
                upperCaseFound = true;
            }
            i++;
        }
        return upperCaseFound;
    }

    /**
     * Checks if the given String contains a digit.
     *
     * @param str
     *            the String to check
     * @return true if str contains a digit, false otherwise
     */
    private static boolean containsDigit(String str) {
        boolean digitFound = false;
        int i = 0;

        while (i < str.length() && !digitFound) {
            if (Character.isDigit(str.charAt(i))) {
                digitFound = true;
            }
            i++;
        }
        return digitFound;
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
        String passwordCandidate;

        out.println("Please come up with a new password;");
        out.println("    * more than 8 characters long");
        out.println("Must satisfy 2 of the following:");
        out.println("    * lower case letters (a, b, c, ...)");
        out.println("    * upper case letters (A, B, C, ...)");
        out.println("    * digits (1, 2, 3, ...)");

        passwordCandidate = in.nextLine();
        checkPassword(passwordCandidate, out);

        in.close();
        out.close();
    }

}
