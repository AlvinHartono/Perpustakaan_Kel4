package com.example.perpustakaan_kel4

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MemberViewModel : ViewModel() {

    val currentMember: MutableLiveData<Member> by lazy {
        MutableLiveData<Member>()
    }


    fun updateMemberData(updatedMember: Member){
        currentMember.value = updatedMember
    }

    val currentMemberList : MutableLiveData<List<Member>> by lazy {
        MutableLiveData<List<Member>>()
    }

    fun insertBookList(newMember : Member){
        val currentList = currentMemberList.value?.toMutableList() ?: mutableListOf()
        currentList.add(newMember)
        currentMemberList.value = currentList.toList()
    }

    fun updateOrDeleteMember(memberId: Int, updatedMember: Member? = null){
        val currentList = currentMemberList.value?.toMutableList() ?: mutableListOf()

        //Find the index of the member with the specified ID
        val indexOfBook = currentList.indexOfFirst { it.id_member.toInt() == memberId }

        if(indexOfBook != -1){
            if (updatedMember != null){
                currentList[indexOfBook] = updatedMember
            } else  {
                currentList.removeAt(indexOfBook)
            }
            currentMemberList.value = currentList.toList()
        }
    }
    override fun onCleared() {
        //clean up resources
        super.onCleared()
    }
}