package fr.atlasdev.chakchouscan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParse {
    private JSONObject jObj;

    public JsonParse(String json) throws JSONException {
        jObj= new JSONObject(json);
        
    }
}
