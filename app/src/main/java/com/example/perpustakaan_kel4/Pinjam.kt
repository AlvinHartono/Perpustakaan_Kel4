package com.example.perpustakaan_kel4

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.Serializable
import java.sql.Time
import java.time.Year
import java.util.Date

class Pinjam {

    var id_buku: String = ""
        get() = field
        set(value){
            field = value
        }

    var id_member: String = ""
        get() = field
        set(value){
            field = value
        }

    var tgl_peminjaman: String = ""
        get() = field
        set(value){
            field = value
        }

    var tgl_pengembalian: String = ""
        get() = field
        set(value){
            field = value
        }

    var batas_tgl_pengembalian: String = ""
        get() = field
        set(value){
            field = value
        }

    var status : Boolean = false
        get() = field
        set(value) {
            field = value
        }

    var image_buku : ByteArray = byteArrayOf()
        get() = field
        set(value) {
            field = value
        }

    var judul_buku : String = ""
        get() = field
        set(value){
            field = value
        }

    var nama_member : String = ""
        get() = field
        set(value){
            field = value
        }

    fun statusToText(status: Boolean): String {
        return if (status) {
            "Sudah Dikembalikan"
        } else {
            "Belum Dikembalikan"
        }
    }

    constructor(
        idBuku: String = "",
        idMember : String = "",
        tglPinjam: String = "",
        tglKembali: String = "",
        batasTglKembali: String = "",
        status: Boolean = false,
        imgBuku: ByteArray = byteArrayOf(),
        judulBuku: String = "",
        namaMember: String = ""
    ){
        this.id_buku = idBuku
        this.id_member = idMember
        this.tgl_peminjaman = tglPinjam
        this.tgl_pengembalian = tglKembali
        this.batas_tgl_pengembalian = batasTglKembali
        this.status = status
        this.image_buku = imgBuku
        this.judul_buku = judulBuku
        this.nama_member = namaMember
    }

    constructor()

    fun decodeByteArrayToBitmap(byteArray: ByteArray) : Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}