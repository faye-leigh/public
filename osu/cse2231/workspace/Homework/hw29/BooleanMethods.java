import static components.utilities.Tokenizer.tokens;

import components.queue.Queue;
import components.queue.Queue1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class BooleanMethods {

    /**
     * Base used in number representation.
     */
    private static final int RADIX = 10;

    /**
     * Evaluates a Boolean expression and returns its value.
     * 
     * @param tokens
     *               the {@code Queue<String>} that starts with a bool-expr string
     * @return value of the expression
     * @updates tokens
     * @requires [a bool-expr string is a prefix of tokens]
     * @ensures
     * 
     *          <pre>
     * valueOfBoolExpr =
     *   [value of longest bool-expr string at start of #tokens]  and
     * #tokens = [longest bool-expr string at start of #tokens] * tokens
     *          </pre>
     */
    public static boolean valueOfBoolExpr(Queue<String> tokens) {
        SimpleWriter out = new SimpleWriter1L();
        out.println(tokens);
        boolean value = false;
        String token = "";
        if (tokens.length() > 0) {
            token = tokens.dequeue();
            switch (token) {
                case "T":
                    value = true;
                    break;
                case "F":
                    value = false;
                    break;
                case "NOT":
                    Queue<String> not = tokens.newInstance();
                    tokens.dequeue();
                    int notParCount = 1;
                    while (notParCount != 0) {
                        String endPar = tokens.front();
                        if (endPar.equals("(")) {
                            notParCount++;
                        } else if (endPar.equals(")")) {
                            notParCount--;
                        }
                        if (notParCount != 0) {
                            not.enqueue(tokens.dequeue());
                        }
                    }
                    value = !valueOfBoolExpr(not);
                    break;
                case "(":
                    Queue<String> expr1 = tokens.newInstance();
                    Queue<String> expr2 = tokens.newInstance();
                    boolean expr1Val = false;
                    boolean expr2Val = false;
                    boolean andOp = true;

                    tokens.dequeue();

                    /*
                     * Find and evaluate value of first expression.
                     */
                    boolean exprFound = false;
                    int exprParCount = 0;
                    while (!exprFound) {
                        token = tokens.dequeue();
                        if (token.equals("(")) {
                            exprParCount++;
                        } else if (token.equals(")")) {
                            exprParCount--;
                        } else if (token.equals("AND")) {
                            if (exprParCount == 0) {
                                exprFound = true;
                                expr1.enqueue("### END OF INPUT ###");
                            }
                        } else if (token.equals("OR")) {
                            if (exprParCount == 0) {
                                exprFound = true;
                                andOp = false;
                                expr1.enqueue("### END OF INPUT ###");
                            }
                        }
                        if (!exprFound) {
                            expr1.enqueue(tokens.dequeue());
                        }
                    }
                    expr1Val = valueOfBoolExpr(expr1);

                    /*
                     * Find and evaluate value of seound expression.
                     */
                    exprParCount = 1;
                    while (exprParCount != 0) {
                        token = tokens.dequeue();
                        if (token.equals("(")) {
                            exprParCount++;
                        } else if (token.equals(")")) {
                            exprParCount--;
                        }
                        if (exprParCount != 0) {
                            expr2.enqueue(tokens.dequeue());
                        }
                    }
                    expr2Val = valueOfBoolExpr(expr2);

                    /*
                     * Evaluate final expr value within parentheses.
                     */
                    if (andOp) {
                        value = expr1Val && expr2Val;
                    } else {
                        value = expr1Val || expr2Val;
                    }
                    break;
                default:
                    tokens.enqueue(token);
                    break;
            }
        }
        return value;
    }

    public static Queue<String> create(String... tokens) {
        Queue<String> q = new Queue1L<>();
        for (String s : tokens) {
            q.enqueue(s);
        }
        return q;
    }

    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        Queue<String> tokens = create("NOT", "(", "F", "OR", "T", ")", "### END OF INPUT ###");
        out.println(tokens);
        out.println(valueOfBoolExpr(tokens));
        out.println(tokens);
        out.close();
    }
}
