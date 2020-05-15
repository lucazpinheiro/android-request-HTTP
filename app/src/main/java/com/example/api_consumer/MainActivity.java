package com.example.api_consumer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ImageView image;
    private TextView pokemon;
    private ProgressDialog load;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("pokemons");
        //DownloadCharacter download = new DownloadCharacter();

        pokemon = (TextView)findViewById(R.id.pokemonName);
        image = (ImageView)findViewById(R.id.pokemonImage);

        //Chama Async Task
        //download.execute();
    }

    private class GetPokemon extends AsyncTask<Void, Void, CharacterQuote> {

        @Override
        protected void onPreExecute(){
            //inicia o dialog
            load = ProgressDialog.show(MainActivity.this,
                    "Aguarde ...", "Obtendo Informações...");
        }

        @Override
        protected Pokemon doInBackground(Void... params) {
            Conversor util = new Conversor();
            try {
                return util.getInformacao("https://pokeapi.co/api/v2/pokemon/78");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Pokemon newPokemon){
            pokemon.setText(newPokemon.getCharacter());
            image.setImageBitmap(newPokemon.getImage());
            load.dismiss();
        }
    }


}
