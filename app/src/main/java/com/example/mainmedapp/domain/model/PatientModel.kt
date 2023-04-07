package com.example.mainmedapp.domain.model

/**
Автор: Каргин Максим (участник №3)
Дата создания: 07.04.2023
Назначение: Моделька для пациента в заказе
 */

data class PatientModel(
    val name:String,
    val items:List<ItemsModel>
)