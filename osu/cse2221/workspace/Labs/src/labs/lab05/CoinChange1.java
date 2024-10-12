package labs.lab05;

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
public final class CoinChange1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CoinChange1() {
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
                centsPerDime = 10, centsPerNickel = 5;
        int cents = 0, dollar = 0, halfDollar = 0, quarter = 0, dime = 0,
                nickel = 0, penny = 0;

        out.print("Enter a number of cents: ");
        cents = in.nextInteger();

        //Calculate number of each coin in order of decreasing value
        dollar = cents / centsPerDollar;
        cents = cents % centsPerDollar;
        halfDollar = cents / centsPerHalf;
        cents = cents % centsPerHalf;
        quarter = cents / centsPerQuarter;
        cents = cents % centsPerQuarter;
        dime = cents / centsPerDime;
        cents = cents % centsPerDime;
        nickel = cents / centsPerNickel;
        cents = cents % centsPerNickel;
        penny = cents;

        out.println("Number of coins");
        out.println("Dollar: " + dollar);
        out.println("Half-dollar: " + halfDollar);
        out.println("Quarter: " + quarter);
        out.println("Dime: " + dime);
        out.println("Nickel: " + nickel);
        out.println("Penny: " + penny);

        in.close();
        out.close();
    }

}
