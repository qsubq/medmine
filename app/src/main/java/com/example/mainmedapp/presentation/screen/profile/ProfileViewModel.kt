package com.example.mainmedapp.presentation.screen.profile

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mainmedapp.domain.model.RequestCreateProfileModel
import com.example.mainmedapp.domain.model.RequestUpdateProfileModel
import com.example.mainmedapp.domain.model.ResponseCreateProfileModel
import com.example.mainmedapp.domain.useCase.CreateProfileUseCase
import com.example.mainmedapp.domain.useCase.UpdateProfileUseCase
import kotlinx.coroutines.launch
import retrofit2.Response

class ProfileViewModel (private val context: Application) : AndroidViewModel(context) {

    //UseCase
    private val updateProfileUseCase = UpdateProfileUseCase()


    //LiveData
    var updateProfileLiveData: MutableLiveData<Response<ResponseCreateProfileModel>> =
        MutableLiveData()
    var errorLiveData: MutableLiveData<Boolean> = MutableLiveData()

    //Функция создания профиля
    fun updateProfile(profile: RequestUpdateProfileModel) {
        if (isOnline(context)) {
            viewModelScope.launch {
                updateProfileLiveData.value = updateProfileUseCase.execute(getToken(), profile)
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