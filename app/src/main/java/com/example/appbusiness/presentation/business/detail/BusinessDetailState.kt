package com.example.appbusiness.presentation.business.detail

data class BusinessDetailState(
    val name: String = "",
    val phone: String = "",
    val about: String = "",
    val rating: Int = 0,
    val countRating: Int = 0
)