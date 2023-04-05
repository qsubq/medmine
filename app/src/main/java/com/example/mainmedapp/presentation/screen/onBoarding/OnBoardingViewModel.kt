package com.example.mainmedapp.presentation.screen.onBoarding

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.mainmedapp.domain.useCase.IsAlreadySeenOnBoardingUseCase
import com.example.mainmedapp.domain.useCase.SetIsAlreadySeenOnBoardingUseCase

/**
    Автор: Каргин Максим (участник №3)
    Дата создания: 05.04.2023
    Назначение: ViewModel для OnBoarding экрана
 */

class OnBoardingViewModel(private val context: Application):AndroidViewModel(context) {

    // Получение ссылок на UseCase'ы
    private val isAlreadySeenOnBoardingUseCase = IsAlreadySeenOnBoardingUseCase(context)
    private val setIsAlreadySeenOnBoardingUseCase = SetIsAlreadySeenOnBoardingUseCase(context)

    //Функция для проверки, просмотрен ли уже OnBoarding
    fun isAlreadySeenUseCase():Boolean{
        return isAlreadySeenOnBoardingUseCase.execute()
    }

    //Сохранение информации о том, что пользователь уже смотрел OnBoardingScreen
    fun setIsAlreadySeenOnBoardingUseCase():Boolean{
        return setIsAlreadySeenOnBoardingUseCase.execute()
    }
}