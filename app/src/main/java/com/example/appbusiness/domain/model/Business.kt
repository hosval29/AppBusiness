package com.example.appbusiness.domain.model

data class Business(
    val id: Int = 0,
    val name: String = "",
    val about: String = "",
    val phone: String = "",
    val photo: String = "",
    val rating: Float = 0F,
    val countRating: Int = 0,
    val created: Long = System.currentTimeMillis(),
    val update: Long = System.currentTimeMillis(),
)
