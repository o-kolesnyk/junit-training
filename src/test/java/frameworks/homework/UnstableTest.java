package frameworks.homework;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

public class UnstableTest {

    @Rule
    public TestRule runAgainRule = new RunAgainRule();

    private static int attempt = 1;

    @Test
    @Unstable(3)
    public void failingTest() {
        if (attempt < 3) {
            Assert.fail("Failed on " + (attempt++) + " attempt");
        }
    }
}
