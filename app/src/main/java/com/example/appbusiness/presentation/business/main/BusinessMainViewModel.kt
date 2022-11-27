package com.example.appbusiness.presentation.business.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appbusiness.domain.model.Business
import com.example.appbusiness.domain.usecase.BusinessUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusinessMainViewModel @Inject constructor(
    private val businessUseCase: BusinessUseCase
) : ViewModel() {

    var state by mutableStateOf(BusinessMainState())
        private set

    private var recentlyDeleteBusiness: Business? = null
    private var recentlyRantingBusiness: Business? = null

    init {
        onEvent(BusinessMainEvent.GetAllBusiness)
    }

    fun onEvent(event: BusinessMainEvent) {
        when (event) {
            is BusinessMainEvent.GetAllBusiness -> {
                businessUseCase.getAllBusiness().onEach { business ->
                    state = state.copy(
                        business = business.toMutableList()
                    )
                }.launchIn(viewModelScope)
            }

            is BusinessMainEvent.DeleteBusiness -> {
                viewModelScope.launch {
                    businessUseCase.deleteBusiness(event.business)
                    recentlyDeleteBusiness = event.business
                }
            }

            is BusinessMainEvent.RestoreBusinessDeleted -> {
                viewModelScope.launch {
                    businessUseCase.addBusiness(recentlyDeleteBusiness ?: return@launch)
                    recentlyDeleteBusiness = null
                }
            }

            is BusinessMainEvent.OnPopupDismissed -> {
                state = state.copy(openDialogRating = event.open)
                recentlyRantingBusiness = event.business
            }

            is BusinessMainEvent.OnValueChangeRating -> {
                state = state.copy(rating = event.rating)
                recentlyRantingBusiness = recentlyRantingBusiness?.copy(rating = event.rating)
            }

            is BusinessMainEvent.SaveRating -> {
                viewModelScope.launch {
                    businessUseCase.updateBusiness(
                        business = recentlyRantingBusiness ?: return@launch
                    )
                    recentlyRantingBusiness = null
                }
            }

        }
    }
}