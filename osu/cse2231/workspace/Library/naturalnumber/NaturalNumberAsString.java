

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumberSecondary;

/**
 * OSU Components {@code NaturalNumber} represented as a {@code String} with
 * implementations of primary methods.
 *
 * @convention <pre>
 * [all characters of $this.rep are '0' through '9']  and
 * [$this.rep does not start with '0']
 * </pre>
 * @correspondence <pre>
 * this = [if $this.rep = "" then 0
 *         else the decimal number whose ordinary depiction is $this.rep]
 * </pre>
 *
 * @author Faye Leigh
 */
public class NaturalNumberAsString extends NaturalNumberSecondary {

    /*
     * Private members ---------------------------------------------------------
     */

    /**
     * Representation of {@code this}.
     */
    private String rep;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.rep = "";
    }

    /*
     * Constructors ------------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public NaturalNumberAsString() {
        this.createNewRep();
    }

    /**
     * Constructor from {@code int}.
     *
     * @param i
     *            {@code int} to initialize from
     */
    public NaturalNumberAsString(int i) {
        assert i >= 0 : "Violation of: i >= 0";

        if (i == 0) {
            this.createNewRep();
        } else {
            this.rep = String.valueOf(i);
        }
    }

    /**
     * Constructor from {@code String}.
     *
     * @param s
     *            {@code String} to initialize from
     */
    public NaturalNumberAsString(String s) {
        assert s != null : "Violation of: s is not null";
        assert s.matches("0|[1-9]\\d*") : ""
                + "Violation of: there exists n: NATURAL (s = TO_STRING(n))";

        if (s.equals("0")) {
            this.createNewRep();
        } else {
            this.rep = s;
        }
    }

    /**
     * Constructor from {@code NaturalNumber}.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     */
    public NaturalNumberAsString(NaturalNumber n) {
        assert n != null : "Violation of: n is not null";

        if (n.isZero()) {
            this.createNewRep();
        } else {
            this.rep = String.valueOf(n);
        }
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @Override
    public final NaturalNumber newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(NaturalNumber source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof NaturalNumberAsString : ""
                + "Violation of: source is of dynamic type NaturalNumberExample";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case.
         */
        NaturalNumberAsString localSource = (NaturalNumberAsString) source;
        this.rep = localSource.rep;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void multiplyBy10(int k) {
        assert 0 <= k : "Violation of: 0 <= k";
        assert k < RADIX : "Violation of: k < 10";

        if (this.rep.length() == 0 && k == 0) {
            this.createNewRep();
        } else {
            this.rep += Integer.toString(k);
        }
    }

    @Override
    public final int divideBy10() {
        int i = 0;
        if (this.rep.length() > 0) {
            char c = this.rep.charAt(this.rep.length() - 1);
            i = Character.getNumericValue(c);
            String newRep = this.rep.substring(0, this.rep.length() - 1);
            this.rep = newRep;
        }
        return i;
    }

    @Override
    public final boolean isZero() {
        return this.rep.length() == 0;
    }

}
