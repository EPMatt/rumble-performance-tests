package helper;

import org.json.simple.JSONObject;
import scaling.Config;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExecutionTimer {


    public static void main(String[] args) {
        String testName = args[0];
        String runtimeConfig = args[1];
        String queryLanguage = args[2];
        String query = args[3];
        int rep = Integer.parseInt(args[4]);
        int queryIndex = Integer.parseInt(args[5]);
        long estimatedInitTime = Long.parseLong(args[6]);
        String outputName = args[7];
        long result;

        try {
            if (RuntimeConfig.lookup(runtimeConfig).useRumble) {
                result = Helper.timeQueryRumble(query, null, runtimeConfig, queryLanguage);
            } else {
                result = Helper.timeQuerySaxon(query, null);
            }
        } catch (Error e) {
            result = -1;
        }

        JSONObject queryResults = new JSONObject();
        queryResults.put("testName", testName);
        queryResults.put("configName", runtimeConfig);
        queryResults.put("queryLanguage", queryLanguage);
        queryResults.put("query", query);
        queryResults.put("runtime", result);
        queryResults.put("run", rep);
        queryResults.put("queryIndex", queryIndex);
        queryResults.put("estimatedInit", estimatedInitTime);

        try {
            Files.createDirectories(Paths.get(Config.outpath));
            FileWriter file = new FileWriter(Config.outpath + "/" + outputName + ".log", true);
            file.write(queryResults.toJSONString() + "\n");
            file.close();
            System.out.println("Wrote result in log");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
