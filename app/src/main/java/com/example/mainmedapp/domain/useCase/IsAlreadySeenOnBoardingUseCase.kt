package com.example.mainmedapp.domain.useCase

import android.app.Application
import com.example.mainmedapp.data.localDataSource.repository.LocalRepositoryImpl
import com.example.mainmedapp.domain.repository.LocalRepository

/**
    Автор: Каргин Максим (участник №3)
    Дата создания: 05.04.2023
    Назначение: UseCase для получения информации о том, просмотрен ли OnBoarding

 */

class IsAlreadySeenOnBoardingUseCase(private val context: Application) {
    private val repository:LocalRepository = LocalRepositoryImpl(context)

    fun execute():Boolean{
        return repository.isAlreadySeenOnBoarding()
    }
}