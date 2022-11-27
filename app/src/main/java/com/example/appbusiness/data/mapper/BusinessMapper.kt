package com.example.appbusiness.data.mapper

import com.example.appbusiness.data.local.entities.BusinessEntity
import com.example.appbusiness.domain.model.Business

fun BusinessEntity.toBusiness(): Business {
    return Business(
        id,
        name,
        about,
        phone,
        photo,
        rating,
        countRating,
        created,
        update
    )
}

fun Business.toBusinessEntity(): BusinessEntity {
    return BusinessEntity(
        id = id,
        name = name,
        about = about,
        phone = phone,
        photo = photo,
        rating = rating,
        countRating = countRating,
        created = created,
        update = update
    )
}