package com.example.mainmedapp.data.localDataSource.repository

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.mainmedapp.data.localDataSource.CartEntity
import com.example.mainmedapp.data.localDataSource.MedDataBase
import com.example.mainmedapp.data.localDataSource.PatientEntity
import com.example.mainmedapp.domain.repository.LocalRepository

/**
    Автор: Каргин Максим (участник №3)
    Дата создания: 05.04.2023
    Назначение: Реализация local data source репозитория
 */

class LocalRepositoryImpl(private val context: Application):LocalRepository {

    private val isAlreadySeenSharedPreferences = context.getSharedPreferences("isAlready", MODE_PRIVATE)
    private val tokenAndPasswordSharedPreferences = context.getSharedPreferences("tokenAndPassword", MODE_PRIVATE)
    private val dao = MedDataBase.getInstance(context).getDao()
    override fun isAlreadySeenOnBoarding(): Boolean {
        return isAlreadySeenSharedPreferences.getBoolean("isAlreadySeenOnBoardingFragment", false)
    }

    override fun setIsAlreadySeenOnBoarding(): Boolean {
        isAlreadySeenSharedPreferences.edit().putBoolean("isAlreadySeenOnBoardingFragment", true).apply()
        return true
    }

    override fun getToken(): String? {
        return tokenAndPasswordSharedPreferences.getString("token", "")
    }

    override suspend fun insertCartItem(item: CartEntity) {
        dao.insertCartItem(item)
    }

    override suspend fun deleteCartItem(item: CartEntity) {
        dao.deleteCartItem(item)
    }

    override suspend fun getAllItems(): List<CartEntity> {
        return dao.getAllItems()
    }

    override suspend fun getItemsPrice(): Int {
        return dao.getItemsPrice()
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }
}