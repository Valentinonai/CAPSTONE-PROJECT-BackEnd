package CapstoneProject.CapstoneProject.chat;

import CapstoneProject.CapstoneProject.exception.SingleBadRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
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
            String apiUrl = "https://api.openai.com/v1/threads/" + thread + "/messages";
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(apiUrl);
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Authorization", "Bearer " + key);
            httpPost.setHeader("OpenAI-Beta", "assistants=v1");
            //Preparo body richiesta
            String jsonBody = "{"
                    + "\"role\": \"user\","
                    + "\"content\": \"" + message.messaggio() + "\""
                    + "}";
            StringEntity entity = new StringEntity(jsonBody);
            httpPost.setEntity(entity);
            HttpResponse response = ((CloseableHttpClient) httpClient).execute(httpPost);
            String responseString = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = new JSONObject(responseString);
            httpClient.close();
            eseguiAssistente(thread);
            // Stampa della risposta
            return rispostaAssistente(thread);
        }catch (Exception e) {
           e.printStackTrace();
           throw new SingleBadRequest("MESSAGGIO NON INVIATO");
       }
    }

    public ThreadPayload apriThread() {
        try {
            // Sostituisci con la tua chiave API
            String openaiApiKey = key;

            // Costruisci l'URL dell'API OpenAI
            String apiUrl = "https://api.openai.com/v1/threads";

            // Crea un client HttpClient
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // Crea una richiesta HTTP di tipo GET
            HttpPost httpPost = new HttpPost(apiUrl);

            // Aggiungi le intestazioni necessarie, inclusa l'autorizzazione
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Authorization", "Bearer "+key);
            httpPost.setHeader("OpenAI-Beta", "assistants=v1");
            httpPost.setHeader("Assistant_id", assistant_id);

            // Esegui la richiesta e ottieni la risposta
            HttpResponse response = ((CloseableHttpClient) httpClient).execute(httpPost);

            // Leggi e stampa la risposta
            String responseBodyString = EntityUtils.toString(response.getEntity());
            JSONObject responseBody = new JSONObject(responseBodyString);
            httpClient.close();
            return new ThreadPayload(responseBody.getString("id"));
            // Chiudi le risorse

        } catch (IOException e) {
            e.printStackTrace();
            throw new SingleBadRequest("STATO NON DISPONIBILE");
            // Gestisci l'eccezione in base alle tue esigenze
        }
    }

    public ChatPayload eseguiAssistente(String thread){
        try {
            String apiurl="https://api.openai.com/v1/threads/"+thread+"/runs";
            CloseableHttpClient httpClient=HttpClients.createDefault();

            HttpPost httpPost=new HttpPost(apiurl);
            httpPost.setHeader("Authorization", "Bearer "+key);
           httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("OpenAI-Beta", "assistants=v1");

            String body="{ \"assistant_id\": \""+assistant_id+"\", \"instructions\": \"\" }";
            StringEntity jsonBody=new StringEntity(body);
            httpPost.setEntity(jsonBody);
            HttpResponse httpResponse=((CloseableHttpClient) httpClient ).execute(httpPost);
            String response=EntityUtils.toString(httpResponse.getEntity());
            JSONObject jsonObject=new JSONObject(response);
            httpClient.close();
            String idRun= (String) jsonObject.get("id");
            boolean x=false;
                while(!x){
                    x=getStato(thread,idRun);
                };

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ChatPayload rispostaAssistente(String thread){
        try {
            String apiurl="https://api.openai.com/v1/threads/"+thread+"/messages";
            CloseableHttpClient httpClient=HttpClients.createDefault();
            HttpGet httpGet=new HttpGet(apiurl);
            httpGet.setHeader("Content-Type", "application/json");
            httpGet.setHeader("Authorization", "Bearer "+key);
            httpGet.setHeader("OpenAI-Beta", "assistants=v1");
            HttpResponse httpResponse=((CloseableHttpClient) httpClient).execute(httpGet);
            String response=EntityUtils.toString(httpResponse.getEntity());
            JSONObject jsonObject=new JSONObject(response);

            String assistantMessage = jsonObject.getJSONArray("data")
                    .getJSONObject(0)
                    .getJSONArray("content")
                    .getJSONObject(0)
                    .getJSONObject("text")
                    .getString("value");

            return new ChatPayload(assistantMessage);
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
        }
    }
}
