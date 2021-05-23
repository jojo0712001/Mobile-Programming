package com.example.mobileprogramming.presentation.api

import android.telecom.Call
import retrofit2.http.GET

interface PokeApi {
    @GET( "pokemon")
    fun getPokemonList(): Call<PokemonResponse>
}