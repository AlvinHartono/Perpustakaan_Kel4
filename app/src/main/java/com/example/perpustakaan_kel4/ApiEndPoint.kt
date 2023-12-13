package com.example.perpustakaan_kel4

class ApiEndPoint {

    companion object {

        private val SERVER = "http://192.168.10.11/Perpustakaan_Kel4/"
        val CREATE = SERVER+"member_register.php"
        val READ = SERVER+"member_authentication.php"
        val DELETE = SERVER+"member_delete.php"
        val UPDATE = SERVER+"member_update.php"

    }
}