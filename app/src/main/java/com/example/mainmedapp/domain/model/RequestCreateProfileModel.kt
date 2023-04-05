package com.example.mainmedapp.domain.model
/**
Автор: Каргин Максим (участник №3)
Дата создания: 05.04.2023
Назначение: Моделька на отправку запроса создания профиля
 */

data class RequestCreateProfileModel(
    val id: Int,
    val firstname:String,
    val lastname:String,
    val middlename:String,
    val bith:String,
    val pol:String,
    val image:String
)
