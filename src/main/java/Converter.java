import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Converter {
    public static List<User> parseJson (String value) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(value, List.class);
    }


}
