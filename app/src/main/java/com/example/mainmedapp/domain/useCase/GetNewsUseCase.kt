package com.example.mainmedapp.domain.useCase

import com.example.mainmedapp.data.remoteDataSource.repository.RemoteRepositoryImpl
import com.example.mainmedapp.domain.model.ResponseGetNewsModel
import com.example.mainmedapp.domain.repository.RemoteRepository
import retrofit2.Response

/**
Автор: Каргин Максим (участник №3)
Дата создания: 06.04.2023
Назначение: UseCase получения новостей
 */

class GetNewsUseCase {
    private val repository: RemoteRepository = RemoteRepositoryImpl()

    suspend fun execute(): Response<List<ResponseGetNewsModel>> {
        return repository.getNews()
    }
}
