package com.example.mainmedapp.domain.useCase

import com.example.mainmedapp.data.remoteDataSource.repository.RemoteRepositoryImpl
import com.example.mainmedapp.domain.model.RequestCreateProfileModel
import com.example.mainmedapp.domain.model.ResponseCreateProfileModel
import com.example.mainmedapp.domain.repository.RemoteRepository
import retrofit2.Response


/**
Автор: Каргин Максим (участник №3)
Дата создания: 05.04.2023
Назначение: UseCase для создания профиля
 */

class CreateProfileUseCase {
    private val repository:RemoteRepository = RemoteRepositoryImpl()

    suspend fun execute(token:String, profileModel: RequestCreateProfileModel): Response<ResponseCreateProfileModel>{
        return repository.createProfile(token, profileModel)
    }
}