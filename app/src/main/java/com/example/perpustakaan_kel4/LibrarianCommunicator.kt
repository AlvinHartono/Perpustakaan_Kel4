package com.example.perpustakaan_kel4

interface LibrarianCommunicator {
    fun editLibrarianFragment()

    fun editMemberFragment(member: Member)

    fun deleteMember(member: Member)
}