package teamscore.pozhenskij2.sandbox;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class URLAndJSONTest {
    public static void main(String[] args) throws IOException {
        var url = new URL("http://date.jsontest.com/");
        var conn = (HttpURLConnection) url.openConnection();


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
        }

       conn.disconnect();
    }
}


