package com.example.perpustakaan_kel4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        var phoneNumber : EditText = findViewById<EditText>(R.id.editTextPhone)
        var password : EditText = findViewById<EditText>(R.id.editTextPassword)
        var loginButton : Button = findViewById<Button>(R.id.buttonLogin)
        var registerTextView : TextView = findViewById(R.id.textViewRegister)

        loginButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        registerTextView.setOnClickListener {
            val intent = Intent(this, RegisterScreen::class.java)
            startActivity(intent)
        }



    }
}