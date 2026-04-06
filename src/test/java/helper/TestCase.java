package helper;

import java.util.List;

public class TestCase {
    String testName;
    List<String> jsoniqQueries;
    List<String> xqueryQueries;

    public TestCase(String testName, List<String> queries) {
        this.testName = testName;
        this.jsoniqQueries = queries;
        this.xqueryQueries = queries;
    }

    public TestCase(String testName, List<String> jsoniqQueries, List<String> xqueryQueries) {
        this.testName = testName;
        this.jsoniqQueries = jsoniqQueries;
        this.xqueryQueries = xqueryQueries;
    }

    public String getTestName() {
        return testName;
    }

    public List<String> getQueries() {
        return jsoniqQueries;
    }

    public List<String> getQueries(String queryLanguage) {
        if ("xquery31".equals(queryLanguage)) {
            return xqueryQueries;
        }
        return jsoniqQueries;
    }
}
