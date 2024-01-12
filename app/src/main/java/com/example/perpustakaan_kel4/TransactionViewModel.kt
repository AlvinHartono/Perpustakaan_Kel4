package com.example.perpustakaan_kel4

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TransactionViewModel : ViewModel() {

    val currentTransaction : MutableLiveData<List<Pinjam>> by lazy {
        MutableLiveData<List<Pinjam>>()
    }

    fun insertBookingList(booking: Pinjam){
        try {
            val currentList = currentTransaction.value?.toMutableList() ?: mutableListOf()
            currentList.add(booking)
            Log.d("response insert zz", currentList.toList().toString())
            currentTransaction.value = currentList.toList()
//        Log.d("response insert", currentBooking.value!![1].judul_buku)
        } catch (e : Exception){
            Log.d("response", e.toString())
        }
    }

    fun cancelBooking(booking : Pinjam){
        try {
            val currentList = currentTransaction.value?.toMutableList() ?: mutableListOf()
            currentList.remove(booking)
            currentTransaction.value = currentList

        } catch (e : Exception){
            Log.d("response", e.toString())
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

}