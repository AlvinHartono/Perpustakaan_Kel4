package com.example.perpustakaan_kel4

import java.io.Serializable

class Librarian : Serializable {
    var id_librarian: String = ""
        get() = field
        set(value) {
            field = value
        }

    var first_name_librarian: String = ""
        get() = field
        set(value) {
            field = value
        }

    var last_name_librarian: String = ""
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

    constructor(
        idLibrarian: String = "",
        firstNameLibrarian: String = "",
        lastNameLibrarian: String ="",
        email: String = "",
        noTelp: String = "",
        password: String = ""
    ){
        this.id_librarian = idLibrarian
        this.first_name_librarian = firstNameLibrarian
        this.last_name_librarian = lastNameLibrarian
        this.email = email
        this.no_telp = noTelp
        this.password = password
    }

    constructor()
}