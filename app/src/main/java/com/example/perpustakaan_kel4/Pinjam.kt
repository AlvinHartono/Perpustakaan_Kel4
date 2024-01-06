package com.example.perpustakaan_kel4

import java.io.Serializable
import java.sql.Time
import java.time.Year
import java.util.Date

class Pinjam : Serializable {
    fun statusToText(): CharSequence? {
        if (!status){
            return "Belum Dikembalikan"
        }else{
            return "Sudah Dikembalikan"
        }
    }

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

    var tgl_peminjaman: Date = Date()
        get() = field
        set(value){
            field = value
        }

    var tgl_pengembalian: Date = Date()
        get() = field
        set(value){
            field = value
        }

    var batas_tgl_pengembalian: Date = Date()
        get() = field
        set(value){
            field = value
        }

    var status : Boolean = false
        get() = field
        set(value) {
            field = value
        }

    constructor(
        idBuku: String = "",
        idMember : String = "",
        tglPinjam: Date = Date(),
        tglKembali: Date = Date(),
        batasTglKembali: Date = Date(),
        status: Boolean = false
    ){
        this.id_buku = idBuku
        this.id_member = idMember
        this.tgl_peminjaman = tglPinjam
        this.tgl_pengembalian = tglKembali
        this.batas_tgl_pengembalian = batasTglKembali
        this.status = status
    }

    constructor()
}