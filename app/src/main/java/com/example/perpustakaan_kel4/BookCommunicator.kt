package com.example.perpustakaan_kel4

interface BookCommunicator {
    fun booksToAddBooksFragment()

    fun editBookFragment(currentBook: Book)
}