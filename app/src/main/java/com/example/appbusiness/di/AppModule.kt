package com.example.appbusiness.di

import android.app.Application
import androidx.room.Room
import com.example.appbusiness.data.local.BusinessDataBase
import com.example.appbusiness.data.repository.BusinessRepositoryImpl
import com.example.appbusiness.domain.repository.BusinessRepository
import com.example.appbusiness.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBusinessDatabase(app: Application): BusinessDataBase {
        return Room.databaseBuilder(
            app,
            BusinessDataBase::class.java,
            "business_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBusinessRepository(dataBase: BusinessDataBase): BusinessRepository {
        return BusinessRepositoryImpl(dataBase.businessDao)
    }

    @Provides
    @Singleton
    fun provideBusinessUseCase(repository: BusinessRepository) : BusinessUseCase {
        return BusinessUseCase(
            getAllBusiness = GetAllBusiness(repository),
            addBusiness = AddBusiness(repository),
            getBusiness = GetBusiness(repository),
            deleteBusiness = DeleteBusiness(repository),
            updateBusiness = UpdateBusiness(repository)
        )
    }

}