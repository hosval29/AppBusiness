package com.example.appbusiness.presentation.business.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appbusiness.domain.model.Business
import com.example.appbusiness.domain.model.InvalidBusinessException
import com.example.appbusiness.domain.repository.BusinessRepository
import com.example.appbusiness.domain.usecase.BusinessUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusinessDetailViewModel @Inject constructor(
    private val businessUseCase: BusinessUseCase
) : ViewModel() {

    var state by mutableStateOf(BusinessDetailState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {

    }

    fun onEvent(event: BusinessDetailEvent) {
        when(event) {

            is BusinessDetailEvent.OnValueChange -> {
                when(event.field) {
                    Fields.NAME -> {
                        state = state.copy(
                            name = event.value
                        )
                    }
                    Fields.PHONE -> {
                        state = state.copy(
                            phone = event.value
                        )
                    }
                    Fields.ABOUT -> {
                        state = state.copy(
                            about = event.value
                        )
                    }
                }
            }

            is BusinessDetailEvent.SaveBusiness -> {
                viewModelScope.launch {
                    try {
                        businessUseCase.addBusiness(
                            Business(
                                name = state.name,
                                phone = state.phone,
                                about = state.about
                            )
                        )
                        _uiEvent.send(UiEvent.SaveBusiness)
                    } catch (e: InvalidBusinessException) {
                        _uiEvent.send(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "No se pudo guardar el negocio"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveBusiness : UiEvent()
    }

    companion object {
        enum class Fields {
            NAME,
            PHONE,
            ABOUT
        }
    }
}