package com.example.perpustakaan_kel4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class WelcomeScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)
    val buttonClick = findViewById<Button>(R.id.getStarted)
        buttonClick.setOnClickListener{
            val intent = Intent(this,LoginScreen::class.java)
            startActivity(intent)
            finish()
        }
    }

}
