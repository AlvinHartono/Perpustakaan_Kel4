package com.example.perpustakaan_kel4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MemberViewModel : ViewModel() {


    val currentMember: MutableLiveData<Member> by lazy {
        MutableLiveData<Member>()
    }

    //MutableLiveData hold the member data
//    private val _memberLiveData = MutableLiveData<Member>()

    //LiveData
//    val memberLiveData: LiveData<Member> = _memberLiveData

    fun updateMemberData(updatedMember: Member){
        currentMember.value = updatedMember
    }

    override fun onCleared() {
        //clean up resources
        super.onCleared()
    }
}