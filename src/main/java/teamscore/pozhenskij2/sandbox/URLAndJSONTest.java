package teamscore.pozhenskij2.sandbox;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class URLAndJSONTest {
    public static void main(String[] args) throws MalformedURLException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4000);
                return getAllInfoHTTP();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        future.thenAccept(System.out::println);

        System.out.println("До Join"); // Сработает сразу

        future.join();

        System.out.println("После join"); //дожидается join
    }

    private static String getAllInfoHTTP() throws MalformedURLException {
        var url = new URL("http://date.jsontest.com/");
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try(var scan = new Scanner(conn.getInputStream());
            var fileWriter = new FileWriter("json.txt")) {
            var sb = new StringBuilder();

            while (scan.hasNextLine()) {
                sb.append(scan.nextLine());
            }

            var jsonArr = new JSONObject(sb.toString());
            jsonArr.put("hehe", 123);
            jsonArr.put("name", "Oleg");

            var jsonTeddy = new JSONObject(new Teddy("Bulka", 3));
            System.out.println(jsonTeddy);
            fileWriter.write(sb.toString());

            conn.disconnect();

            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


