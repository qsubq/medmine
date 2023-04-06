package com.example.mainmedapp.domain.useCase

import com.example.mainmedapp.data.remoteDataSource.repository.RemoteRepositoryImpl
import com.example.mainmedapp.domain.model.RequestUpdateProfileModel
import com.example.mainmedapp.domain.model.ResponseCreateProfileModel
import com.example.mainmedapp.domain.repository.RemoteRepository
import retrofit2.Response

class UpdateProfileUseCase {
    private val repository: RemoteRepository = RemoteRepositoryImpl()

    suspend fun execute(
        token: String,
        profileModel: RequestUpdateProfileModel
    ): Response<ResponseCreateProfileModel> {
        return repository.updateProfile(token, profileModel)
    }
}