package com.example.api_consumer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connection {

    public static String getJsonFromApi(String url){
        //metodo que  conecta na api e pega o json
        String returnData = "";
        try {
            URL apiEnd = new URL(url);
            HttpURLConnection conn;


            conn = (HttpURLConnection)
            apiEnd.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(150000);
            conn.setConnectTimeout(150000);
            conn.connect();

            int response = conn.getResponseCode();
            InputStream is;
            if(response == HttpURLConnection.HTTP_OK){
                is = conn.getInputStream();
            }else{
                is = conn.getErrorStream();
            }

            returnData = converterInputStreamToString(is);
            is.close();
            conn.disconnect();
        } catch (Exception e){
            e.printStackTrace();
        }
        return returnData;
    }

    private static String converterInputStreamToString(InputStream is){
        StringBuffer buffer = new StringBuffer();
        try{
            BufferedReader br;
            String linha;

            br = new BufferedReader(new InputStreamReader(is));
            while((linha = br.readLine())!=null){
                buffer.append(linha);
            }

            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return buffer.toString();
    }
}
