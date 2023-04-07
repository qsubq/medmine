package com.example.mainmedapp.domain.useCase

import android.app.Application
import com.example.mainmedapp.data.localDataSource.repository.LocalRepositoryImpl
import com.example.mainmedapp.domain.repository.LocalRepository

/**
Автор: Каргин Максим (участник №3)
Дата создания: 07.04.2023
Назначение: UseCase для получения сохраненного токена
 */

class GetTokenUseCase(private val context: Application) {
    private val repository:LocalRepository = LocalRepositoryImpl(context)

    fun execute():String?{
        return repository.getToken()
    }
}