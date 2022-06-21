package fr.atlasdev.chakchouscan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParse {
    private JSONObject obj;

    public JsonParse(String json) throws JSONException {
        JSONObject jObj= new JSONObject(json);
        this.obj = jObj.getJSONObject("product");
    }

    public String getInfo() {
        String keywords = "";
        try {
            keywords = obj.getJSONArray("_keywords").toString();
            keywords += "\n" + obj.get("ingredients_text_fr");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return keywords;
    }
}
