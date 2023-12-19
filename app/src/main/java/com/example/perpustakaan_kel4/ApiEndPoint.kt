package com.example.perpustakaan_kel4

class ApiEndPoint {

    companion object {
        private val SERVER = "http://192.168.110.59/Perpustakaan_Kel4/"
        val CREATE_MEMBER = SERVER+"member_register.php"
        val READ_MEMBER = SERVER+"member_authentication.php"
        val DELETE_MEMBER = SERVER+"member_delete.php"
        val UPDATE_MEMBER = SERVER+"member_update.php"
    }
}