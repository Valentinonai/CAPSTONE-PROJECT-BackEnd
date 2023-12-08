package CapstoneProject.CapstoneProject.chat;

import CapstoneProject.CapstoneProject.exception.SingleBadRequest;
import org.apache.logging.log4j.message.Message;
import org.cloudinary.json.JSONArray;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Service
public class ChatService {
    @Value("${openai.api.key}")
    private String key;

    public ChatPayload domanda(ChatPayload message) throws IOException {

        String apiUrl = "https://api.openai.com/v1/completions";
        HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer "+key);

        JSONObject request = new JSONObject();
        request.put("model", "text-davinci-003");
        request.put("max_tokens", 500);
        request.put("prompt", message.messaggio());
        request.put("temperature", 1.0);

        connection.setDoOutput(true);
        connection.getOutputStream().write(request.toString().getBytes());

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = reader.lines()
                .reduce((a, b) -> a + b)
                .get();

        return new ChatPayload(new JSONObject(response).getJSONArray("choices").getJSONObject(0).getString("text"));

    }
}
