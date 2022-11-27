package com.example.appbusiness.domain.usecase

import com.example.appbusiness.domain.model.Business
import com.example.appbusiness.domain.repository.BusinessRepository

class GetBusiness(private val repository: BusinessRepository) {
    suspend operator fun invoke(id: Int): Business? {
        return repository.getById(id)
    }
}