package com.example.mainmedapp.domain.repository


/**
    Автор: Каргин Максим (участник №3)
    Дата создания: 05.04.2023
    Назначение: Интерфейс local data source репозитория
 */

interface LocalRepository {
    fun isAlreadySeenOnBoarding():Boolean
    fun setIsAlreadySeenOnBoarding():Boolean
}