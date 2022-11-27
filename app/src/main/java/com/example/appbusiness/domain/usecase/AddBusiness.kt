package com.example.appbusiness.domain.usecase

import com.example.appbusiness.domain.model.Business
import com.example.appbusiness.domain.model.InvalidBusinessException
import com.example.appbusiness.domain.repository.BusinessRepository

class AddBusiness(private val repository: BusinessRepository) {

    @Throws(InvalidBusinessException::class)
    suspend operator fun invoke(business: Business) {
        if (business.name.isBlank()) {
            throw InvalidBusinessException("El campo nombre no puede ser vacio")
        }

        if (business.phone.isBlank()) {
            throw InvalidBusinessException("El campo teléfono no puede ser vacio")
        }

        if (business.about.isBlank()) {
            throw InvalidBusinessException("El campo Acerca no puede ser vacio")
        }

        repository.create(business)
    }
}