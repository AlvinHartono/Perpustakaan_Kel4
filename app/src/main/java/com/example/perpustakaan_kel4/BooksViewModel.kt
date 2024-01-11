package com.example.perpustakaan_kel4

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BooksViewModel : ViewModel() {
    val currentBookList: MutableLiveData<List<Book>> by lazy {
        MutableLiveData<List<Book>>()
    }

    fun insertBookList(newBook: Book) {
        val currentList = currentBookList.value?.toMutableList() ?: mutableListOf()
        currentList.add(newBook)
        currentBookList.value = currentList.toList()
    }

    fun updateBookData(book: Book){
        val currentList = currentBookList.value?.toMutableList() ?: mutableListOf()

        val index = currentList.indexOfFirst { it.id_buku == book.id_buku}
        if (index != -1) {
            // Replace the old member with the updated member
            currentList[index] = book
            currentBookList.value = currentList
        }
    }

    fun deleteBook(currentBook: Book) {
        try {
            val currentList = currentBookList.value?.toMutableList() ?: mutableListOf()
            currentList.remove(currentBook)
            currentBookList.value = currentList
        } catch (e: Exception) {
            Log.d("response", e.toString())
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}