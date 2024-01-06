package com.example.perpustakaan_kel4

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookingViewModel : ViewModel() {

    val currentBooking : MutableLiveData<Pinjam> by lazy {
        MutableLiveData<Pinjam>()

    }

    fun updateBookingData(updatedBookings: Pinjam){
        currentBooking.value = updatedBookings
    }

    override fun onCleared() {
        super.onCleared()
    }
}