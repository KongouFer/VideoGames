package com.amaurypm.videogames.data.remote

import com.amaurypm.videogames.data.remote.model.PokemonDetail2Dto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET("api/v2/pokemon/{id}")
    fun getPokemonDetail(
        @Path("id") id: String
    ): Call<PokemonDetail2Dto>

}