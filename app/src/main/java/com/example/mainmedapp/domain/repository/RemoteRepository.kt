package com.example.mainmedapp.domain.repository

import com.example.mainmedapp.domain.model.*
import retrofit2.Response

/**
Автор: Каргин Максим (участник №3)
Дата создания: 05.04.2023
Назначение: Интерфейс remote data source репозитория, в котором описаны методы доступа к api
 */

interface RemoteRepository {
    suspend fun sendCode(email: String): Response<ResponseSendCodeModel>
    suspend fun signIn(email: String, code: String): Response<ResponseSignInModel>
    suspend fun createProfile(
        token: String,
        profile: RequestCreateProfileModel
    ): Response<ResponseCreateProfileModel>

    suspend fun getNews(): Response<List<ResponseGetNewsModel>>
    suspend fun getCatalog(): Response<List<ResponseGetCatalogModel>>
}