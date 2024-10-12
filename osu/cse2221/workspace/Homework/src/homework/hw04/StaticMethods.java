package homework.hw04;

import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Monte Carlo estimation for CSE 2221 HW #4.
 *
 * @author FayeLeigh
 *
 */
public final class StaticMethods {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private StaticMethods() {
    }

    /**
     * Checks whether the given point (xCoord, yCoord) is inside the circle of
     * radius 1.0 centered at the point (1.0, 1.0).
     *
     * @param xCoord
     *            the x coordinate of the point
     * @param yCoord
     *            the y coordinate of the point
     * @return true if the point is inside the circle, false otherwise
     */
    private static boolean pointIsInCircle(double xCoord, double yCoord) {
        final double r = 1.0, xCenter = 1.0, yCenter = 1.0;
        double d = 0.0; //distance between point and center of circle

        /*
         * Calculates the distance between the input point and the center of the
         * circle
         */
        d = Math.sqrt((xCoord - xCenter) * (xCoord - xCenter)
                + (yCoord - yCenter) * (yCoord - yCenter));

        return d < r; //If d is less than r, point is in the circle
    }

    /**
     * Generates n pseudo-random points in the [0.0,2.0) x [0.0,2.0) square and
     * returns the number that fall in the circle of radius 1.0 centered at the
     * point (1.0, 1.0).
     *
     * @param n
     *            the number of points to generate
     * @return the number of points that fall in the circle
     */
    private static int numberOfPointsInCircle(int n) {
        final double squareSize = 2.0;
        int ptsInCircle = 0, ptsTotal = 0;
        Random rnd = new Random1L(); //Create pseudo-random number generator

        /*
         * Generate n number of random x and y coordinates in the range
         * [0.0,2.0) and check if the points lie within the circle of radius 1.0
         * centered on (1.0,1.0)
         */
        while (ptsTotal < n) {
            double x = rnd.nextDouble() * squareSize;
            double y = rnd.nextDouble() * squareSize;
            ptsTotal++;

            if (pointIsInCircle(x, y)) { //Count number of points in circle
                ptsInCircle++;
            }
        }
        return ptsInCircle;
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

        final double squareSize = 2.0;

        out.println("Number of points: ");
        int n = in.nextInteger();

        int ptsInCircle = numberOfPointsInCircle(n);

        out.println("Points in circle / Total number of points");
        out.println(ptsInCircle + " / " + n);

        /*
         * Use ratio of points in circle to points total to estimate the area of
         * the circle
         */
        double estimate = squareSize * squareSize * ptsInCircle / n;
        out.println("Estimate of area: " + estimate);

        in.close();
        out.close();
    }
}
