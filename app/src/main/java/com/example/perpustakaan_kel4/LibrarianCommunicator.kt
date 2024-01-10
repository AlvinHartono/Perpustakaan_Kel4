package com.example.perpustakaan_kel4

interface LibrarianCommunicator {
    fun editLibrarianFragment()

    fun editMemberFragment(currentMember: Member)

    fun deleteMember(memberId: Int)
}