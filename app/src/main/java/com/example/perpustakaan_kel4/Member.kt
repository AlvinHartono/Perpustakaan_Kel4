package com.example.perpustakaan_kel4

import java.io.Serializable

class Member: Serializable {
    var id_member: String = ""
        get() = field
        set(value) {
            field = value
        }

    var first_name_member: String = ""
        get() = field
        set(value) {
            field = value
        }

    var last_name_member: String = ""
        get() = field
        set(value) {
            field = value
        }

    var email: String = ""
        get() = field
        set(value) {
            field = value
        }

    var no_telp: String = ""
        get() = field
        set(value) {
            field = value
        }

    var password: String = ""
        get() = field
        set(value) {
            field = value
        }


}