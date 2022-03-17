import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


//276544EC44734433FF058CB0DA1583A3

public class Start {
    public static void main(String[] args) throws IOException {

        post();
    }
    public static  void post() throws IOException {

    }

    public static void getSesion(){
        try {
            URL url = new URL("http://94.198.50.185:7081/api/users");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            String contentType = connection.getHeaderField("set-cookie");

            System.out.println(contentType);
        }
        catch (Exception ignored){

        }
    }
}
