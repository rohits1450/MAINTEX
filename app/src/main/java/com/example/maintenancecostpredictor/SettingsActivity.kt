package com.example.maintenancecostpredictor

import android.os.Bundle
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val notifSwitch = findViewById<Switch>(R.id.switchNotifications)
        val darkModeSwitch = findViewById<Switch>(R.id.switchDarkMode)

        notifSwitch.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, if (isChecked) "Notifications ON" else "Notifications OFF", Toast.LENGTH_SHORT).show()
        }

        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, if (isChecked) "Dark Mode Enabled" else "Dark Mode Disabled", Toast.LENGTH_SHORT).show()
        }
    }
}