import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


//D9673DE8164FF4EC7DA00F07126E9AC3

public class Start {
    static String url = "http://94.198.50.185:7081/api/users";

    public static void main(String[] args) throws IOException {
        String sesion = getSesion();
        post(sesion);
        put(sesion);
        delete(sesion);
    }
    public static  void post(String sesionId) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = restTemplate.headForHeaders(url);
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Cookie" , sesionId);

        User user = new User(3L, "James" , "Brown" , (byte) 25);
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(url , HttpMethod.POST , request , String.class);
        System.out.println(response);
    }

    public static void put(String session) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = restTemplate.headForHeaders(url);

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Cookie" , session);


        User user = new User(2L, "Thomas" , "Shelby" , (byte) 25);
        HttpEntity<User> request = new HttpEntity<>(user, headers);


        ResponseEntity<String> response = restTemplate.exchange (url , HttpMethod.PUT , request, String.class);
        System.out.println(response);

    }
    public static void delete(String session) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = restTemplate.headForHeaders(url);
//
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Cookie" , session);
//

        User user = new User(2L, "Thomas" , "Shelby" , (byte) 25);
        HttpEntity<String> request = new HttpEntity<String>(user.getLastName(), headers);




        ResponseEntity<String> response = restTemplate.exchange (url + "/3", HttpMethod.DELETE , request, String.class);

        System.out.println(response);
    }

    public static String getSesion() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = restTemplate.headForHeaders(url);

        headers.setContentType(MediaType.APPLICATION_JSON);

        String juser = restTemplate.getForObject(url, String.class);
        String cookie = headers.getFirst("set-cookie");
        final ObjectMapper objectMapper = new ObjectMapper();
        User[] users = objectMapper.readValue(juser, User[].class);



        String sesionid = "";

        boolean en = false;
        for (char ch : cookie.toCharArray()){
            if(ch == '='){
                en = true;
                continue;
            }
            else if(ch == ';'){
                break;
            }

            if(en){
                sesionid += ch;
            }
        }
        System.out.println(sesionid);
        return sesionid;
    }
}
