package com.example.perpustakaan_kel4

class ApiEndPoint {

    companion object {
        var ipAddress = "192.168.0.5"

        private val SERVER = "http://$ipAddress/Perpustakaan_Kel4/"

        //MEMBER'S CRUD
        val AUTH_MEMBER = SERVER+"member_authentication.php"
        val CREATE_MEMBER = SERVER+"member_register.php"
        val READ_MEMBER = SERVER+"member_read.php"
        val UPDATE_MEMBER = SERVER+"member_update.php"
        val DELETE_MEMBER = SERVER+"member_delete.php"

        //LIBRARIAN'S CRUD
        val AUTH_LIBRARIAN = SERVER+"librarian_authentication.php"
        val CREATE_LIBRARIAN = SERVER+"librarian_register.php"
        val READ_LIBRARIAN = SERVER+"librarian_read.php"
        val UPDATE_LIBRARIAN = SERVER+"librarian_update.php"
        val DELETE_LIBRARIAN = SERVER+"librarian_delete.php"

        val READ_MEMBER_LIBRARIAN = SERVER+"member_read_all.php"

        //BOOK'S CRUD
        val READ_BOOKS = SERVER+"buku_read.php"
        val DELETE_BOOK = SERVER+"buku_delete.php"
        //TODO: ADD BOOKS CRUD

        //PINJAM'S CRUD
        val READ_PINJAM = SERVER+"pinjam_read.php"
        val READ_PINJAM_ALL = SERVER+"pinjam_read_all.php"
        val DELETE_PINJAM = SERVER+"pinjam_delete.php"
        val ADD_PINJAM = SERVER+"pinjam_add.php"
        val UPDATE_PINJAM = SERVER+"pinjam_confirm.php"
    }
}