package teamscore.pozhenskij2;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
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
        CompletableFuture<JSONArray> securitiesFuture = searchMOEXSecurities(query);

        securitiesFuture.thenAccept(securities -> {
            if (securities != null && securities.length() > 0) {
                saveSecuritiesToCSV(query, securities);
                System.out.println("Securities saved to CSV file.");
            } else {
                System.out.println("No securities found for the query: " + query);
            }
        }).join();
    }

    public static CompletableFuture<JSONArray> searchMOEXSecurities(String query) {
        CompletableFuture<JSONArray> future = new CompletableFuture<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest;
        try {
            httpRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://iss.moex.com/iss/securities.json?q=" + query))
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            future.completeExceptionally(e);
            return future;
        }

        httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(MOEXSecuritiesSearch::parseJSONResponse)
                .thenAccept(future::complete)
                .exceptionally(throwable -> {
                    future.completeExceptionally(throwable);
                    return null;
                });

        return future;
    }

    private static JSONArray parseJSONResponse(String response) {
        JSONObject jsonResponse = new JSONObject(response);
        JSONObject securitiesData = jsonResponse.getJSONObject("securities");
        if (securitiesData.has("data")) {
            return securitiesData.getJSONArray("data");
        }
        return new JSONArray();
    }

    public static void saveSecuritiesToCSV(String query, JSONArray securities) {
        String fileName = query + ".csv";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("secid,shortname,regnumber,name,emitent_title,emitent_inn,emitent_okpo\n");
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
