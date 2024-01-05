package com.example.perpustakaan_kel4

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BukuViewMode : ViewModel() {

    val currentBookList : MutableLiveData<List<Buku>> by lazy {
        MutableLiveData<List<Buku>>()
    }

    fun updateMemberData(updatedBookList: List<Buku>){
        currentBookList.value = updatedBookList
    }
}