package scaling;


import helper.TestCase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class ScalingTest {
    protected static final String JSONIQ_LANGUAGE = "jsoniq";
    protected static final String XQUERY_LANGUAGE = "xquery31";
    private static final List<String> JAVA_17_ADD_OPENS = List.of(
        "--add-opens=java.base/java.lang.invoke=ALL-UNNAMED",
        "--add-opens=java.base/java.net=ALL-UNNAMED",
        "--add-opens=java.base/java.nio=ALL-UNNAMED",
        "--add-opens=java.base/java.util=ALL-UNNAMED",
        "--add-opens=java.base/sun.nio.ch=ALL-UNNAMED",
        "--add-opens=java.base/sun.util.calendar=ALL-UNNAMED"
    );

    public abstract String getTestName();

    public abstract boolean getInitTesting();

    private static List<String> javaCommandPrefix() {
        List<String> command = new ArrayList<>();
        command.add("java");
        command.addAll(JAVA_17_ADD_OPENS);
        command.add("-cp");
        command.add(System.getProperty("java.class.path"));
        return command;
    }

    public void runTest(List<TestCase> testCases, String configName, String queryLanguage) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd'T'HHmmss");
        String outputName = getTestName()
            + "_"
            + java.time.LocalDateTime.now().format(formatter)
            + "_"
            + configName
            + "_"
            + queryLanguage;
        for (int rep = 1; rep <= Config.reps; rep++) {
            for (TestCase test : testCases) {
                boolean measureInit = getInitTesting();
                long estimatedInitTime = measureInit ? testInitTime(test.getQueries(queryLanguage).get(0), configName, queryLanguage) : -1;
                int queryIndex = 0;
                for (String query : test.getQueries(queryLanguage)) {
                    queryIndex++;
                    System.out.println("Testing: " + query);
                    try {
                        List<String> command = javaCommandPrefix();
                        command.add("helper.ExecutionTimer");
                        command.add(test.getTestName());
                        command.add(configName);
                        command.add(queryLanguage);
                        command.add(query);
                        command.add(String.valueOf(rep));
                        command.add(String.valueOf(queryIndex));
                        command.add(String.valueOf(estimatedInitTime));
                        command.add(outputName);
                        ProcessBuilder processBuilder = new ProcessBuilder(command);
                        processBuilder.inheritIO();
                        Process process = processBuilder.start();
                        try (
                            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))
                        ) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                System.out.println(line);
                            }
                        }
                        process.waitFor(240, TimeUnit.SECONDS);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private long testInitTime(String query, String configName, String queryLanguage) {
        try {
            List<String> command = javaCommandPrefix();
            command.add("helper.InitTimeEstimator");
            command.add(query);
            command.add(configName);
            command.add(queryLanguage);
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    if (line.startsWith("Result:")) {
                        return Long.parseLong(line.split(":")[1].trim());
                    }
                }
            }
            process.waitFor(240, TimeUnit.SECONDS);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
