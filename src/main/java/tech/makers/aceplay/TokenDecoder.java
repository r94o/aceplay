package tech.makers.aceplay;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Base64;

public class TokenDecoder {

    public static String getUsernameFromToken(String token) {
        String[] chunks = token.split("\\.");
        String username;
        Base64.Decoder decoder = Base64.getUrlDecoder();
        try {
            ObjectMapper mapper = new ObjectMapper();
            String payload = new String(decoder.decode(chunks[1]));
            JsonNode tree = mapper.readTree(payload);
            JsonNode node = tree.get("sub");
            username = node.textValue();
        } catch(Exception e) {
            username = "";
        }
        return username;
    }
}
