package com.example.appbusiness.domain.repository

import com.example.appbusiness.domain.model.Business
import kotlinx.coroutines.flow.Flow

interface BusinessRepository {

    suspend fun create(business: Business)

    fun getAll(): Flow<List<Business>>

    suspend fun getById(id: Int): Business?

    suspend fun update(business: Business)

    suspend fun delete(business: Business)

}