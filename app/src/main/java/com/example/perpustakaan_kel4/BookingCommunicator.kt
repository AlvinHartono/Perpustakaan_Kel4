package com.example.perpustakaan_kel4

interface BookingCommunicator {

    fun editTransactionFragment(currentTransaction: Pinjam)

    fun cancelBooking(booking: Pinjam)

    fun confirmedBooking(booking: Pinjam)
}