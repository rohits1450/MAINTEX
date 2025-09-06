package com.example.maintenancecostpredictor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var registerBtn: Button
    private lateinit var loginLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Bind UI elements
        name = findViewById(R.id.registerName)
        email = findViewById(R.id.registerEmail)
        password = findViewById(R.id.registerPassword)
        confirmPassword = findViewById(R.id.registerConfirmPassword)
        registerBtn = findViewById(R.id.registerBtn)
        loginLink = findViewById(R.id.loginLink)

        // Handle Register button
        registerBtn.setOnClickListener {
            val nameText = name.text.toString().trim()
            val emailText = email.text.toString().trim()
            val passText = password.text.toString().trim()
            val confirmPassText = confirmPassword.text.toString().trim()

            if (nameText.isEmpty() || emailText.isEmpty() || passText.isEmpty() || confirmPassText.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else if (passText != confirmPassText) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                // ✅ Successful registration (hardcoded / dummy flow)
                Toast.makeText(this, "Registered Successfully!", Toast.LENGTH_SHORT).show()

                // Go directly to Dashboard
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        // Handle Login link
        loginLink.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}