package com.example.maintenancecostpredictor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import android.graphics.Color
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val lineChart = findViewById<LineChart>(R.id.lineChart)
        val barChart = findViewById<BarChart>(R.id.barChart)
        val recommendationsRecycler = findViewById<RecyclerView>(R.id.recommendationsRecycler)

        // ✅ Line chart data (total costs timeline)
        val lineEntries = listOf(
            Entry(1f, 200f), // Jan
            Entry(2f, 250f), // Feb
            Entry(3f, 300f), // Mar
            Entry(4f, 220f)  // Apr
        )
        val lineDataSet = LineDataSet(lineEntries, "Total Utility Cost").apply {
            color = Color.parseColor("#FF6F00")
            valueTextColor = Color.WHITE
            lineWidth = 2f
            circleRadius = 5f
            setCircleColor(Color.parseColor("#FF6F00"))
        }
        lineChart.data = LineData(lineDataSet)
        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        lineChart.xAxis.valueFormatter = IndexAxisValueFormatter(listOf("Jan", "Feb", "Mar", "Apr"))
        lineChart.xAxis.textColor = Color.WHITE
        lineChart.axisLeft.textColor = Color.WHITE
        lineChart.axisRight.isEnabled = false
        lineChart.legend.textColor = Color.WHITE
        lineChart.description.isEnabled = false
        lineChart.animateX(1000)

        // ✅ Stacked Bar Chart data (electricity, water, gas)
        val barEntries = listOf(
            BarEntry(1f, floatArrayOf(120f, 50f, 30f)), // Jan
            BarEntry(2f, floatArrayOf(140f, 70f, 40f)), // Feb
            BarEntry(3f, floatArrayOf(180f, 80f, 40f)), // Mar
            BarEntry(4f, floatArrayOf(150f, 50f, 20f))  // Apr
        )
        val barDataSet = BarDataSet(barEntries, "Utilities").apply {
            setColors(
                Color.parseColor("#FF6F00"), // Electricity
                Color.parseColor("#29B6F6"), // Water
                Color.parseColor("#66BB6A")  // Gas
            )
            stackLabels = arrayOf("Electricity", "Water", "Gas")
            valueTextColor = Color.WHITE
            valueTextSize = 12f
        }
        barChart.data = BarData(barDataSet)
        barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(listOf("Jan", "Feb", "Mar", "Apr"))
        barChart.xAxis.textColor = Color.WHITE
        barChart.axisLeft.textColor = Color.WHITE
        barChart.axisRight.isEnabled = false
        barChart.legend.textColor = Color.WHITE
        barChart.description.isEnabled = false
        barChart.animateY(1000)



        // ✅ Button navigation
        findViewById<Button>(R.id.btnPrediction).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        findViewById<Button>(R.id.btnSettings).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        findViewById<Button>(R.id.btnProfile).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        findViewById<Button>(R.id.btnRecommendations).setOnClickListener {
            startActivity(Intent(this, RecommendationsActivity::class.java))
        }


    }
}
