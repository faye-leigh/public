

import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;

/**
 * OSU CSE 2231 HW #12. BinaryTree and Recursion I.
 *
 * @author Faye Leigh
 * @param <T>
 */
public class BinaryTreeMethods<T> extends BinaryTree1<T> {
    /**
     * Returns the size of the given {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} whose size to return
     * @return the size of the given {@code BinaryTree}
     * @ensures size = |t|
     */
    public static <T> int sizeRecursive(BinaryTree<T> t) {
        int size = 0;
        if (t.height() > 0) {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            T tmp = t.disassemble(left, right);
            size = 1 + sizeRecursive(left) + sizeRecursive(right);
            t.assemble(tmp, left, right);
        }
        return size;
    }

    /**
     * Returns the size of the given {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} whose size to return
     * @return the size of the given {@code BinaryTree}
     * @ensures size = |t|
     */
    @SuppressWarnings("unused")
    public static <T> int sizeIterative(BinaryTree<T> t) {
        int size = 0;
        for (T x : t) {
            size++;
        }
        return size;
    }

    /**
     * Returns the {@code String} prefix representation of the given
     * {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} to convert to a {@code String}
     * @return the prefix representation of {@code t}
     * @ensures treeToString = [the String prefix representation of t]
     */
    public static <T> String treeToString(BinaryTree<T> t) {
        StringBuilder s = new StringBuilder();
        if (t.size() > 0) {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            T tmp = t.disassemble(left, right);
            s.append(tmp);
            s.append("(" + treeToString(left) + treeToString(right) + ")");
            t.assemble(tmp, left, right);
        } else {
            s.append("()");
        }
        return String.valueOf(s);
    }

    /**
     * Returns a copy of the the given {@code BinaryTree}.
     *
     * @param t
     *            the {@code BinaryTree} to copy
     * @return a copy of the given {@code BinaryTree}
     * @ensures copy = t
     */
    public static BinaryTree<Integer> copy(BinaryTree<Integer> t) {
        BinaryTree<Integer> copy = t.newInstance();
        if (t.size() > 0) {
            BinaryTree<Integer> left = t.newInstance();
            BinaryTree<Integer> right = t.newInstance();
            int tmp = t.disassemble(left, right);
            copy.assemble(tmp, copy(left), copy(right));
            t.assemble(tmp, left, right);

        }
        return copy;
    }

    /**
     * Returns whether {@code x} is in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be searched for
     * @return true if t contains x, false otherwise
     * @requires IS_BST(t)
     * @ensures isInTree = (x is in labels(t))
     */
    public static <T extends Comparable<T>> boolean isInTree(BinaryTree<T> t,
            T x) {
        boolean isInTree = false;
        if (t.size() > 0) {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            T tmp = t.disassemble(left, right);
            int i = tmp.compareTo(x);
            if (i == 0) {
                isInTree = true;
            } else if (i < 0) {
                isInTree = isInTree(left, x);
            } else {
                isInTree(right, x);
            }
            t.assemble(tmp, left, right);
        }

        return isInTree;
    }

    /**
     * Removes and returns the smallest (left-most) label in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove the label
     * @return the smallest label in the given {@code BinaryTree}
     * @updates t
     * @requires IS_BST(t) and |t| > 0
     * @ensures <pre>
     * IS_BST(t)  and  removeSmallest = [the smallest label in #t]  and
     *  labels(t) = labels(#t) \ {removeSmallest}
     * </pre>
     */
    public static <T> T removeSmallest(BinaryTree<T> t) {
        T smallest = t.root();
        if (t.height() > 1) {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            T tmp = t.disassemble(left, right);
            smallest = removeSmallest(left);
            t.assemble(tmp, left, right);
        }
        return smallest;
    }

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
    @SuppressWarnings("unused")
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
}
