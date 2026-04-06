package scaling.steps;

import org.junit.Test;
import scaling.ScalingTest;

public class TestStepImprovements extends ScalingTest {

    @Test
    public void testMaster2() {
        runTest(StepsTestCases.stepImprovementCases, "opt_instanceof", JSONIQ_LANGUAGE);
        runTest(StepsTestCases.stepImprovementCases, "opt_instanceof_parent", JSONIQ_LANGUAGE);
        runTest(StepsTestCases.stepImprovementCases, "opt_instanceof_parent_steps", JSONIQ_LANGUAGE);
        runTest(StepsTestCases.stepImprovementCases, "rumble_experimental", JSONIQ_LANGUAGE);
    }

    @Test
    public void testMaster1() {
        runTest(StepsTestCases.stepImprovementCases, "first_implementation", JSONIQ_LANGUAGE);
    }

    @Override
    public String getTestName() {
        return "StepImprovements";
    }

    @Override
    public boolean getInitTesting() {
        return false;
    }
}
