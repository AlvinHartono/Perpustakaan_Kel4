package com.example.perpustakaan_kel4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class LoginScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        var textStatus: TextView = findViewById(R.id.textViewStatus)
        var phoneNumber: EditText = findViewById(R.id.editTextPhone)
        var password: EditText = findViewById(R.id.editTextPassword)
        var loginButton: Button = findViewById(R.id.buttonLogin)
        var registerTextView: TextView = findViewById(R.id.textViewRegister)


        loginButton.setOnClickListener {

            val queue = Volley.newRequestQueue(this)
            val url: String = ApiEndPoint.READ_MEMBER
            val stringRequest = StringRequest(
                Request.Method.GET,
                url,
                { response ->
                    Log.d("response", response)
                    if (response == "true") {
                        val intent = Intent(this@LoginScreen, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this@LoginScreen,
                            "Invalid Phone Number or Password",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                { response ->
                    //error callback
                    textStatus.text = response.toString()
                })
            queue.add(stringRequest)
        }

        registerTextView.setOnClickListener {
            val intent = Intent(this, RegisterScreen::class.java)
            startActivity(intent)
        }
    }
}

//private fun kirimData() {
//
//    //instantiate the RequestQueue.
//
//    val url: String = ApiEndPoint.READ
//
//
//    //Request a string response from the provided URL
//    val stringRequest = StringRequest(
//        Request.Method.GET,
//        url,
//        Response.Listener<String> { response ->
//            Toast.makeText(this, "Nice", Toast.LENGTH_SHORT).show()
//        },
//        Response.ErrorListener { response ->
//            Toast.makeText(this, "Nice", Toast.LENGTH_SHORT).show()
//        })
//}