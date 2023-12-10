package CapstoneProject.CapstoneProject.chat;

import CapstoneProject.CapstoneProject.exception.SingleBadRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.message.Message;
import org.cloudinary.json.JSONArray;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import java.net.http.HttpClient;
import java.util.Objects;

@Service
public class ChatService {
    @Value("${openai.api.key}")
    private String key;

    @Value("${openai.assistant.id}")
    private String assistant_id;

    public ChatPayload domanda(ChatPayload message,String thread) throws IOException {

        try {
            // URL dell'endpoint API per i messaggi in un thread specifico
            URL url = new URL("https://api.openai.com/v1/threads/" + thread + "/messages");

            // Apertura della connessione HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Impostazione del metodo di richiesta
            connection.setRequestMethod("POST");

            // Impostazione degli header della richiesta
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer "+key);
            connection.setRequestProperty("OpenAI-Beta", "assistants=v1");

            // Abilitazione dell'invio di dati nella richiesta
            connection.setDoOutput(true);

            // Creazione del corpo della richiesta
            String requestBody = "{"
                    + "\"role\": \"user\","
                    + "\"content\": \"" + message.messaggio() + "\""
                    + "}";

            // Invio dei dati nella richiesta
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Lettura della risposta dalla connessione
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                connection.disconnect();
                eseguiAssistente(thread);
                // Stampa della risposta
                return rispostaAssistente(thread);
            }

            // Chiusura della connessione


        } catch (Exception e) {
            e.printStackTrace();
            throw new SingleBadRequest("MESSAGGIO NON INVIATO");
        }

    }

    public ThreadPayload apriThread() {
        try {
            // URL dell'endpoint API
            URL url = new URL("https://api.openai.com/v1/threads");

            // Apertura della connessione HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Impostazione del metodo di richiesta
            connection.setRequestMethod("POST");

            // Impostazione degli header della richiesta
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer "+key);
            connection.setRequestProperty("OpenAI-Beta", "assistants=v1");
            connection.setRequestProperty("Assistant_id", assistant_id);


            // Abilitazione dell'invio di dati nella richiesta
            connection.setDoOutput(true);

            // Invio dei dati nella richiesta (nel tuo caso, vuoto)
            connection.getOutputStream().write("{}".getBytes());

            // Lettura della risposta dalla connessione
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // Chiusura della connessione
            reader.close();
            connection.disconnect();
            // Stampa della risposta
            return new ThreadPayload(new JSONObject(response.toString()).getString("id"));

        } catch (Exception e) {
            e.printStackTrace();
            throw new SingleBadRequest("THREAD NON APERTO");
        }
    }

    public void chiudiThread(String thread) {
    }

    public ChatPayload eseguiAssistente(String thread){
        try {
            // URL dell'endpoint API
            URL url = new URL("https://api.openai.com/v1/threads/"+thread+"/runs");

            // Apertura della connessione HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Impostazione del metodo di richiesta
            connection.setRequestMethod("POST");

            // Impostazione degli header HTTP
            connection.setRequestProperty("Authorization", "Bearer "+key);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("OpenAI-Beta", "assistants=v1");

            // Abilitazione dell'invio di dati nella richiesta
            connection.setDoOutput(true);

            // Creazione del corpo della richiesta
            String requestBody = "{ \"assistant_id\": \""+assistant_id+"\", \"instructions\": \"\" }";

            // Invio del corpo della richiesta
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Lettura della risposta
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                String ID_run="";
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                int i=response.indexOf(",");
                ID_run=response.substring(8,i-1);
                boolean x=false;
                while(!x){
                    x=getStato(thread,ID_run);
                };

            }

            // Chiusura della connessione
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ChatPayload rispostaAssistente(String thread){
        try {
            // URL dell'endpoint API
            URL url = new URL("https://api.openai.com/v1/threads/"+thread+"/messages");

            // Apertura della connessione HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Impostazione del metodo di richiesta
            connection.setRequestMethod("GET");

            // Impostazione degli header HTTP
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer "+key);
            connection.setRequestProperty("OpenAI-Beta", "assistants=v1");

            // Lettura della risposta
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                JSONObject jsonObject = new JSONObject(response.toString());
                JSONArray dataArray = jsonObject.getJSONArray("data");
                // Verifica se esiste un secondo elemento nell'array
                if (dataArray.length() > 1) {
                    for(int i=0;i<dataArray.length();i++){
                        JSONObject message=dataArray.getJSONObject(i);
                        if(Objects.equals(message.getString("role"), "assistant")){
                            String assistantMessage = message.getJSONArray("content")
                                    .getJSONObject(0)
                                    .getJSONObject("text")
                                    .getString("value");
                            return new ChatPayload(assistantMessage);
                        }
                    }


                } else {
                    System.out.println("Non ci sono abbastanza elementi nell'array per estrarre il secondo parametro 'value'.");
                }
            }

            // Chiusura della connessione
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean getStato(String thread,String id_run) throws IOException {

        try {
            // Sostituisci con la tua chiave API
            String openaiApiKey = key;

            // Sostituisci con l'ID del thread e dell'esecuzione specifici
            String threadId = thread;
            String runId = id_run;

            // Costruisci l'URL dell'API OpenAI
            String apiUrl = "https://api.openai.com/v1/threads/" + threadId + "/runs/" + runId;

            // Crea un client HttpClient
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // Crea una richiesta HTTP di tipo GET
            HttpGet httpGet = new HttpGet(apiUrl);

            // Aggiungi le intestazioni necessarie, inclusa l'autorizzazione
            httpGet.setHeader("Authorization", "Bearer " + openaiApiKey);
            httpGet.setHeader("OpenAI-Beta", "assistants=v1");

            // Esegui la richiesta e ottieni la risposta
            HttpResponse response = ((CloseableHttpClient) httpClient).execute(httpGet);

            // Leggi e stampa la risposta
            String responseBodyString = EntityUtils.toString(response.getEntity());
            JSONObject responseBody = new JSONObject(responseBodyString);
            httpClient.close();
            if(Objects.equals(responseBody.getString("status"), "completed"))   return true;
            else return false;
            // Chiudi le risorse

        } catch (IOException e) {
            e.printStackTrace();
            throw new SingleBadRequest("STATO NON DISPONIBILE");
            // Gestisci l'eccezione in base alle tue esigenze
        }

    }
}
