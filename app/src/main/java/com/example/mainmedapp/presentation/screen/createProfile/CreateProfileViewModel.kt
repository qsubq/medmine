package com.example.mainmedapp.presentation.screen.createProfile

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mainmedapp.domain.model.RequestCreateProfileModel
import com.example.mainmedapp.domain.model.ResponseCreateProfileModel
import com.example.mainmedapp.domain.useCase.CreateProfileUseCase
import kotlinx.coroutines.launch
import retrofit2.Response

/**
Автор: Каргин Максим (участник №3)
Дата создания: 05.04.2023
Назначение: ViewModel на создание пароля
 */

class CreateProfileViewModel(private val context: Application) : AndroidViewModel(context) {

    //UseCase
    private val createProfileUseCase = CreateProfileUseCase()


    //LiveData
    var createProfileLiveData: MutableLiveData<Response<ResponseCreateProfileModel>> =
        MutableLiveData()
    var errorLiveData: MutableLiveData<Boolean> = MutableLiveData()

    //Функция создания профиля
    fun createProfile(profile: RequestCreateProfileModel) {
        if (isOnline(context)) {
            viewModelScope.launch {
                createProfileLiveData.value = createProfileUseCase.execute(getToken(), profile)
            }
        } else {
            viewModelScope.launch {
                errorLiveData.value = false
            }
        }
    }

    //функция проверки интернет соединения
    private fun isOnline(context: Application): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

        return (networkCapabilities != null)
    }

    private fun getToken(): String {
        val sharedPref = context.getSharedPreferences("tokenAndPassword", Context.MODE_PRIVATE)
        return "Bearer ${sharedPref.getString("token", "")}"
    }
}