package com.example.perpustakaan_kel4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class activity_login_librarian : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_login_librarian)

        var phonenumber : TextView = findViewById(R.id.editTextPhone)
        var password : TextView = findViewById(R.id.editTextPassword)
        var loginButton : Button = findViewById(R.id.buttonLogin)
        var registerTextView : TextView = findViewById(R.id.textViewRegister)

        loginButton.setOnClickListener {
            login(phonenumber, password)
        }

        registerTextView.setOnClickListener {
            val intent = Intent(this, RegisterLibrarian::class.java)
            startActivity(intent)
        }
    }

    private fun login(phoneNumber: TextView, password: TextView){
        val url : String = ApiEndPoint.READ_LIBRARIAN
        val stringRequest = object : StringRequest(
            com.android.volley.Request.Method.POST, url,
            Response.Listener { response ->
                Log.d("response", response)

                if(response.equals("true")){
                    val intent = Intent(this@activity_login_librarian, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this, "false", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { response ->
                Toast.makeText(this, "$response", Toast.LENGTH_SHORT).show()
            }
        ){
            override fun getParams() : HashMap<String, String>{
                val params = HashMap<String, String>()
                params["no_telp"] = phoneNumber.text.toString()
                params["password"] = password.text.toString()
                return params
            }
        }
        Volley.newRequestQueue(this).add(stringRequest)
    }

}