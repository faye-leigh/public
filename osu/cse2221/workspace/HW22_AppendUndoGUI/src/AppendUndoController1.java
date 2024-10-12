import components.stack.Stack;

/**
 * Controller class.
 *
 * @author Bruce W. Weide
 * @author Paolo Bucci
 */
public final class AppendUndoController1 implements AppendUndoController {

    /**
     * Model object.
     */
    private final AppendUndoModel model;

    /**
     * View object.
     */
    private final AppendUndoView view;

    /**
     * Updates view to display model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     */
    private static void updateViewToMatchModel(AppendUndoModel model,
            AppendUndoView view) {
        /*
         * Get model info
         */
        String input = model.input();
        Stack<String> output = model.output();
        String outputString = "";
        /*
         * Update status of undo button, and if output is not empty, combine
         * elements of the output stack into the output string
         */
        if (output.length() > 0) {
            for (String s : output) {
                outputString = s + outputString;
            }
            view.updateUndoAllowed(true);
        } else {
            view.updateUndoAllowed(false);
        }

        /*
         * Update view to reflect changes in model
         */
        view.updateInputDisplay(input);
        view.updateOutputDisplay(outputString);
    }

    /**
     * Constructor; connects {@code this} to the model and view it coordinates.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public AppendUndoController1(AppendUndoModel model, AppendUndoView view) {
        this.model = model;
        this.view = view;
        /*
         * Update view to reflect initial value of model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes reset event.
     */
    @Override
    public void processResetEvent() {
        /*
         * Set model.input to empty string, clear model.output
         */
        this.model.setInput("");
        this.model.output().clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes append event.
     */
    @Override
    public void processAppendEvent(String input) {
        /*
         * Add input string to model.output, set model.input to empty string
         */
        this.model.output().push(input);
        this.model.setInput("");
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes undo event.
     */
    @Override
    public void processUndoEvent() {
        /*
         * Set model.input to first element of model.output, remove that element
         * from model.output
         */
        this.model.setInput(this.model.output().pop());
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

}
