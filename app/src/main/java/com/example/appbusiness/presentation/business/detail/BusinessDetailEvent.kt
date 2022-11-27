package com.example.appbusiness.presentation.business.detail

import com.example.appbusiness.presentation.business.main.BusinessMainEvent

sealed class BusinessDetailEvent {
    data class OnValueChange(
        val value: String,
        val field: BusinessDetailViewModel.Companion.Fields
    ) : BusinessDetailEvent()

    object SaveBusiness : BusinessDetailEvent()
}