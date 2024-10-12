
import components.map.Map;
import components.map.Map1L;

/**
 * Customized JUnit test fixture for {@code Map2}.
 */
public class MapAsQueueTest extends MapTest {

    @Override
    protected final Map<String, String> constructorTest() {
        return new MapAsQueue<String, String>();
    }

    @Override
    protected final Map<String, String> constructorRef() {
        return new Map1L<String, String>();
    }

}
