/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spotifymaven;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author equilibrium
 */
public class SpotifyApi {

    public static void main(String[] args) throws IOException {
        /*
        System.out.println("hi!");
        
        System.out.println(Unirest.post("https://accounts.spotify.com/api/token/{method}")
                .routeParam("method", "post")
                .header("Authorization: Basic ", "NTM0NzYyN2JkYzQ0NGEwYzg3ZWI4NGFkZTkwMTc0YzI6NDQ5NjNjMDViY2FjNDlmNGIwZDU1YWE4ZWY5YWY0NDk=")
                .field("grant_type", "client_credentials")
                .asJson()
                .getBody()); 
         */
 /*
        URL url = new URL("https://accounts.spotify.com/api/token");
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        String basicAuth = "Basic NTM0NzYyN2JkYzQ0NGEwYzg3ZWI4NGFkZTkwMTc0YzI6NDQ5NjNjMDViY2FjNDlmNGIwZDU1YWE4ZWY5YWY0NDk=";
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod("POST");
        httpCon.setRequestProperty("Authorization", basicAuth);
        
        OutputStreamWriter out = new OutputStreamWriter(
                httpCon.getOutputStream());
        out.write("grant_type=client_credentials");
        out.flush();
        System.out.println(httpCon.getResponseCode());
        System.out.println(httpCon.getResponseMessage());
        out.close();
        
        
        String json_response = "";
        InputStreamReader in = new InputStreamReader(httpCon.getInputStream());
        BufferedReader br = new BufferedReader(in);
        String text = "";
        while ((text = br.readLine()) != null) {
            json_response += text;
        }
        System.out.println(json_response);

        
        System.out.println("###");
        System.out.println("###");
        System.out.println("###");
        
        
        JsonObject jsonObject = new JsonParser().parse(json_response).getAsJsonObject();
        System.out.println(jsonObject.get("access_token").getAsString()); //John
         */

        System.out.println("###");
        System.out.println("###");
        System.out.println("###");
        System.out.println("###");
        System.out.println("###");
        System.out.println("###");

        //curl -H "Authorization: Bearer BQA6myy_AB-tyANSfiG2UtWbhHxzplok_i0qZTGGp4N2Lm6oBB6vNQfFa0JhdS7uNgqEC0wzRqAdhDFdWPqyHQ" "https://api.spotify.com/v1/search?q=upc:8033772864862&type=album"
        URL url = new URL("https://api.spotify.com/v1/search?q=upc:8033772864862&type=album");
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        String basicAuth = "Bearer BQDmx0sW1jNfqZIqDP6PzqavPrjuX563_nvbJkoFmu-jEG5r6fV9oIJhcLOSkbSnY99STgrhSzAZE2ALYqNqiQ";
        httpCon.setRequestMethod("GET");
        httpCon.setRequestProperty("Authorization", basicAuth);

        int responseCode = httpCon.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        /*
        OutputStreamWriter out = new OutputStreamWriter(
                httpCon.getOutputStream());
        out.flush();
        System.out.println(httpCon.getResponseCode());
        System.out.println(httpCon.getResponseMessage());
        out.close();
        
        
        String json_response = "";
        InputStreamReader in = new InputStreamReader(httpCon.getInputStream());
        BufferedReader br = new BufferedReader(in);
        String text = "";
        while ((text = br.readLine()) != null) {
            json_response += text;
        }
        System.out.println(json_response);
         */

        BufferedReader in = new BufferedReader(
                new InputStreamReader(httpCon.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

}