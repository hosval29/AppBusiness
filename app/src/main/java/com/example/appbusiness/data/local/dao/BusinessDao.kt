package com.example.appbusiness.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.appbusiness.data.local.entities.BusinessEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface BusinessDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun create(businessEntity: BusinessEntity)

    @Query("SELECT * FROM business_table ORDER BY updateAt")
    fun getAll() : Flow<List<BusinessEntity>>

    @Query("SELECT * FROM business_table WHERE id == :id")
    suspend fun getById(id: Int): BusinessEntity?

    @Update
    suspend fun update(businessEntity: BusinessEntity)

    @Delete
    suspend fun delete(businessEntity: BusinessEntity)

}