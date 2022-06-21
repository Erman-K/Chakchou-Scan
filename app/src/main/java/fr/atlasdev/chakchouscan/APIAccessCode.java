package fr.atlasdev.chakchouscan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class APIAccessCode {
    public static APIAccessCode instance;
    private BufferedReader reader;
    private String line;
    private StringBuffer responseContent = new StringBuffer();
    private URL url;

    public static APIAccessCode getInstance(){
        if(instance==null){
            instance = new APIAccessCode();
        }
        return instance;
    }

    private APIAccessCode(){
    }

    private HttpURLConnection connectAPI(String code){
        HttpURLConnection connection=null;
        try {
            this.url = new URL("https://world.openfoodfacts.org/api/v0/product/"+code+".json");
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
        HttpURLConnection conn = connectAPI(barcode);
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
//            System.out.println(responseContent.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
//            System.out.println("test");
        }
        return responseContent.toString();
    }

//    public static void main(String [] args){
//        String ricola = "3174332018280";
//        getInstance().getItem(ricola);
//    }
}
