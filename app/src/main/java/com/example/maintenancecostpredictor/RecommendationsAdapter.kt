package com.example.maintenancecostpredictor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecommendationsAdapter(private val recs: List<String>) :
    RecyclerView.Adapter<RecommendationsAdapter.RecViewHolder>() {

    class RecViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text = itemView.findViewById<TextView>(R.id.textRecommendation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recommendation, parent, false)
        return RecViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecViewHolder, position: Int) {
        holder.text.text = recs[position]
    }

    override fun getItemCount() = recs.size
}

