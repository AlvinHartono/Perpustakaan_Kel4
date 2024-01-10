package com.example.perpustakaan_kel4

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible

class NotificationPage : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_page)

        val notifSwitch : Switch = findViewById(R.id.NotifSwitch)

        val textNone : TextView = findViewById(R.id.textView16)
        val text1hr : TextView = findViewById(R.id.textView17)
        val text2hr : TextView = findViewById(R.id.textView18)

        val noneChecked : ImageView = findViewById(R.id.noneCheck)
        val onehrChecked : ImageView = findViewById(R.id.onehrCheck)
        val twohrChecked : ImageView = findViewById(R.id.twohrCheck)

        val NoneCol : View = findViewById(R.id.noneClick)
        val onehrCol : View = findViewById(R.id.onehrClick)
        val twohrCol : View = findViewById(R.id.twohrClick)

        val backbtn : ImageView = findViewById(R.id.backbutton)

        if(notifSwitch.isChecked){
            Toast.makeText(this, "Notification Enabled", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "Notification Disabled", Toast.LENGTH_LONG).show()

            textNone.setTextColor(Color.parseColor("#808080"))
            text1hr.setTextColor(Color.parseColor("#808080"))
            text2hr.setTextColor(Color.parseColor("#808080"))

            NoneCol.isClickable = false
            onehrCol.isClickable = false
            twohrCol.isClickable = false
        }

        NoneCol.setOnClickListener {
            noneChecked.isVisible = true
            onehrChecked.isVisible = false
            twohrChecked.isVisible = false
        }

        onehrCol.setOnClickListener {
            noneChecked.isVisible = false
            onehrChecked.isVisible = true
            twohrChecked.isVisible = false
        }

        twohrCol.setOnClickListener {
            noneChecked.isVisible = false
            onehrChecked.isVisible = false
            twohrChecked.isVisible = true
        }

        backbtn.setOnClickListener {
            //TODO: BACK
            finish()
        }
    }
}