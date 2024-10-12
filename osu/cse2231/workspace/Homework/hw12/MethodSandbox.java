
import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Method sandbox.
 *
 * @author Faye Leigh
 */
public final class MethodSandbox {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private MethodSandbox() {
        // no code needed here
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
    public static <T> int sizeRecursive(BinaryTree<T> t) {
        int size = 0;
        if (t.height() > 0) {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            T tmp = t.disassemble(left, right);
            size += sizeRecursive(left);
            size += sizeRecursive(right);
            size++;
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
            s.append("(");
            s.append(treeToString(left));
            s.append(treeToString(right));
            s.append(")");
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
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        BinaryTree<String> bl = new BinaryTree1<String>();
        BinaryTree<String> left = bl.newInstance();
        BinaryTree<String> right = bl.newInstance();
        bl.assemble("b", left, right);
        BinaryTree<String> br = bl.newInstance();
        br.assemble("c", left.newInstance(), right.newInstance());
        BinaryTree<String> bn = bl.newInstance();
        bn.assemble("a", bl, br);
//        String s = bn.disassemble(left, right);
        out.println(bn);
//        out.println(s);
        out.println(bn.height());
        out.println(sizeIterative(bn));
        out.println(treeToString(bn));
        BinaryTree<Integer> i1 = new BinaryTree1<Integer>();
        BinaryTree<Integer> i2 = i1.newInstance();
        BinaryTree<Integer> i3 = i1.newInstance();
        i2.assemble(2, i2.newInstance(), i3.newInstance());
        i3.assemble(2, i2.newInstance(), i3.newInstance());
        i1.assemble(1, i2, i3);
        out.println(i1);
        out.println(copy(i1));
        out.println(treeToString(i1));
        out.close();
    }

}
