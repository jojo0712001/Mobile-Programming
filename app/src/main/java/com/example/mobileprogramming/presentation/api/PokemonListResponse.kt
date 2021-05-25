package com.example.mobileprogramming.presentation.api

import com.example.mobileprogramming.presentation.list.Pokemon

data class PokemonListResponse(
    val count:Int,
    val next: String,
    val results: List<Pokemon>

)