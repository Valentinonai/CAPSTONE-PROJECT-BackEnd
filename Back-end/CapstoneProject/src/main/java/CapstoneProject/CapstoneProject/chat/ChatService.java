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
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ChatService {
    @Value("${openai.api.key}")
    private String key;
    public ChatPayload domanda(ChatPayload message) throws IOException {
        String url = "https://api.openai.com/v1/completions";
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer "+key);

        JSONObject data = new JSONObject();
        data.put("model", "text-davinci-003");
        data.put("prompt", message.messaggio());
        data.put("max_tokens", 4000);
        data.put("temperature", 1.0);

        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());

        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
                .reduce((a, b) -> a + b).get();

        JSONObject jsonObject = new JSONObject(output);


        JSONArray choicesArray = jsonObject.getJSONArray("choices");


        JSONObject firstChoice = choicesArray.getJSONObject(0);


        String str = firstChoice.getString("text");
        
        int secondaRigaIndex = str.indexOf("\n", str.indexOf("\n") + 1);

        if (secondaRigaIndex != -1) {
        return new ChatPayload(str.substring(secondaRigaIndex + 1));

    }else throw new SingleBadRequest("Errore");


    }
}
