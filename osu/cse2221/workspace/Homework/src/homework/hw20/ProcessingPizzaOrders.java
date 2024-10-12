package homework.hw20;

import components.map.Map;
import components.map.Map1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * CSE 2221 HW #20.
 *
 * @author Faye Leigh
 */
public final class ProcessingPizzaOrders {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ProcessingPizzaOrders() {
    }

    /**
     * Inputs a "menu" of words (items) and their prices from the given file and
     * stores them in the given {@code Map}.
     *
     * @param fileName
     *            the name of the input file
     * @param priceMap
     *            the word -> price map
     * @replaces priceMap
     * @requires <pre>
     * [file named fileName exists but is not open, and has the
     *  format of one "word" (unique in the file) and one price (in cents)
     *  per line, with word and price separated by ','; the "word" may
     *  contain whitespace but no ',']
     * </pre>
     * @ensures [priceMap contains word -> price mapping from file fileName]
     */
    private static void getPriceMap(String fileName,
            Map<String, Integer> priceMap) {
        SimpleReader file = new SimpleReader1L(fileName);

        while (!file.atEOS()) {
            String line = file.nextLine();
            int commaIndex = line.indexOf(',');
            String item = line.substring(0, commaIndex);
            String price = line.substring(commaIndex + 1);

            priceMap.add(item, Integer.parseInt(price));
        }

        file.close();
    }

    /**
     * Input one pizza order and compute and return the total price.
     *
     * @param input
     *            the input stream
     * @param sizePriceMap
     *            the size -> price map
     * @param toppingPriceMap
     *            the topping -> price map
     * @return the total price (in cents)
     * @updates input
     * @requires <pre>
     * input.is_open and
     * [input.content begins with a pizza order consisting of a size
     *  (something defined in sizePriceMap) on the first line, followed
     *  by zero or more toppings (something defined in toppingPriceMap)
     *  each on a separate line, followed by an empty line]
     * </pre>
     * @ensures <pre>
     * input.is_open and
     * #input.content = [one pizza order (as described
     *              in the requires clause)] * input.content and
     * getOneOrder = [total price (in cents) of that pizza order]
     * </pre>
     */
    private static int getOneOrder(SimpleReader input,
            Map<String, Integer> sizePriceMap,
            Map<String, Integer> toppingPriceMap) {
        assert input.isOpen() : "Violation of: input.is_open";

        String size = input.nextLine();
        String topping = input.nextLine();
        int price = sizePriceMap.value(size);

        while (!topping.isBlank()) {
            price += toppingPriceMap.value(topping);
            topping = input.nextLine();
        }

        return price;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        Map<String, Integer> toppingsMap = new Map1L<>();
        Map<String, Integer> sizeMap = new Map1L<>();
        String size = "data/hw20/sizes.txt";
        String toppings = "data/hw20/toppings.txt";
        String order = "data/hw20/orders.txt";
        SimpleReader orderFile = new SimpleReader1L(order);

        getPriceMap(toppings, toppingsMap);
        out.println(toppingsMap);
        getPriceMap(size, sizeMap);
        out.println(sizeMap);
        out.println(getOneOrder(orderFile, sizeMap, toppingsMap));

        orderFile.close();
        out.close();
    }
}
