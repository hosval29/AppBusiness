package com.example.appbusiness.domain.usecase

import com.example.appbusiness.domain.model.Business
import com.example.appbusiness.domain.repository.BusinessRepository
import kotlinx.coroutines.flow.Flow


class GetAllBusiness(
    private val repository: BusinessRepository
) {
    operator fun invoke(): Flow<List<Business>> {
        return repository.getAll()
    }
}