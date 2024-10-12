package labs.lab05;

import java.util.Arrays;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * CSE 2221 Lab #5 - program to convert an arbitrary number of cents to coins
 * using the greedy method.
 *
 * @author Faye Leigh
 */
public final class CoinChange2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CoinChange2() {
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
        final int centsPerDollar = 100, centsPerHalf = 50, centsPerQuarter = 25,
                centsPerDime = 10, centsPerNickel = 5, centsPerPenny = 1,
                coinTypes = 6;
        final int[] coinValue = { centsPerDollar, centsPerHalf, centsPerQuarter,
                centsPerDime, centsPerNickel, centsPerPenny };
        int cents = 0;
        int[] coinCount = new int[coinTypes];

        out.print("Enter a number of cents: ");
        cents = in.nextInteger();

        //Calculate number of each coin in order of decreasing value
        for (int i = 0; i < coinTypes; i++) {
            coinCount[i] = cents / coinValue[i];
            cents = cents % coinValue[i];
        }

        out.println("Number of coins");
        out.println("[Dollar, Half-dollar, Quarter, Dime, Nickel, Penny]");
        out.println(Arrays.toString(coinCount));

        in.close();
        out.close();
    }

}
