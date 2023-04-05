package com.example.mainmedapp.presentation.screen.signIn

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
Назначение: ViewModel на экран отправки email кода
 */

class SignInViewModel(private val context:Application):AndroidViewModel(context) {

    //UseCase
    private val sendCodeUseCase = SendCodeUseCase()


    //LiveData
    var sendCodeLiveData: MutableLiveData<Response<ResponseSendCodeModel>> = MutableLiveData()
    var errorLiveData:MutableLiveData<Boolean> = MutableLiveData()

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

    //Функция проверки доступа в интернет
    private fun isOnline(context: Application): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

        return (networkCapabilities != null)
    }
}