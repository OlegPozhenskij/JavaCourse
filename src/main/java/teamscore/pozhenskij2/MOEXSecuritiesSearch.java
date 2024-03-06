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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
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

        System.out.println("До future");

        CompletableFuture<JSONArray> future = CompletableFuture.supplyAsync(() -> searchMOEXSecurities(query));
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            try {
                var secur = future.get();

                if (secur != null && ! secur.isEmpty()) {
                    saveSecuritiesToCSV(query, secur);
                    System.out.println("Securities saved to CSV file.");
                } else {
                    System.out.println("No securities found for the query: " + query);
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });


        System.out.println("После future");
        try {
            Thread.sleep(6000); // тяжёлые задачи после асинхронной
            System.out.println("После задач основного кода");

            cf.join(); // Ждем завершения выполнения CompletableFuture
            System.out.println("После выполнения future");
        } catch (Exception e) {
            e.printStackTrace();
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

            Thread.sleep(3000);

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

            Thread.sleep(3000);

            writer.write("secid,shortname,regnumber,name,emitent_title,emitent_inn,emitent_okpo\n");
            String res = "";

            for (int i = 0; i < securities.length(); i++) {
                JSONArray security = securities.getJSONArray(i);

                String result = security.join(", ");
                writer.write(result + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
