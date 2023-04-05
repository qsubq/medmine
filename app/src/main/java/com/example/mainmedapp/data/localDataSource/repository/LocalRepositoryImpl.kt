package com.example.mainmedapp.data.localDataSource.repository

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.mainmedapp.domain.repository.LocalRepository

/**
    Автор: Каргин Максим (участник №3)
    Дата создания: 05.04.2023
    Назначение: Реализация local data source репозитория
 */

class LocalRepositoryImpl(private val context: Application):LocalRepository {

    private val isAlreadySeenSharedPreferences = context.getSharedPreferences("isAlready", MODE_PRIVATE)
    override fun isAlreadySeenOnBoarding(): Boolean {
        return isAlreadySeenSharedPreferences.getBoolean("isAlreadySeenOnBoardingFragment", false)
    }

    override fun setIsAlreadySeenOnBoarding(): Boolean {
        isAlreadySeenSharedPreferences.edit().putBoolean("isAlreadySeenOnBoardingFragment", true).apply()
        return true
    }
}