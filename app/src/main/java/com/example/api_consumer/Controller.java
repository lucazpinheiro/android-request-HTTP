package com.example.api_consumer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Controller {

    public Pokemon getData(String end) throws Exception {
        String json = Connection.getJsonFromApi(end);

        if(json.isEmpty()){
            throw new Exception("something went wrong");
        }
        String data =  json.substring(1, json.length() -1);
        return parseJson(data);
    }

    private Pokemon parseJson(String json){
        try {
            Pokemon poke = new Pokemon();

            JSONObject obj = new JSONObject(json);
            poke.setName(obj.getString("name"));
            poke.setId(obj.getString("id"));


            return poke;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

}
