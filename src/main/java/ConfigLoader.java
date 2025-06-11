import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private Properties properties;

    public ConfigLoader() {
        properties = new Properties();
        try (InputStream input = getClass().getResourceAsStream("application.properties")) {
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getApiUrl() {
        String apiKey = properties.getProperty("API_KEY");
        String base = properties.getProperty("BASE_URL");
        return base + apiKey + "/latest/USD";
    }
}
