package com.example.appbusiness.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "business_table")
data class BusinessEntity(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "about") val about: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "photo") val photo: String,
    @ColumnInfo(name = "rating") val rating: Float,
    @ColumnInfo(name = "countRating") val countRating: Int,
    @ColumnInfo(name = "createdAt", defaultValue = "CURRENT_TIMESTAMP")
    val created: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "updateAt", defaultValue = "CURRENT_TIMESTAMP")
    val update: Long = System.currentTimeMillis(),
)