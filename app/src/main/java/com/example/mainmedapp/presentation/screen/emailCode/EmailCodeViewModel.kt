package com.example.mainmedapp.presentation.screen.emailCode

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mainmedapp.domain.model.ResponseSendCodeModel
import com.example.mainmedapp.domain.model.ResponseSignInModel
import com.example.mainmedapp.domain.useCase.SendCodeUseCase
import com.example.mainmedapp.domain.useCase.SignInUseCase
import kotlinx.coroutines.launch
import retrofit2.Response

/**
Автор: Каргин Максим (участник №3)
Дата создания: 05.04.2023
Назначение: ViewModel на экран ввода email код
 */

class EmailCodeViewModel (private val context: Application): AndroidViewModel(context) {
    //UseCase
    private val sendCodeUseCase = SendCodeUseCase()
    private val signInUseCase = SignInUseCase()

    //LiveData
    var sendCodeLiveData: MutableLiveData<Response<ResponseSendCodeModel>> = MutableLiveData()
    var signInLiveData: MutableLiveData<Response<ResponseSignInModel>> = MutableLiveData()
    var errorLiveData: MutableLiveData<Boolean> = MutableLiveData()

    //Функция отправки кода
    fun sendCode(email:String){
        if(isOnline(context)){
            viewModelScope.launch {
                sendCodeLiveData.value = sendCodeUseCase.execute(email)
            }
        }else{
            viewModelScope.launch {
                errorLiveData.value = false
            }
        }
    }

    //Функция авторизации
    fun signIn(email:String, code: String){
        if(isOnline(context)){
            viewModelScope.launch {
                signInLiveData.value = signInUseCase.execute(email, code)
            }
        }else{
            viewModelScope.launch {
                errorLiveData.value = false
            }
        }
    }

    private fun isOnline(context: Application): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

        return (networkCapabilities != null)
    }
}