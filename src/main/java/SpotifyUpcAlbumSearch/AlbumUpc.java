/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpotifyUpcAlbumSearch;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;

/**
 *
 * @author equilibrium
 */
public class AlbumUpc {

    private String link;
    private JsonObject jsonObject;
    private String response;
    private JSONArray jArray;
    private String albumsJson;

    public AlbumUpc(String link) {
        this.link = link;
        this.response="";
        this.jsonObject = null;
    }
    
    
    
    private static String getToken(){
        String json_response = "";
        
        try {
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
            //System.out.println(httpCon.getResponseCode());
            //System.out.println(httpCon.getResponseMessage());
            out.close();
            
            InputStreamReader in = new InputStreamReader(httpCon.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String text = "";
            while ((text = br.readLine()) != null) {
                json_response += text;
            }
            //System.out.println(json_response);
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(AlbumUpc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(AlbumUpc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AlbumUpc.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JsonObject token = new JsonParser().parse(json_response).getAsJsonObject();
        return token.get("access_token").getAsString();
              
                
    }

    public String getLink() {
        return this.link;
    }
    
    
    
    public JsonObject getJson(String link){
        
        
        try {
            URL url = new URL(link);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            String basicAuth = "Bearer " + "BQC9Wag-6GbjoTdBMW7MAyBf85dcUNhkyaq7Vzb--aWmSDHoj5A6XQohcbr3Jm0tRUt4F4VQrZj9R1kGzSIvQg"; //+ token;
            httpCon.setRequestMethod("GET");
            httpCon.setRequestProperty("Authorization", basicAuth);
            
            int responseCode = httpCon.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(httpCon.getInputStream()));
            String inputLine;
            
            
            while ((inputLine = in.readLine()) != null) {
                response += inputLine;
            }
            in.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(AlbumUpc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(AlbumUpc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AlbumUpc.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.jsonObject = new JsonParser().parse(response).getAsJsonObject();
        //System.out.println("PRINTING JSON: " + this.jsonObject.toString());
        return this.jsonObject;      
        
    }
    
    
    public String getAlbumId(){
        
        
        JsonObject jsonId = new JsonParser().parse(response).getAsJsonObject();
        this.albumsJson = jsonId.get("albums").toString();    
        System.out.println(this.albumsJson);
        
        jsonId = new JsonParser().parse(this.albumsJson).getAsJsonObject();
        
        String items = jsonId.get("items").toString(); 
        
        System.out.println("ITEMS: " + items);
        
        

        JSONObject jsonObj = new JSONObject(this.albumsJson);
        this.jArray = jsonObj.getJSONArray("items");
        
        System.out.println("ARRAY: " + jArray.toString());

        //array.length() >= 3
        JSONObject job = jArray.getJSONObject(0);
        System.out.println("JOB: " + job.toString());
        String albumId = job.getString("id");
        System.out.println("ID: " + albumId);
        
        
        return albumId;
        
        
    }
    
    
    public String getAlbumName(){
        
       
        //array.length() >= 3
        JSONObject job = jArray.getJSONObject(0);
        System.out.println("JOB: " + job.toString());
        String albumName = job.getString("name");
        System.out.println("NAME: " + albumName);
        
        
        return albumName;
        
        
        
        
    }
    
    
    
    public String showCoverLink(){
        System.out.println("#1#1#1#1#");
        System.out.println("#1#1#1#1#");
        System.out.println("#1#1#1#1#\n");
        
        JSONObject jsonObj = new JSONObject(this.albumsJson);
        this.jArray = jsonObj.getJSONArray("items");
        
        //System.out.println("ARRAY: " + jArray.toString());

        //array.length() >= 3
        JSONObject job = this.jArray.getJSONObject(0);
        System.out.println("JOB: " + job.toString());
        
        System.out.println("IMAGES: " + job.getJSONArray("images").toString());
  
        JSONArray jArrayImage = job.getJSONArray("images");
        JSONObject jobImage = jArrayImage.getJSONObject(0);
        System.out.println("JOB: " + jobImage.toString());
        String coverURL = jobImage.getString("url");
        System.out.println("URL: " + coverURL);
        
        return coverURL;
    }
    
    public String getLabelApiId(){
        
        
        JsonObject jsonId = new JsonParser().parse(response).getAsJsonObject();
        this.albumsJson = jsonId.get("label").toString();
        
        
        System.out.println("LABEL: " + this.albumsJson);
        
        return this.albumsJson;
        
    }
    
    
    public String getReleaseDateApiId(){
        
        JsonObject jsonId = new JsonParser().parse(response).getAsJsonObject();
        this.albumsJson = jsonId.get("release_date").toString();
        
        System.out.println("RELEASE DATE: " + this.albumsJson);
        
        return this.albumsJson;
        
    }
    
    
    
    

}