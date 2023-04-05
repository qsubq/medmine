package com.example.mainmedapp.domain.useCase

import com.example.mainmedapp.data.remoteDataSource.repository.RemoteRepositoryImpl
import com.example.mainmedapp.domain.model.ResponseSendCodeModel
import com.example.mainmedapp.domain.repository.RemoteRepository
import retrofit2.Response

/**
Автор: Каргин Максим (участник №3)
Дата создания: 05.04.2023
Назначение: UseCase на отправку кода
 */

class SendCodeUseCase {
    private val repository:RemoteRepository = RemoteRepositoryImpl()

    suspend fun execute(email:String): Response<ResponseSendCodeModel>{
        return repository.sendCode(email)
    }
}