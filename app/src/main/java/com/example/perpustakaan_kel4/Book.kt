package com.example.perpustakaan_kel4

import android.graphics.Bitmap
import android.graphics.BitmapFactory

class Book {

    var id_buku: Int = 0
        get() = field
        set(value) {
            field = value
        }

    var judul_buku: String = ""
        get() = field
        set(value) {
            field = value
        }

    var penerbit: String = ""
        get() = field
        set(value) {
            field = value
        }

    var pengarang: String = ""
        get() = field
        set(value) {
            field = value
        }

    var tahun_terbit: String = ""
        get() = field
        set(value) {
            field = value
        }

    var nama_kategori: String = ""
        get() = field
        set(value) {
            field = value
        }

    var image_buku: ByteArray= byteArrayOf()
        get() = field
        set(value) {
            field = value
        }

    fun decodeByteArrayToBitmap(byteArray: ByteArray) : Bitmap{
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}