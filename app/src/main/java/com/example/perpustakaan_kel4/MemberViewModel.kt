package com.example.perpustakaan_kel4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MemberViewModel : ViewModel() {

    val currentMember: MutableLiveData<Member> by lazy {
        MutableLiveData<Member>()
    }

    fun updateMemberData(updatedMember: Member){
        currentMember.value = updatedMember
    }

    override fun onCleared() {
        //clean up resources
        super.onCleared()
    }
}