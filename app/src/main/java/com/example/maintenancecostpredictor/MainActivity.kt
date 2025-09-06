


package com.example.maintenancecostpredictor

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import android.content.Intent


class MainActivity : AppCompatActivity() {

    private lateinit var pipeAge: EditText
    private lateinit var waterUsage: EditText
    private lateinit var rainfall: EditText
    private lateinit var appliances: EditText
    private lateinit var dailyUsage: EditText
    private lateinit var wiringAge: EditText
    private lateinit var gasAppliances: EditText
    private lateinit var pipeCondition: EditText
    private lateinit var devices: EditText
    private lateinit var householdSize: EditText
    private lateinit var recyclingScore: EditText
    private lateinit var speedPlanSpinner: Spinner
    private lateinit var resultText: TextView
    private lateinit var predictBtn: Button


    val url = "http://10.0.2.2:5050/predict"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pipeAge = findViewById(R.id.pipeAge)
        waterUsage = findViewById(R.id.waterUsage)
        rainfall = findViewById(R.id.rainfall)
        appliances = findViewById(R.id.appliances)
        dailyUsage = findViewById(R.id.dailyUsage)
        wiringAge = findViewById(R.id.wiringAge)
        gasAppliances = findViewById(R.id.gasAppliances)
        pipeCondition = findViewById(R.id.pipeCondition)
        devices = findViewById(R.id.devices)
        householdSize = findViewById(R.id.householdSize)
        recyclingScore = findViewById(R.id.recyclingScore)
        speedPlanSpinner = findViewById(R.id.speedPlanSpinner)
        resultText = findViewById(R.id.resultText)
        predictBtn = findViewById(R.id.predictBtn)
        val btnProfile = findViewById<Button>(R.id.btnProfile)
        btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        // Ensure this list matches the categories in your dataset and encoder
        val plans = arrayOf("Basic", "Standard", "Premium", "Ultra")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, plans)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        speedPlanSpinner.adapter = adapter

        predictBtn.setOnClickListener {
            sendPredictionRequest()
        }
    }

    private fun sendPredictionRequest() {
        try {
            // JSON body with keys that match Flask FEATURES exactly
            val jsonBody = JSONObject().apply {
                put("pipeAge", pipeAge.text.toString().toDouble())
                put("waterUsage", waterUsage.text.toString().toDouble())
                put("rainfall", rainfall.text.toString().toDouble())
                put("applianceCount", appliances.text.toString().toDouble())
                put("dailyUsage", dailyUsage.text.toString().toDouble())
                put("wiringAge", wiringAge.text.toString().toDouble())
                put("gasAppliances", gasAppliances.text.toString().toDouble())
                put("pipeCondition", pipeCondition.text.toString().toDouble())
                put("devices", devices.text.toString().toDouble())
                put("householdSize", householdSize.text.toString().toDouble())
                put("recyclingScore", recyclingScore.text.toString().toDouble())
                put("plan", speedPlanSpinner.selectedItem.toString()) // "Basic", "Standard", etc.
            }

            // Volley request
            val request = JsonObjectRequest(
                Request.Method.POST, url, jsonBody,
                { response ->
                    // Log full response
                    resultText.text = "Response: $response"

                    val cost = response.optDouble("prediction", -1.0)
                    if (cost != -1.0) {
                        resultText.text = "Predicted Cost: ₹%.2f".format(cost)
                    } else {
                        resultText.text = "Error: Invalid response from server. Response: $response"
                    }
                },
                { error ->
                    // Log full error details
                    error.printStackTrace()
                    resultText.text = "Volley Error: ${error.localizedMessage ?: "unknown"}"
                }
            )

            Volley.newRequestQueue(this).add(request)

        } catch (e: Exception) {
            resultText.text = "⚠ Invalid input. Please fill all fields with numbers."
        }
    }
}
