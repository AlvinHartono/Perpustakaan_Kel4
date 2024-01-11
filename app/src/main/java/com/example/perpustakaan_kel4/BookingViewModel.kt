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

    fun updateOrDeleteBooking(updatedMember: Pinjam? = null){
        val currentList = currentBooking.value?.toMutableList() ?: mutableListOf()

        //Find the index of the member with the specified ID
        val indexOfBook = currentList.indexOfFirst { it.id_member.toInt() == updatedMember!!.id_member.toInt() }

        if(indexOfBook != -1){
            if (updatedMember != null){
                currentList[indexOfBook] = updatedMember
            } else  {
                currentList.removeAt(indexOfBook)
            }
            currentBooking.value = currentList.toList()
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}