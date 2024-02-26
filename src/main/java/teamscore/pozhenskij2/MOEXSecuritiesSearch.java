package teamscore.pozhenskij2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

public class MOEXSecuritiesSearch {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java teamscore.pozhenskij2.MOEXSecuritiesSearch <search_query>");
            return;
        }

        String query = args[0];
        JSONArray securities = searchMOEXSecurities(query);

        if (securities != null && securities.length() > 0) {
            saveSecuritiesToCSV(query, securities);
            System.out.println("Securities saved to CSV file.");
        } else {
            System.out.println("No securities found for the query: " + query);
        }
    }

    public static JSONArray searchMOEXSecurities(String query) {
        JSONArray securities = new JSONArray();
        HttpURLConnection connection = null;
        Scanner scanner = null;

        try {
            URL url = new URL("https://iss.moex.com/iss/securities.json?q=" + query);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                scanner = new Scanner(connection.getInputStream());
                StringBuilder response = new StringBuilder();
                while (scanner.hasNextLine()) {
                    response.append(scanner.nextLine());
                }

                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONObject securitiesData = jsonResponse.getJSONObject("securities");


                if (securitiesData.has("data")) {
                    securities = securitiesData.getJSONArray("data");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }

        return securities;
    }

    public static void saveSecuritiesToCSV(String query, JSONArray securities) {
        String fileName = query + ".csv";
        try (FileWriter writer = new FileWriter(fileName)) {

            writer.write("secid,shortname,regnumber,name,emitent_title,emitent_inn,emitent_okpo\n");
            String res = "";

            for (int i = 0; i < securities.length(); i++) {
                JSONArray security = securities.getJSONArray(i);

                String result = security.toList().stream()
                        .map(Objects::toString)
                        .collect(Collectors.joining(", "));

                writer.write(result + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
