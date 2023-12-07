package CapstoneProject.CapstoneProject.chat;

import CapstoneProject.CapstoneProject.exception.SingleBadRequest;
import org.cloudinary.json.JSONArray;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ChatService {
    @Value("${openai.api.key}")
    private String key;

    public ChatPayload domanda(ChatPayload message) throws IOException {

        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = key;
        String model = "gpt-3.5-turbo";

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");


            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + message.messaggio() + "\"}]}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();


            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            StringBuffer response = new StringBuffer();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();


            return new ChatPayload(extractMessageFromJSONResponse(response.toString()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


        public  String extractMessageFromJSONResponse(String response) {
            int start = response.indexOf("content")+ 11;

            int end = response.indexOf("\"", start);

            return response.substring(start, end);

        }
    }


