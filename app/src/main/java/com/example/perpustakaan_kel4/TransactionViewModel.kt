package com.example.perpustakaan_kel4

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TransactionViewModel : ViewModel() {

    val currentTransaction : MutableLiveData<List<Pinjam>> by lazy {
        MutableLiveData<List<Pinjam>>()
    }

    fun insertTransactionList(newBookings: Pinjam){
        val currentList = currentTransaction.value?.toMutableList() ?: mutableListOf()
        currentList.add(newBookings)
        currentTransaction.value = currentList.toList()
    }

    override fun onCleared() {
        super.onCleared()
    }

}