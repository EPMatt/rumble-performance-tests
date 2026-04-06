package scaling.pagination;

import org.junit.Test;
import scaling.ScalingTest;


public class TestPagination extends ScalingTest {

    @Test
    public void all() {
        runTest(PaginationTestCases.allTestCases, "rumble", JSONIQ_LANGUAGE);
        runTest(PaginationTestCases.allTestCases, "rumble", XQUERY_LANGUAGE);
    }

    @Override
    public String getTestName() {
        return "Pagination";
    }

    @Override
    public boolean getInitTesting() {
        return false;
    }
}
