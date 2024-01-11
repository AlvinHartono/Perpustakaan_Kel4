package com.example.perpustakaan_kel4

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class TermsAndPrivacyPage : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_and_privacy_page)

        val back : ImageView = findViewById(R.id.buttonBack1)

        back.setOnClickListener {
            finish()
        }

    }
}