package com.example.mainmedapp.data.remoteDataSource
/**
Автор: Каргин Максим (участник №3)
Дата создания: 05.04.2023
Назначение: Создание singleton обьекта retrofit'a
 */

import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    private const val BASE_URL = "https://medic.madskill.ru"

    private val retrofit = retrofit2.Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api:MedApi = retrofit.create(MedApi::class.java)
}