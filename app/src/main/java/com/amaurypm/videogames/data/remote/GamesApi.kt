package com.amaurypm.videogames.data.remote

import com.amaurypm.videogames.data.remote.model.GameDetailDto
import com.amaurypm.videogames.data.remote.model.GameDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface GamesApi {

    //Aquí van las funciones
    //para los endpoints

    //https://www.serverbpw.com/cm/games/games_list.php
    @GET
    fun getGames(
        @Url url: String
    ): Call<List<GameDto>>

    //getGames("cm/games/games_list.php")

    //https://www.serverbpw.com/cm/games/game_detail.php?id=21357
    //https://www.serverbpw.com/cm/games/game_detail.php?id=21357&name=amaury

    @GET("cm/games/game_detail.php?")
    fun getGameDetail(
        @Query("id") id: String/*,
        @Query("name") user: String*/
    ): Call<GameDetailDto>
    //getGameDetail("21357")
    //getGameDetail("21357", "amaury")


    //https://private-a649a-games28.apiary-mock.com/games/game_detail/21357/amaury
    @GET("games/game_detail/{id}")
    fun getGameDetail2(
        @Path("id") id: String
    ): Call<GameDetailDto>




}