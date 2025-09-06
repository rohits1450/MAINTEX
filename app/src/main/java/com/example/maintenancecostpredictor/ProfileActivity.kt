package com.example.maintenancecostpredictor

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val nameInput = findViewById<EditText>(R.id.editName)
        val emailInput = findViewById<EditText>(R.id.editEmail)
        val saveBtn = findViewById<Button>(R.id.btnSaveProfile)

        saveBtn.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()

            if (name.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "✅ Profile Saved: $name, $email", Toast.LENGTH_LONG).show()
            }
        }
    }
}