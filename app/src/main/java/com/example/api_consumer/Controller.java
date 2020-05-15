package com.example.api_consumer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Controller {

    public CharacterQuote getData(String end) throws Exception {
        String json = ConexaoApi.getJsonFromApi(end);

        if(json.isEmpty()){
            throw new Exception("something went wrong");
        }
        String jsonSemParenteses =  json.substring(1, json.length() -1);
        return parseJson(jsonSemParenteses);
    }

    private CharacterQuote parseJson(String json){
        try {
            CharacterQuote quote = new CharacterQuote();

            JSONObject obj = new JSONObject(json);
            quote.setCharacter(obj.getString("character"));
            quote.setCharacterDirection(obj.getString("characterDirection"));
            quote.setImage(converteImagem((obj.getString("image"))));
            quote.setQuote(obj.getString("quote"));

            return quote;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    private Bitmap converteImagem(String url) {
        try {
            URL urlImagem = new URL(url);
            InputStream inputStream = urlImagem.openStream();
            Bitmap image = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
