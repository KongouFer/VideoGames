package com.amaurypm.videogames.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.amaurypm.videogames.R
import com.amaurypm.videogames.data.remote.GamesApi
import com.amaurypm.videogames.data.remote.PokemonApi
import com.amaurypm.videogames.data.remote.model.GameDto
import com.amaurypm.videogames.data.remote.model.PokemonDetail2Dto
import com.amaurypm.videogames.databinding.ActivityTestBinding
import com.amaurypm.videogames.util.Constants
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Test : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //Generamos una instancia a retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val pokemonApi = retrofit.create(PokemonApi::class.java)

        //val call: Call<List<GameDto>> = gamesApi.getGames("cm/games/games_list.php")

        //Usando Apiary
        val call: Call<PokemonDetail2Dto> = pokemonApi.getPokemonDetail("6")

        call.enqueue(object: Callback<PokemonDetail2Dto>{
            override fun onResponse(p0: Call<PokemonDetail2Dto>, response: Response<PokemonDetail2Dto>) {

                Glide.with(this@Test)
                    .load(response.body()?.sprites?.other?.officialArtwork?.front_default)
                    .into(binding.imageView)

                Toast.makeText(this@Test, response.body()?.name, Toast.LENGTH_SHORT).show()

            }

            override fun onFailure(p0: Call<PokemonDetail2Dto>, p1: Throwable) {
                TODO("Not yet implemented")
            }

        })


    }
}