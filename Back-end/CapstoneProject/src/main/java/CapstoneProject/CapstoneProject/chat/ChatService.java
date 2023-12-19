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
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

@Service
public class ChatService {
    @Value("${openai.api.key}")
    private String key;

    @Value("${openai.assistant.id}")
    private String assistant_id;

    private static final int TIMER_DELAY = 1000;
    private static final int TIMEOUT_SECONDS = 30;
   private static boolean x=false;

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
            ChatPayload c=eseguiAssistente(thread);

           if(c==null)
            return rispostaAssistente(thread);
           else return new ChatPayload("Errore nel caricamento");
        }catch (Exception e) {
           e.printStackTrace();
           throw new SingleBadRequest("MESSAGGIO NON INVIATO");
       }
    }

    public ThreadPayload apriThread() {
        try {

            String openaiApiKey = key;


            String apiUrl = "https://api.openai.com/v1/threads";


            CloseableHttpClient httpClient = HttpClients.createDefault();


            HttpPost httpPost = new HttpPost(apiUrl);


            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Authorization", "Bearer "+key);
            httpPost.setHeader("OpenAI-Beta", "assistants=v1");
            httpPost.setHeader("Assistant_id", assistant_id);


            HttpResponse response = ((CloseableHttpClient) httpClient).execute(httpPost);


            String responseBodyString = EntityUtils.toString(response.getEntity());
            JSONObject responseBody = new JSONObject(responseBodyString);
            httpClient.close();
            return new ThreadPayload(responseBody.getString("id"));


        } catch (IOException e) {
            e.printStackTrace();
            throw new SingleBadRequest("STATO NON DISPONIBILE");

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


            CountDownLatch latch = new CountDownLatch(1);
            Timer timer = new Timer();

            timer.schedule(new TimerTask() {
                int elapsedSeconds = 0;

                @Override
                public void run() {
                    try {
                        x = getStato(thread,idRun);
                        if (x || elapsedSeconds >= TIMEOUT_SECONDS) {
                            latch.countDown();
                            timer.cancel();
                        }else {
                            elapsedSeconds++;

                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                }
            }, 0, TIMER_DELAY);
            latch.await();
            if(x) return null;
            else return new ChatPayload("Errore caricamento");


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

            String openaiApiKey = key;


            String threadId = thread;
            String runId = id_run;


            String apiUrl = "https://api.openai.com/v1/threads/" + threadId + "/runs/" + runId;


            CloseableHttpClient httpClient = HttpClients.createDefault();


            HttpGet httpGet = new HttpGet(apiUrl);


            httpGet.setHeader("Authorization", "Bearer " + openaiApiKey);
            httpGet.setHeader("OpenAI-Beta", "assistants=v1");


            HttpResponse response = ((CloseableHttpClient) httpClient).execute(httpGet);


            String responseBodyString = EntityUtils.toString(response.getEntity());
            JSONObject responseBody = new JSONObject(responseBodyString);
            httpClient.close();
            if(Objects.equals(responseBody.getString("status"), "completed"))   return true;
            else return false;


        } catch (IOException e) {
            e.printStackTrace();
            throw new SingleBadRequest("STATO NON DISPONIBILE");
        }
    }
}
