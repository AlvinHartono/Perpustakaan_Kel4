package com.example.perpustakaan_kel4

class ApiEndPoint {

    companion object {
        var ipAddress = "192.168.110.59"
        private val SERVER = "http://$ipAddress/Perpustakaan_Kel4/"
        val CREATE_MEMBER = SERVER+"member_register.php"
        val AUTH_MEMBER = SERVER+"member_authentication.php"
        val READ_MEMBER = SERVER+"member_read.php"
        val READ_LIBRARIAN = SERVER+"librarian_authentication.php"
        val CREATE_LIBRARIAN = SERVER+"librarian_register.php"
        val DELETE_MEMBER = SERVER+"member_delete.php"
        val UPDATE_MEMBER = SERVER+"member_update.php"
    }
}