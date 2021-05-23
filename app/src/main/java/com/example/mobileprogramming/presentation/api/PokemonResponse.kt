package com.example.mobileprogramming.presentation.api

import com.example.mobileprogramming.presentation.list.Pokemon

data class PokemonResponse(
    val count:Int,
    val next: String,
    val result: List<Pokemon>

)