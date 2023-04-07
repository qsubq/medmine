package com.example.mainmedapp.domain.useCase

import com.example.mainmedapp.data.remoteDataSource.repository.RemoteRepositoryImpl
import com.example.mainmedapp.domain.model.RequestCreateOrderModel
import com.example.mainmedapp.domain.model.ResponseCreateOrderModel
import com.example.mainmedapp.domain.repository.RemoteRepository
import retrofit2.Response

/**
Автор: Каргин Максим (участник №3)
Дата создания: 07.04.2023
Назначение: UseCase для создания заказа
 */

class CreateOrderUseCase{
    private val repository:RemoteRepository = RemoteRepositoryImpl()

    suspend fun execute(token:String, order:RequestCreateOrderModel): Response<ResponseCreateOrderModel> {
        return repository.createOrder(token, order)
    }
}
