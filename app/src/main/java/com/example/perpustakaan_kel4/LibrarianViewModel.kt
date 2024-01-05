package com.example.perpustakaan_kel4

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LibrarianViewModel : ViewModel() {

    val currentLibrarian: MutableLiveData<Librarian> by lazy {
        MutableLiveData<Librarian>()
    }

    fun updateLibrarianData(updatedLibrarian: Librarian){
        currentLibrarian.value = updatedLibrarian
    }

    override fun onCleared() {
        //clean up resources
        super.onCleared()
    }


}