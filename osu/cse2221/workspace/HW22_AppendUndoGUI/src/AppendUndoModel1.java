import components.stack.Stack;
import components.stack.Stack1L;

/**
 * Model class.
 *
 * @author Faye Leigh
 */
public final class AppendUndoModel1 implements AppendUndoModel {

    /**
     * Input variable.
     */
    private String input;

    /**
     * Output variable.
     */
    private Stack<String> output;

    /**
     * Default constructor.
     */
    public AppendUndoModel1() {
        /*
         * Initialize model; input starts as empty string, output starts as
         * empty stack
         */
        this.input = "";
        this.output = new Stack1L<String>();
    }

    @Override
    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public String input() {
        return this.input;
    }

    @Override
    public Stack<String> output() {
        return this.output;
    }

}
