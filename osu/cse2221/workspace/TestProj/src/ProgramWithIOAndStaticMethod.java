import components.map.Map;
import components.map.Map1L;
import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class ProgramWithIOAndStaticMethod {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ProgramWithIOAndStaticMethod() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    private static int myMethod(NaturalNumber n) {

        int d = n.divideBy10(), dSig = d;

        if (!n.isZero()) {
            dSig = myMethod(n);
        }

        n.multiplyBy10(d);

        return dSig;
    }

    public static boolean isSubset(Set<Integer> s1, Set<Integer> s2) {

        boolean isSubset = true;

        if (s1.size() > 0) {
            int i = s1.removeAny();
            if (!s2.contains(i) || !isSubset(s1, s2)) {
                isSubset = false;
            }
            s1.add(i);
        }

        return isSubset;
    }

    public static Map<String, Integer> wordCounts(SimpleReader in) {
        Map<String, Integer> wordCounts = new Map1L<>();

        while (!in.atEOS()) {
            String word = in.nextLine();

            if (!wordCounts.hasKey(word)) {
                wordCounts.add(word, 1);
            } else {
                Map.Pair<String, Integer> p = wordCounts.remove(word);
                wordCounts.add(word, p.value() + 1);
            }
        }

        return wordCounts;
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
        NaturalNumber n = new NaturalNumber2(7);
        Set<Integer> s1 = new Set1L<>();
        s1.add(1);
        s1.add(2);
        s1.add(5);
        Set<Integer> s2 = new Set1L<>();
        s2.add(1);
        s2.add(2);
        s2.add(3);
        s2.add(4);

        out.println(myMethod(n));
        out.println(n);

        out.println(isSubset(s1, s2));
        out.println(s1);
        out.println(s2);

        SimpleReader t = new SimpleReader1L("test/test.txt");
        out.println(wordCounts(t));

        int[] data = { 4, 8, 2, 5, 1, 2, 8, 7, 6 };
        int x = 0;
        for (int i = 1; i < data.length; i++) {
            if (data[i] >= data[x]) {
                x = i;
            }
        }
        out.println(x);

        NaturalNumber[] array = new NaturalNumber[5];
        array[0] = new NaturalNumber2(5);
        for (int i = 1; i < array.length; i++) {
            if (i % 2 == 0) {
                array[i] = new NaturalNumber2(array[i - 1]);
            } else {
                array[i] = array[i - 1];
            }
            array[i].decrement();
        }
        for (int i = 0; i < array.length; i++) {
            out.println(array[i]);
        }

        in.close();
        out.close();
    }

}
