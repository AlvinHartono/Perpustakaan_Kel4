package com.example.perpustakaan_kel4

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookingViewModel : ViewModel() {

    val currentBooking : MutableLiveData<List<Pinjam>> by lazy {
        MutableLiveData<List<Pinjam>>()
    }

    fun insertBookingList(booking: Pinjam){
        try {
        val currentList = currentBooking.value?.toMutableList() ?: mutableListOf()
        currentList.add(booking)
        Log.d("response insert zz", currentList.toList().toString())
        currentBooking.value = currentList.toList()
//        Log.d("response insert", currentBooking.value!![1].judul_buku)
        } catch (e : Exception){
            Log.d("response", e.toString())
        }
    }

    fun cancelBooking(booking : Pinjam){
        try {
        val currentList = currentBooking.value?.toMutableList() ?: mutableListOf()
            currentList.remove(booking)
            currentBooking.value = currentList

        } catch (e : Exception){
            Log.d("response", e.toString())
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}