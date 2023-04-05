package com.example.mainmedapp.data.remoteDataSource

import com.example.mainmedapp.domain.model.RequestCreateProfileModel
import com.example.mainmedapp.domain.model.ResponseCreateProfileModel
import com.example.mainmedapp.domain.model.ResponseSendCodeModel
import com.example.mainmedapp.domain.model.ResponseSignInModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
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

}