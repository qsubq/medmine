package com.example.mainmedapp.domain.repository

import com.example.mainmedapp.data.localDataSource.CartEntity
import com.example.mainmedapp.data.localDataSource.PatientEntity


/**
    Автор: Каргин Максим (участник №3)
    Дата создания: 05.04.2023
    Назначение: Интерфейс local data source репозитория
 */

interface LocalRepository {
    fun isAlreadySeenOnBoarding():Boolean
    fun setIsAlreadySeenOnBoarding():Boolean
    fun getToken():String?

    suspend fun insertCartItem(item:CartEntity)
    suspend fun deleteCartItem(item: CartEntity)
    suspend fun getAllItems():List<CartEntity>
    suspend fun getItemsPrice():Int
    suspend fun deleteAll()
}