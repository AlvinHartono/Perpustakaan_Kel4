package com.example.perpustakaan_kel4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.appbar.MaterialToolbar

class Update_Member_Account : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_member_account)

        val topAppBar: MaterialToolbar = findViewById(R.id.topAppBar)

        topAppBar.setNavigationOnClickListener{
            finish()
        }
    }
}