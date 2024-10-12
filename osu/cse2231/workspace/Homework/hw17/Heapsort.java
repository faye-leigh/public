
import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * OSU CSE 2231 HW #12. BinaryTree and Recursion I.
 *
 * @author Faye Leigh
 * @param <T>
 */
public class Heapsort<T> extends BinaryTree1<T> {

    /**
     * Checks if the given {@code BinaryTree<Integer>} satisfies the heap
     * ordering property according to the <= relation.
     *
     * @param t
     *            the binary tree
     * @return true if the given tree satisfies the heap ordering property;
     *         false otherwise
     * @ensures <pre>
     * satisfiesHeapOrdering = [t satisfies the heap ordering property]
     * </pre>
     */
    private static boolean satisfiesHeapOrdering(BinaryTree<Integer> t) {
        boolean satisfies = true;
        if (t.size() > 1) {
            BinaryTree<Integer> left = t.newInstance();
            BinaryTree<Integer> right = t.newInstance();
            int i = t.disassemble(left, right);
            if (left.size() > 0) {
                satisfies = i <= left.root() && satisfiesHeapOrdering(left);
            }
            if (satisfies && right.size() > 0) {
                satisfies = i <= right.root() && satisfiesHeapOrdering(right);
            }
            t.assemble(i, left, right);
        }
        return satisfies;
    }

    /**
     * Main method.
     *
     * @param args
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        BinaryTree<Integer> t = new BinaryTree1<Integer>();
        BinaryTree<Integer> left = new BinaryTree1<Integer>();
        BinaryTree<Integer> right = new BinaryTree1<Integer>();
        t.assemble(1, left, right);
        out.println(satisfiesHeapOrdering(t));
        out.close();
    }
}
