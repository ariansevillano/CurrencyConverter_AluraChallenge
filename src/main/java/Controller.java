import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Controller {
    public static JsonObject obtenerTasas() {
        try {
            ConfigLoader configLoader = new ConfigLoader();
            String urlCompleta = configLoader.getApiUrl();
            URL url = new URL(urlCompleta);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonobj = root.getAsJsonObject();

            if (!jsonobj.get("result").getAsString().equals("success")) {
                System.out.println("Error: " + jsonobj.get("error-type").getAsString());
                return null;
            }

            return jsonobj.getAsJsonObject("conversion_rates");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
