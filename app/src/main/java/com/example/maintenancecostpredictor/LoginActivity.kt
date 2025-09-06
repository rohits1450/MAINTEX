package com.example.maintenancecostpredictor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginBtn: Button
    private lateinit var registerLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.loginEmail)
        password = findViewById(R.id.loginPassword)
        loginBtn = findViewById(R.id.loginBtn)
        registerLink = findViewById(R.id.registerLink)

        loginBtn.setOnClickListener {
            val emailText = email.text.toString().trim()
            val passText = password.text.toString().trim()

            if (emailText == "test@test.com" && passText == "1234") {
                // ✅ Successful login -> Go to Dashboard
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finish() // Prevent going back to login
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }

        registerLink.setOnClickListener {
            // ✅ Navigate to Register screen
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}