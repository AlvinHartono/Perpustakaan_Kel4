package com.example.perpustakaan_kel4

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookingViewModel : ViewModel() {

    val currentBooking : MutableLiveData<List<Pinjam>> by lazy {
        MutableLiveData<List<Pinjam>>()

    }

    fun insertBookingList(newBookings: Pinjam){
        val currentList = currentBooking.value?.toMutableList() ?: mutableListOf()
        currentList.add(newBookings)
        currentBooking.value = currentList.toList()
    }

    override fun onCleared() {
        super.onCleared()
    }
}