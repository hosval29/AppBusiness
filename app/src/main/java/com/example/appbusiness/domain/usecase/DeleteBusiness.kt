package com.example.appbusiness.domain.usecase

import com.example.appbusiness.domain.model.Business
import com.example.appbusiness.domain.repository.BusinessRepository

class DeleteBusiness(private val repository: BusinessRepository) {
    suspend operator fun invoke(business: Business) {
        repository.delete(business)
    }
}