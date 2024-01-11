package com.example.perpustakaan_kel4

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MemberViewModel : ViewModel() {

    //SINGLE MEMBER
    val currentMember: MutableLiveData<Member> by lazy {
        MutableLiveData<Member>()
    }

    fun updateMemberData(updatedMember: Member){
        currentMember.value = updatedMember
    }

    //LIST OF MEMBERS
    val currentMemberList : MutableLiveData<List<Member>> by lazy {
        MutableLiveData<List<Member>>()
    }


    fun insertBookList(newMember : Member){
        val currentList = currentMemberList.value?.toMutableList() ?: mutableListOf()
        currentList.add(newMember)
        currentMemberList.value = currentList.toList()
    }

    fun updateCurrentMemberList(member : Member){
        val currentList = currentMemberList.value?.toMutableList() ?: mutableListOf()

        val index = currentList.indexOfFirst { it.id_member == member.id_member }
        if (index != -1) {
            // Replace the old member with the updated member
            currentList[index] = member
            currentMemberList.value = currentList
        }
    }

    fun deleteMember(member: Member){
        val currentList = currentMemberList.value?.toMutableList() ?: mutableListOf()
        currentList.remove(member)
        currentMemberList.value = currentList
    }

    override fun onCleared() {
        //clean up resources
        super.onCleared()
    }
}