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

/**
 *
 * @author equilibrium
 */
public class AlbumUpc {

    private String link;
    private JsonObject jsonObject;

    public AlbumUpc(String link) {
        this.link = link;
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
        String response = "";
        
        try {
            URL url = new URL(link);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            String basicAuth = "Bearer " + getToken(); //+ token;
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

}