package com.example.mainmedapp.domain.model

/**
Автор: Каргин Максим (участник №3)
Дата создания: 07.04.2023
Назначение: Моделька для запрооса создания заказа
 */

data class RequestCreateOrderModel(
    val address:String,
    val date_time:String,
    val phone:String,
    val comment:String,
    val audio_comment:String,
    val patients: List<PatientModel>
)