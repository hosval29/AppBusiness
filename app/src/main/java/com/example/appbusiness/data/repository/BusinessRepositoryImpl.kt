package com.example.appbusiness.data.repository

import com.example.appbusiness.data.local.dao.BusinessDao
import com.example.appbusiness.data.mapper.toBusiness
import com.example.appbusiness.data.mapper.toBusinessEntity
import com.example.appbusiness.domain.model.Business
import com.example.appbusiness.domain.repository.BusinessRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BusinessRepositoryImpl @Inject constructor(
    private val dao: BusinessDao
) : BusinessRepository {
    override suspend fun create(business: Business) {
        dao.create(business.toBusinessEntity())
    }

    override fun getAll(): Flow<List<Business>> {
        return dao.getAll().map { entities -> entities.map { it.toBusiness() } }
    }

    override suspend fun getById(id: Int): Business? {
        return dao.getById(id)?.toBusiness()
    }

    override suspend fun update(business: Business) {
        dao.update(business.toBusinessEntity())
    }

    override suspend fun delete(business: Business) {
        dao.delete(business.toBusinessEntity())
    }
}