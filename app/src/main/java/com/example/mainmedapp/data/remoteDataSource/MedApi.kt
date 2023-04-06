package com.example.mainmedapp.data.remoteDataSource

import com.example.mainmedapp.domain.model.*
import retrofit2.Response
import retrofit2.http.*

/**
Автор: Каргин Максим (участник №3)
Дата создания: 05.04.2023
Назначение: api
 */

interface MedApi {

    //Функция отправки кода
    @POST("/api/sendCode")
    @Headers("Accept: application/json")
    suspend fun sendCode(@Header("email") email: String): Response<ResponseSendCodeModel>

    //Функция авторизации
    @POST("/api/signin")
    @Headers("Accept: application/json")
    suspend fun signIn(
        @Header("email") email: String,
        @Header("code") code: String
    ): Response<ResponseSignInModel>

    //Функция создания профиля
    @POST("/api/createProfile")
    @Headers("Accept: application/json")
    suspend fun createProfile(
        @Header("Authorization") token: String,
        @Body profile: RequestCreateProfileModel
    ): Response<ResponseCreateProfileModel>

    //Функция получения новостей
    @GET("/api/news")
    @Headers("Accept: application/json")
    suspend fun getNews(): Response<List<ResponseGetNewsModel>>

    //Функция получения каталога
    @GET("/api/catalog")
    @Headers("Accept: application/json")
    suspend fun getCatalog(): Response<List<ResponseGetCatalogModel>>

}