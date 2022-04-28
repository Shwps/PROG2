import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadURL {


    public static void main(String[] args) throws IOException {
        URL url = new URL("https://dsv.su.se");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        reader.lines().forEach(System.out::println);
    }
}
