package com.example.perpustakaan_kel4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


class LoginScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_login_screen)

        var phoneNumber: EditText = findViewById(R.id.editTextPhone)
        var password: EditText = findViewById(R.id.editTextPassword)
        var loginButton: Button = findViewById(R.id.buttonLogin)
        var registerTextView: TextView = findViewById(R.id.textViewRegister)
        var librarianAccess : ImageView = findViewById(R.id.imageView3)


        loginButton.setOnClickListener {
            login(phoneNumber, password)
        }

        registerTextView.setOnClickListener {
            val intent = Intent(this, RegisterScreen::class.java)
            startActivity(intent)
        }

        librarianAccess.setOnClickListener {
            val intent = Intent(this, activity_login_librarian::class.java)
            startActivity(intent)
        }

    }
    private fun login(phoneNumber: EditText, password:EditText){
        val url: String = ApiEndPoint.AUTH_MEMBER
        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener { response ->
                Log.d("responser", response)

                if (response.equals("true")) {
                    val bundle = Bundle()
                    bundle.putString("no_telp", phoneNumber.text.toString())
                    val intent = Intent(this@LoginScreen, MainActivity::class.java)
                    intent.putExtras(bundle)
                    startActivity(intent)
                    finish()
                } else{
                    Toast.makeText(this,"false",Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { response ->
                Toast.makeText(this, "$response", Toast.LENGTH_SHORT).show()
                Log.d("responser", response.toString())
            }
        ){
            override fun getParams(): HashMap<String,String>{
                val params = HashMap<String,String>()
                params["no_telp"]      = phoneNumber.text.toString()
                params["password"]     = password.text.toString()
                return params
            }
        }
        Volley.newRequestQueue(this).add(stringRequest)

    }
}

