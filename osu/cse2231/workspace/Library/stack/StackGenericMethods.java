
import components.queue.Queue1L;
import components.stack.Stack;

/**
 * OSU Components {@code Queue} represented as {@link components.sequence
 * components.sequence} with implementations of primary methods.
 *
 * @param <T>
 *            type of {@code Queue} entries
 * @author Faye Leigh
 *
 */
public class StackGenericMethods<T> extends Queue1L<T> {

    /**
     * Shifts entries between {@code leftStack} and {@code rightStack}, keeping
     * reverse of the former concatenated with the latter fixed, and resulting
     * in length of the former equal to {@code newLeftLength}.
     *
     * @param <T>
     *            type of {@code Stack} entries
     * @param leftStack
     *            the left {@code Stack}
     * @param rightStack
     *            the right {@code Stack}
     * @param newLeftLength
     *            desired new length of {@code leftStack}
     * @updates leftStack, rightStack
     * @requires <pre>
     * 0 <= newLeftLength  and
     * newLeftLength <= |leftStack| + |rightStack|
     * </pre>
     * @ensures <pre>
     * rev(leftStack) * rightStack = rev(#leftStack) * #rightStack  and
     * |leftStack| = newLeftLength}
     * </pre>
     */
    @SuppressWarnings("unused")
    private static <T> void setLengthOfLeftStack(Stack<T> leftStack,
            Stack<T> rightStack, int newLeftLength) {
        assert newLeftLength >= 0 : "Violation of: newLeftLength >= 0";
        assert newLeftLength <= leftStack.length() + rightStack
                .length() : "Violation of: newLeftLength <= |leftStack| + |rightStack|";

        if (newLeftLength > leftStack.length()) {
            for (int i = 0; i < leftStack.length() - newLeftLength; i++) {
                rightStack.push(leftStack.pop());
            }
        } else {
            for (int i = 0; i < rightStack.length() - newLeftLength; i++) {
                leftStack.push(rightStack.pop());
            }
        }
    }

}
