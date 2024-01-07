package com.example.perpustakaan_kel4

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BooksViewModel: ViewModel() {
    val currentBookList: MutableLiveData<List<Book>> by lazy {
        MutableLiveData<List<Book>>()
    }

    fun insertBookList(newBook: Book){
        val currentList = currentBookList.value?.toMutableList() ?: mutableListOf()
        currentList.add(newBook)
        currentBookList.value = currentList.toList()
    }

    fun updateOrDeleteBook(bookId: Int, updatedBook: Book? = null){
        val currentList = currentBookList.value?.toMutableList() ?: mutableListOf()

        //Find the index of the book with the specified ID
        val indexOfBook = currentList.indexOfFirst { it.id_buku == bookId }

        if (indexOfBook != -1) {
            // If the book is found, update or delete it
            if (updatedBook != null) {
                // Update the book if an updated book is provided
                currentList[indexOfBook] = updatedBook
            } else {
                // Delete the book if no updated book is provided
                currentList.removeAt(indexOfBook)
            }
            currentBookList.value = currentList.toList()
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}