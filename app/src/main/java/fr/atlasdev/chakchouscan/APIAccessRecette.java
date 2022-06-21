package fr.atlasdev.chakchouscan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class APIAccessRecette {
    public static APIAccessRecette instance;
    private BufferedReader reader;
    private String line;
    private StringBuffer responseContent = new StringBuffer();
    private URL url;

    public static APIAccessRecette getInstance(){
        if(instance==null){
            instance = new APIAccessRecette();
        }
        return instance;
    }

    private APIAccessRecette(){
    }

    private HttpURLConnection connectAPI(String code){
        HttpURLConnection connection=null;
        try {
            this.url = new URL("www.themealdb.com/api/json/v1/1/lookup.php?i=52772");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public String getItem(String barcode){
        HttpURLConnection conn = connectAPI(recette);
        try {
            int responsecode = conn.getResponseCode();
            if(responsecode>299){
                this.reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }
            else {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();

        }
        return responseContent.toString();
    }


}
