

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Customized JUnit test fixture for {@code NaturalNumberAsString}.
 */
public class NaturalNumberAsStringTest extends NaturalNumberTest {

    @Override
    protected final NaturalNumber constructorTest() {
        return new NaturalNumberAsString();
    }

    @Override
    protected final NaturalNumber constructorTest(int i) {
        return new NaturalNumberAsString(i);
    }

    @Override
    protected final NaturalNumber constructorTest(String s) {
        return new NaturalNumberAsString(s);
    }

    @Override
    protected final NaturalNumber constructorTest(NaturalNumber n) {
        return new NaturalNumberAsString(n);
    }

    @Override
    protected final NaturalNumber constructorRef() {
        return new NaturalNumber2();
    }

    @Override
    protected final NaturalNumber constructorRef(int i) {
        return new NaturalNumber2(i);
    }

    @Override
    protected final NaturalNumber constructorRef(String s) {
        return new NaturalNumber2(s);
    }

    @Override
    protected final NaturalNumber constructorRef(NaturalNumber n) {
        return new NaturalNumber2(n);
    }

}
