
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.tree.Tree;
import components.tree.Tree1;

/**
 * Generic methods for OSU Components {@code Tree}.
 *
 * @param <T>
 *            type of {@code Tree} entries
 * @author Faye Leigh
 *
 */
public class TreeGenericMethods<T> extends Tree1<T> {
    /**
     * Returns the size of the given {@code Tree<T>} recursively.
     *
     * @param <T>
     *            the type of the {@code Tree} node labels
     * @param t
     *            the {@code Tree} whose size to return
     * @return the size of the given {@code Tree}
     * @ensures size = |t|
     */
    public static <T> int sizeR(Tree<T> t) {
        int size = 0;
        if (t.height() > 0) {
            size++;
            /*
             * Find size of each subtree and add to total size.
             */
            int subCount = t.numberOfSubtrees();
            for (int i = 0; i < subCount; i++) {
                Tree<T> subTree = t.removeSubtree(i);
                size += sizeR(subTree);
                t.addSubtree(i, subTree);
            }
        }
        return size;
    }

    /**
     * Returns the size of the given {@code Tree<T>} iteratively.
     *
     * @param <T>
     *            the type of the {@code Tree} node labels
     * @param t
     *            the {@code Tree} whose size to return
     * @return the size of the given {@code Tree}
     * @ensures size = |t|
     */
    @SuppressWarnings("unused")
    public static <T> int sizeI(Tree<T> t) {
        int size = 0;
        for (T root : t) {
            size++;
        }
        return size;
    }

    /**
     * Returns the height of the given {@code Tree<T>}.
     *
     * @param <T>
     *            the type of the {@code Tree} node labels
     * @param t
     *            the {@code Tree} whose height to return
     * @return the height of the given {@code Tree}
     * @ensures height = ht(t)
     */
    public static <T> int height(Tree<T> t) {
        int height = 0;
        if (t.size() > 0) {
            height++;
            /*
             * Find heights of each subtree and add the height of tallest
             * subtree to total height.
             */
            int subCount = t.numberOfSubtrees();
            int largest = 0;
            for (int i = 0; i < subCount; i++) {
                Tree<T> subTree = t.removeSubtree(i);
                int subHeight = height(subTree);
                if (subHeight > largest) {
                    largest = subHeight;
                }
                t.addSubtree(i, subTree);
            }
            height += largest;
        }
        return height;
    }

    /**
     * Returns the largest integer in the given {@code Tree<Integer>}.
     *
     * @param t
     *            the {@code Tree<Integer>} whose largest integer to return
     * @return the largest integer in the given {@code Tree<Integer>}
     * @requires |t| > 0
     * @ensures <pre>
     * max is in labels(t)  and
     * for all i: integer where (i is in labels(t)) (i <= max)
     * </pre>
     */
    public static int max(Tree<Integer> t) {
        int max = t.root();
        if (t.size() > 1) {
            /*
             * Find the max int from all subtrees and compare with current max.
             */
            int subCount = t.numberOfSubtrees();
            for (int i = 0; i < subCount; i++) {
                Tree<Integer> subTree = t.removeSubtree(i);
                int root = max(subTree);
                if (root > max) {
                    max = root;
                }
                t.addSubtree(i, subTree);
            }
        }
        return max;
    }

    /**
     * Main method.
     *
     * @param args
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        out.close();
    }
}
