package scaling.steps;

import org.junit.Test;
import scaling.ScalingTest;


public class TestSteps extends ScalingTest {

    @Test
    public void all() {
        runTest(StepsTestCases.allRumbleCases, "rumble", JSONIQ_LANGUAGE);
        runTest(StepsTestCases.allRumbleCases, "rumble", XQUERY_LANGUAGE);
    }

    @Override
    public String getTestName() {
        return "Steps";
    }

    @Override
    public boolean getInitTesting() {
        return true;
    }
}
