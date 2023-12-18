package com.example.perpustakaan_kel4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class RegisterScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_register_screen)

        var editTextPhone: EditText = findViewById(R.id.editTextPhone)
        var editTextEmail: EditText = findViewById(R.id.editTextEmail)
        var editFirstName: EditText = findViewById(R.id.editFirstName)
        var editLastName: EditText = findViewById(R.id.editLastName)
        var editTextPassword: EditText = findViewById(R.id.editTextPassword)
        var editTextRePassword: EditText = findViewById(R.id.editTextRePassword)
        var signUp: Button = findViewById(R.id.buttonSignUp)
        var LoginTextView: TextView = findViewById(R.id.textViewLogin)

        LoginTextView.setOnClickListener {
            val intent = Intent(this, LoginScreen::class.java)
            startActivity(intent)
        }

        signUp.setOnClickListener {

            if (editTextPassword.text.toString().equals(editTextRePassword.text.toString())) {
                if (editTextPassword.text.toString().length >= 8) {
                    register(
                        editTextPhone,
                        editTextEmail,
                        editFirstName,
                        editLastName,
                        editTextPassword,
                        editTextRePassword
                    )
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(this, LoginScreen::class.java)
                        startActivity(intent)
                        finish()
                    }, 1000)
                } else {
                    Toast.makeText(this, "Password must be 8 characters or more", Toast.LENGTH_SHORT).show()
                    editTextPassword.setText("")
                    editTextRePassword.setText("")
                }


            } else {
                Toast.makeText(this, "Password don't match", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun register(
        editTextPhone: EditText,
        editTextEmail: EditText,
        editFirstName: EditText,
        editLastName: EditText,
        editTextPassword: EditText,
        editTextRePassword: EditText
    ) {
        val url: String = ApiEndPoint.CREATE_MEMBER
        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener { response ->
                Log.d("response", response)
                Toast.makeText(this, "Register Successful", Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener { response ->
                Toast.makeText(this, "Register Failed $response", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): HashMap<String, String> {
                val params = HashMap<String, String>()
                params["first_name_member"] = editFirstName.text.toString()
                params["last_name_member"] = editLastName.text.toString()
                params["email"] = editTextEmail.text.toString()
                params["no_telp"] = editTextPhone.text.toString()
                params["password"] = editTextPassword.text.toString()
                return params
            }
        }
        Volley.newRequestQueue(this).add(stringRequest)
    }

}