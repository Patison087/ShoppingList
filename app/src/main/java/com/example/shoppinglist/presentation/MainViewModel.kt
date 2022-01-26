package com.example.shoppinglist.presentation

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.*

class MainViewModel : ViewModel () {

    private val repository = ShopListRepositoryImpl
    private val getShopListUseCase = GetShopListUseCase (repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase (repository)
    private val editShopListUseCase = EditShopItemUseCase (repository)

    val shopList = MutableLiveData<List<ShopItem>> ()

    fun getShopList(){
        val list = getShopListUseCase.getShopList()
        shopList.value = list
    }
    fun deleteShopItem (shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
        getShopList()
    }
    fun changeEnabledState(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopListUseCase.editShopItem(newItem)
        getShopList()
    }
}