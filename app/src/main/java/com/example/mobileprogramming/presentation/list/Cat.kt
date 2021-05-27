package com.example.mobileprogramming.presentation.list

data class Cat (
    val name: String,
    val image: Image,
    val origin: String,
    val affection_level: Int,
    val temperament: String,
    val life_span:String
)

data class Image (
    val url: String
)
