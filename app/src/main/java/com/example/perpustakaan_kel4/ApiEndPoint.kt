package com.example.perpustakaan_kel4

class ApiEndPoint {

    companion object {
        private val SERVER = "http://10.234.211.54/Perpustakaan_Kel4/"
        val CREATE_MEMBER = SERVER+"member_register.php"
        val READ_MEMBER = SERVER+"member_authentication.php"
        val DELETE_MEMBER = SERVER+"member_delete.php"
        val UPDATE_MEMBER = SERVER+"member_update.php"
    }
}