package com.example.perpustakaan_kel4

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookingViewModel : ViewModel() {

    val currentBooking : MutableLiveData<List<Pinjam>> by lazy {
        MutableLiveData<List<Pinjam>>()

    }

    fun insertBookingList(booking: Pinjam){
        val currentList = currentBooking.value?.toMutableList() ?: mutableListOf()
        currentList.add(booking)
        currentBooking.value = currentList.toList()
    }

    fun cancelBooking(booking : Pinjam){
        val currentList = currentBooking.value?.toMutableList() ?: mutableListOf()
        currentList.remove(booking)
        currentBooking.value = currentList.toList()
    }

    override fun onCleared() {
        super.onCleared()
    }
}