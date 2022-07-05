package fr.atlasdev.chakchouscan;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.view.View;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonParse {
    private JSONObject obj;

    public JsonParse(String json) throws JSONException {
        JSONObject jObj= new JSONObject(json);
        this.obj = jObj.getJSONObject("product");
    }

    public Ingredient getInfo() {
        Ingredient ingredient = new Ingredient();
        try {
            ingredient.setNom(obj.getString("product_name"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            ingredient.setQuantite(obj.getDouble("product_quantity"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            ingredient.setNutriscore(obj.getString("nutriscore_grade"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            ingredient.setNovascore(obj.getInt("nutriscore_score"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            ingredient.setImg_url(obj.getString("image_front_url"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ingredient;
    }
}
