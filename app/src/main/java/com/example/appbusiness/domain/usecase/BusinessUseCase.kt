package com.example.appbusiness.domain.usecase

data class BusinessUseCase(
    val getAllBusiness: GetAllBusiness,
    val addBusiness: AddBusiness,
    val getBusiness: GetBusiness,
    val deleteBusiness: DeleteBusiness,
    val updateBusiness: UpdateBusiness
)