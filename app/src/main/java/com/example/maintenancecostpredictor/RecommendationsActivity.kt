package com.example.maintenancecostpredictor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecommendationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommendations)

        val recyclerView = findViewById<RecyclerView>(R.id.recommendationsRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 🔥 TODO: Replace this with actual API call to your Flask backend
        val recommendations = listOf(
            "Pipes are quite old. Plan inspection.",
            "High water usage. Check for leaks.",
            "Low rainfall + high usage. Consider rainwater harvesting.",
            "Wiring is old. Plan a rewiring audit.",
            "Many appliances in use. Stagger heavy loads."
        )

        recyclerView.adapter = RecommendationsAdapter(recommendations)
    }
}
