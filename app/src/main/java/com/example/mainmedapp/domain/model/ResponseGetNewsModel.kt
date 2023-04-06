package com.example.mainmedapp.domain.model

/**
Автор: Каргин Максим (участник №3)
Дата создания: 06.04.2023
Назначение: Моделька ответа получения новостей
 */

data class ResponseGetNewsModel(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val image: String
)