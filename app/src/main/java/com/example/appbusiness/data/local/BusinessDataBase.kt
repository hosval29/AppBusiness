package com.example.appbusiness.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appbusiness.data.local.dao.BusinessDao
import com.example.appbusiness.data.local.entities.BusinessEntity

@Database(
    entities = [BusinessEntity::class],
    version = 1
)
abstract class BusinessDataBase : RoomDatabase() {

    abstract val businessDao: BusinessDao

}