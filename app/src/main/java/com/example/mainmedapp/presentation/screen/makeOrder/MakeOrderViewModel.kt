package com.example.mainmedapp.presentation.screen.makeOrder

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mainmedapp.data.localDataSource.PatientEntity
import com.example.mainmedapp.domain.model.RequestCreateOrderModel
import com.example.mainmedapp.domain.model.ResponseCreateOrderModel
import com.example.mainmedapp.domain.useCase.CreateOrderUseCase
import com.example.mainmedapp.domain.useCase.GetItemsPriceUseCase
import com.example.mainmedapp.domain.useCase.GetTokenUseCase
import kotlinx.coroutines.launch
import retrofit2.Response

/**
Автор: Каргин Максим (участник №3)
Дата создания: 07.04.2023
Назначение: ViewModel фрагмента оформления заказа
 */


class MakeOrderViewModel(private val context: Application) : AndroidViewModel(context) {

    //UseCase
    private val createOrderUseCase = CreateOrderUseCase()
    private val getTokenUseCase = GetTokenUseCase(context)
    private val getItemsPriceUseCase = GetItemsPriceUseCase(context)


    //LiveData
    var createOrderLiveData: MutableLiveData<Response<ResponseCreateOrderModel>> =
        MutableLiveData()
    var priceLiveData: MutableLiveData<Int> = MutableLiveData()
    var errorLiveData: MutableLiveData<Boolean> = MutableLiveData()

    //Функция создания заказа
    fun createOrder(order: RequestCreateOrderModel) {
        if (isOnline(context)) {
            viewModelScope.launch {
                createOrderLiveData.value =
                    getTokenUseCase.execute()?.let { createOrderUseCase.execute(it, order) }
            }
        } else {
            viewModelScope.launch {
                errorLiveData.value = false
            }
        }
    }

    //Функция получения итоговой стоимости
    fun getItemsPrice() {
        viewModelScope.launch {
            priceLiveData.value = getItemsPriceUseCase.execute()
        }
    }


    //Функция проверки интернет соединения
    private fun isOnline(context: Application): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

        return (networkCapabilities != null)
    }

}