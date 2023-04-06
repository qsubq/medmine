package com.example.mainmedapp.data.remoteDataSource.repository

import com.example.mainmedapp.data.remoteDataSource.Retrofit
import com.example.mainmedapp.domain.model.*
import com.example.mainmedapp.domain.repository.RemoteRepository
import retrofit2.Response

/**
Автор: Каргин Максим (участник №3)
Дата создания: 05.04.2023
Назначение: Реализация remote data source репозитория
 */

class RemoteRepositoryImpl : RemoteRepository {
    private val api = Retrofit.api

    override suspend fun sendCode(email: String): Response<ResponseSendCodeModel> {
        return api.sendCode(email)
    }

    override suspend fun signIn(email: String, code: String): Response<ResponseSignInModel> {
        return api.signIn(email, code)
    }

    override suspend fun createProfile(
        token: String,
        profile: RequestCreateProfileModel
    ): Response<ResponseCreateProfileModel> {
        return api.createProfile(token, profile)
    }

    override suspend fun getNews(): Response<List<ResponseGetNewsModel>> {
        return api.getNews()
    }

    override suspend fun getCatalog(): Response<List<ResponseGetCatalogModel>> {
        return api.getCatalog()
    }

    override suspend fun updateProfile(
        token: String,
        profile: RequestUpdateProfileModel
    ): Response<ResponseCreateProfileModel> {
        return api.updateProfile(token,profile)
    }
}