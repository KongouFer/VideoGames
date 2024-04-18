package com.amaurypm.videogames.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.amaurypm.videogames.R
import com.amaurypm.videogames.data.remote.GamesApi
import com.amaurypm.videogames.data.remote.model.GameDto
import com.amaurypm.videogames.databinding.ActivityMainBinding
import com.amaurypm.videogames.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Generamos una instancia a retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val gamesApi = retrofit.create(GamesApi::class.java)

        //val call: Call<List<GameDto>> = gamesApi.getGames("cm/games/games_list.php")

        //Usando Apiary
        val call: Call<List<GameDto>> = gamesApi.getGames("games/games_list")


        call.enqueue(object : Callback<List<GameDto>> {
            override fun onResponse(p0: Call<List<GameDto>>, response: Response<List<GameDto>>) {

                binding.pbLoading.visibility = View.INVISIBLE

                Log.d(Constants.LOGTAG, "Respuesta recibida: ${response.body()}")

                response.body()?.let { games ->
                    val miAdapter = GameAdapter(games){ game ->
                        //Click de cada elemento
                        game.id?.let{ id ->
                            val bundle = bundleOf(
                                "id" to id
                            )

                            val intent = Intent(this@MainActivity, Detalles::class.java)

                            intent.putExtras(bundle)

                            startActivity(intent)
                        }
                    }

                    binding.rvGames.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = miAdapter
                    }
                }

            }

            override fun onFailure(p0: Call<List<GameDto>>, p1: Throwable) {
                //Error de conexión
                //Manejo del error

                binding.pbLoading.visibility = View.INVISIBLE

                Toast.makeText(
                    this@MainActivity,
                    "No hay conexión disponible",
                    Toast.LENGTH_SHORT)
                    .show()
            }

        })

    }
}