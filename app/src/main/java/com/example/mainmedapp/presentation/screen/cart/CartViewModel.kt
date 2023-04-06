package com.example.mainmedapp.presentation.screen.cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mainmedapp.data.localDataSource.CartEntity
import com.example.mainmedapp.domain.useCase.*
import kotlinx.coroutines.launch

class CartViewModel(private val context: Application) : AndroidViewModel(context) {

    //UseCase
    private val deleteCartItemUseCase = DeleteCartItemUseCase(context)
    private val getItemsPriceUseCase = GetItemsPriceUseCase(context)
    private val deleteAllItemsUseCase = DeleteAllItemsUseCase(context)
    private val getAllItemsUseCase = GetAllItemsUseCase(context)
    private val insertCartItemUseCase = InsertCartItemUseCase(context)

    //LiveData
    var itemsLiveData: MutableLiveData<List<CartEntity>> = MutableLiveData()
    var priceLiveData: MutableLiveData<Int> = MutableLiveData()

    fun deleteAll() {
        viewModelScope.launch {
            deleteAllItemsUseCase.execute()
            priceLiveData.value = getItemsPriceUseCase.execute()
            itemsLiveData.value = getAllItemsUseCase.execute()
        }
    }

    fun getAllItems() {
        viewModelScope.launch {
            itemsLiveData.value = getAllItemsUseCase.execute()
            priceLiveData.value = getItemsPriceUseCase.execute()
        }
    }

    fun deleteItem(item: CartEntity) {
        viewModelScope.launch {
            deleteCartItemUseCase.execute(item)
            priceLiveData.value = getItemsPriceUseCase.execute()
            itemsLiveData.value = getAllItemsUseCase.execute()
        }
    }

    fun insertItem(item: CartEntity) {
        viewModelScope.launch {
            insertCartItemUseCase.execute(item)
            itemsLiveData.value = getAllItemsUseCase.execute()
            priceLiveData.value = getItemsPriceUseCase.execute()
        }
    }

}