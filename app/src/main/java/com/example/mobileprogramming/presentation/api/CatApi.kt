package com.example.mobileprogramming.presentation.api

import com.example.mobileprogramming.presentation.list.Cat
import retrofit2.Call
import retrofit2.http.GET

interface CatApi {
    @GET( "breeds")
    fun getCatList(): Call<List<Cat>>

    //@GET( "pokemon/{id}")
    //fun getPokemonDetail(@Path("id") id:Int): Call<PokemonDetailResponse>
}