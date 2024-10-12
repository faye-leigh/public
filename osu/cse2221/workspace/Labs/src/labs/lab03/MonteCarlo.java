package labs.lab03;

import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * CSE 2221 Lab #3. Monte Carlo Estimate: compute percentage of pseudo-random
 * points in a [0.0,2.0)x[0.0,2.0) square that land in the area of a circle of
 * radius 1 centered at (1.0,1.0).
 *
 * @author Faye Leigh
 */
public final class MonteCarlo {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private MonteCarlo() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        /*
         * Variables
         */
        final double squareSize = 2.0; //Side length of square
        final double circleX = 1.0; //x coordinate of circle center
        final double circleY = 1.0; //y coordinate of circle center
        final double circleRadius = 1.0;
        /*
         * Open input and output streams
         */
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();
        /*
         * Ask user for number of points to generate
         */
        output.print("Number of points: ");
        int n = input.nextInteger();
        /*
         * Declare counters and initialize them
         */
        int ptsInInterval = 0, ptsInSubinterval = 0;
        Random rnd = new Random1L(); //Create pseudo-random number generator
        /*
         * Generate points and count how many fall in circle centered at
         * (1.0,1.0) of radius 1.
         */
        while (ptsInInterval < n) {
            /*
             * Generate pseudo-random numbers in [0.0,2.0) interval for x and y
             * coordinates
             */
            double x = rnd.nextDouble() * squareSize;
            double y = rnd.nextDouble() * squareSize;
            ptsInInterval++; //Increment total number of generated points
            /*
             * Check if point is in circle area and increment counter if it is
             */
            double d = Math.sqrt(
                    Math.pow(x - circleX, 2.0) + Math.pow(y - circleY, 2.0));
            if (d < circleRadius) {
                ptsInSubinterval++;
            }
        }
        /*
         * Estimate area the circle with ratio of points in subinterval and
         * interval
         */
        double estimate = (squareSize * squareSize * ptsInSubinterval)
                / ptsInInterval;
        output.println("Estimate of area: " + estimate);
        /*
         * Close input and output streams
         */
        input.close();
        output.close();
    }

}
