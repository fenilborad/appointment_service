package com.appointment.services;

import com.appointment.helper.JsonHelper;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class HttpClientServiceImpl implements HttpClientService {

    public JsonNode post(String urlPath, String payload){
        try{
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(urlPath);

            StringEntity entity = new StringEntity(payload);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            CloseableHttpResponse response = client.execute(httpPost);
            if(response.getStatusLine().getStatusCode() != HttpStatus.OK.value()){
                client.close();
                return null;
            }

            HttpEntity responseEntity = response.getEntity();
            InputStream content = responseEntity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            String responseString = "";
            String line;
            while ((line = reader.readLine()) != null) {
                responseString += line;
            }

            EntityUtils.consume(responseEntity);
            reader.close();
            client.close();

            JsonNode responseJson = JsonHelper.toJsonNode(responseString);
            return responseJson;

        }catch(IOException e) {
            return null;
        }
    }

    @Override
    public JsonNode get(String urlPath) {
        try{
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(urlPath);

            httpGet.setHeader("Accept", "application/json");
            httpGet.setHeader("Content-type", "application/json");

            CloseableHttpResponse response = client.execute(httpGet);
            if(response.getStatusLine().getStatusCode() != HttpStatus.OK.value()){
                client.close();
                return null;
            }

            HttpEntity responseEntity = response.getEntity();
            InputStream content = responseEntity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            String responseString = "";
            String line;
            while ((line = reader.readLine()) != null) {
                responseString += line;
            }

            EntityUtils.consume(responseEntity);
            reader.close();
            client.close();

            JsonNode responseJson = JsonHelper.toJsonNode(responseString);
            return responseJson;

        }catch(IOException e) {
            return null;
        }
    }
}
