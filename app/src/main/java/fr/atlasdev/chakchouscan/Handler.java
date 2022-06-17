package fr.atlasdev.chakchouscan;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class Handler extends AsyncTask<String, Void, String> implements HandlerCallback{
    private HttpsURLConnection connection;
    private StringBuffer responseContent = new StringBuffer();

    @Override
    protected void onPostExecute(String result) {
        onResponseReceived(result);
    }

    @Override
    protected String doInBackground(String... strings) {
        return httpServiceCall(strings[0]);
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onProgressUpdate(Void... values) {
    }

    public String httpServiceCall(String requestURL) {
        String line = null;
        try {
            URL url = new URL("https://world.openfoodfacts.org/api/v0/product/"+requestURL+".json");
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while((line = reader.readLine()) != null){
                responseContent.append(line);
            }
            reader.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return responseContent.toString();
    }



    private String convertResultToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while (true){
            try {
                if (!((line=bufferedReader.readLine()) != null)){
                    stringBuilder.append(line+'\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return stringBuilder.toString();
        }
    }

    @Override
    public void onResponseReceived(String result) {

    }
}
