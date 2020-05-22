package com.example.api_consumer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //private ImageView image;
    private TextView descriptionId;
    private TextView descriptionName;
    private TextView pokemon;
    private TextView pokemonId;
    private ProgressDialog load;
    String url = "https://pokeapi.co/api/v2/pokemon/";

    Random r = new Random();
    int low = 1;
    int high = 150;
    int result = r.nextInt(high-low) + low;
    String finalUrl = url.concat(String.valueOf(result));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("pokemon");
        GetPokemon download = new GetPokemon();

        pokemon = (TextView)findViewById(R.id.pokemonName);
        pokemonId = (TextView)findViewById(R.id.pokemonId);
        descriptionId = (TextView)findViewById(R.id.descriptionIdText);
        descriptionName = (TextView)findViewById(R.id.descriptionNameText);
        //image = (ImageView)findViewById(R.id.pokemonImage);

        download.execute();
    }

    private class GetPokemon extends AsyncTask<Void, Void, Pokemon> {

        @Override
        protected void onPreExecute(){
            load = ProgressDialog.show(MainActivity.this,
                    "wait", "...");
        }

        @Override
        protected Pokemon doInBackground(Void... params) {
            Controller util = new Controller();
            try {
                return util.getData(finalUrl);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Pokemon newPokemon){
            pokemon.setText(newPokemon.getName());
            pokemonId.setText(newPokemon.getId());
            descriptionId.setText("ID:");
            descriptionName.setText("NAME:");
            //image.setImageBitmap(newPokemon.getImage());
            load.dismiss();
        }
    }


}
