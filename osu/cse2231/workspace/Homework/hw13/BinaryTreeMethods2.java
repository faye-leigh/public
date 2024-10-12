

import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;

/**
 * OSU CSE 2231 HW #12. BinaryTree and Recursion I.
 *
 * @author Faye Leigh
 * @param <T>
 */
public class BinaryTreeMethods2<T> extends BinaryTree1<T> {
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
}
