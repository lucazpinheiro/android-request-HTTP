package com.example.api_consumer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    //private ImageView image;
    private TextView pokemon;
    private TextView pokemonId;
    private ProgressDialog load;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("pokemons");
        GetPokemon download = new GetPokemon();

        pokemon = (TextView)findViewById(R.id.pokemonName);
        pokemonId = (TextView)findViewById(R.id.pokemonId);
        //image = (ImageView)findViewById(R.id.pokemonImage);

        download.execute();
    }

    private class GetPokemon extends AsyncTask<Void, Void, Pokemon> implements com.example.api_consumer.GetPokemon {

        @Override
        protected void onPreExecute(){
            load = ProgressDialog.show(MainActivity.this,
                    "wait", "...");
        }

        @Override
        protected Pokemon doInBackground(Void... params) {
            Controller util = new Controller();
            try {
                return util.getData("https://pokeapi.co/api/v2/pokemon/78");
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
            //image.setImageBitmap(newPokemon.getImage());
            load.dismiss();
        }
    }


}
