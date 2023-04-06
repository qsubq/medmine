package com.example.mainmedapp.presentation.screen.analyses

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mainmedapp.domain.model.ResponseGetCatalogModel
import com.example.mainmedapp.domain.model.ResponseGetNewsModel
import com.example.mainmedapp.domain.useCase.GetCatalogUseCase
import com.example.mainmedapp.domain.useCase.GetNewsUseCase
import kotlinx.coroutines.launch
import retrofit2.Response

/**
Автор: Каргин Максим (участник №3)
Дата создания: 06.04.2023
Назначение: ViewModel фрагмента "Анализы/Главная"
 */

class AnalysesViewModel(private val context: Application) : AndroidViewModel(context) {
    //UseCase
    private val getNewsUseCase = GetNewsUseCase()
    private val getCatalogUseCase = GetCatalogUseCase()

    //LiveData
    var newsLiveData: MutableLiveData<Response<List<ResponseGetNewsModel>>> = MutableLiveData()
    var catalogLiveData: MutableLiveData<Response<List<ResponseGetCatalogModel>>> =
        MutableLiveData()
    var errorLiveData: MutableLiveData<Boolean> = MutableLiveData()


    //Функция получения данных каталога
    fun getCatalog() {
        if (isOnline(context)) {
            viewModelScope.launch {
                catalogLiveData.value = getCatalogUseCase.execute()
            }
        } else {
            viewModelScope.launch {
                errorLiveData.value = false
            }
        }
    }

    //Функция получения данных новостей
    fun getNews() {
        if (isOnline(context)) {
            viewModelScope.launch {
                newsLiveData.value = getNewsUseCase.execute()
            }
        } else {
            viewModelScope.launch {
                errorLiveData.value = false
            }
        }
    }

    //Функция проверки онлайн ли устройство
    private fun isOnline(context: Application): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

        return (networkCapabilities != null)
    }
}