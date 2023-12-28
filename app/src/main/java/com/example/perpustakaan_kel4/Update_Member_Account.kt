package com.example.perpustakaan_kel4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.appbar.MaterialToolbar

class Update_Member_Account : AppCompatActivity() {

    private lateinit var memberViewModel: MemberViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_member_account)
        var editTextEditFirstName: EditText = findViewById(R.id.editTextEditFirstname)
        var editTextEditLastName: EditText = findViewById(R.id.editTextEditLastName)
        var editTextEditEmail: EditText = findViewById(R.id.editTextEditEmail)
        var editTextPhoneNumber: EditText = findViewById(R.id.editTextPhoneNumber)
        var editTextEditPassword: EditText = findViewById(R.id.editTextEditPassword)
        var editTextEditRePassword: EditText = findViewById(R.id.editTextEditRePassword)


        val topAppBar: MaterialToolbar = findViewById(R.id.topAppBar)

        topAppBar.setNavigationOnClickListener{
            finish()
        }

        memberViewModel = ViewModelProvider(this)[MemberViewModel::class.java]

//        Log.d("response", memberViewModel.currentMember.value!!.id_member)

        memberViewModel.currentMember.observe(this, Observer {
            editTextEditFirstName.setText(it.first_name_member)
            editTextEditLastName.setText(it.last_name_member)
            editTextEditEmail.setText(it.email)
            editTextPhoneNumber.setText(it.no_telp)
            editTextEditPassword.setText(it.password)
            editTextEditRePassword.setText(it.password)
        })
    }
}