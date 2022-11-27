package com.example.appbusiness.presentation.business.main

import com.example.appbusiness.domain.model.Business

sealed class BusinessMainEvent {
    object GetAllBusiness : BusinessMainEvent()
    data class DeleteBusiness(val business: Business) : BusinessMainEvent()
    object RestoreBusinessDeleted : BusinessMainEvent()
    data class OnPopupDismissed(val open: Boolean = false, val business: Business? = null) :
        BusinessMainEvent()

    data class OnValueChangeRating(val rating: Float) : BusinessMainEvent()
    object SaveRating : BusinessMainEvent()
}
