package com.amaurypm.videogames.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.amaurypm.videogames.R
import com.amaurypm.videogames.data.remote.GamesApi
import com.amaurypm.videogames.data.remote.model.GameDetailDto
import com.amaurypm.videogames.data.remote.model.GameDto
import com.amaurypm.videogames.databinding.ActivityDetallesBinding
import com.amaurypm.videogames.util.Constants
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Detalles : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        val id = bundle?.getString("id", "")

        Log.d(Constants.LOGTAG, "Id recibido $id")

        //Generamos una instancia a retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val gamesApi = retrofit.create(GamesApi::class.java)

        //val call: Call<GameDetailDto> = gamesApi.getGameDetail(id!!)

        //Para Apiary
        val call: Call<GameDetailDto> = gamesApi.getGameDetail2(id!!)

        call.enqueue(object: Callback<GameDetailDto>{
            override fun onResponse(p0: Call<GameDetailDto>, response: Response<GameDetailDto>) {

                /*binding.apply {

                    pbLoading.visibility = View.INVISIBLE
                    tvTitle.text = response.body()?.title
                    tvLongDesc.text = response.body()?.longDesc

                    Glide.with(this@Detalles)
                        .load(response.body()?.image)
                        .into(ivImage)
                }*/



            }

            override fun onFailure(p0: Call<GameDetailDto>, p1: Throwable) {
                //Manejamos el error de conexión
                binding.pbLoading.visibility = View.INVISIBLE
            }

        })


    }
}