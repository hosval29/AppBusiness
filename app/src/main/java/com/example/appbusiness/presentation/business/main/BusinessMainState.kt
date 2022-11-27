package com.example.appbusiness.presentation.business.main

import com.example.appbusiness.domain.model.Business

data class BusinessMainState(
    val business: MutableList<Business> = mutableListOf(),
    val openDialogRating: Boolean = false,
    val rating: Float = 0F
)
