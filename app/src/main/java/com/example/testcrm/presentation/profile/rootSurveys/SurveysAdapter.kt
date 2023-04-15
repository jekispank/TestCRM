package com.example.testcrm.presentation.profile.rootSurveys

import android.annotation.SuppressLint
import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.example.testcrm.R

class SurveysAdapter(private val application: Application) :
    RecyclerView.Adapter<SurveysAdapter.SurveysHolder>() {

    val listOfSurveys: MutableList<String> = emptyList<String>().toMutableList()
    val listOfLink: MutableList<String> = emptyList<String>().toMutableList()

    class SurveysHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val surveyname = itemView.findViewById<TextView>(R.id.tv_name_of_survey)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurveysHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.survey_item, parent, false)
        return SurveysHolder(view)
    }

    override fun onBindViewHolder(holder: SurveysHolder, position: Int) {
        val itemText = listOfSurveys[position]
        val itemLink = listOfLink[position]
        holder.apply {
            surveyname.text = itemText
            itemView.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(itemLink))
                application.startActivity(browserIntent)
            }
            itemView.setOnLongClickListener {
                val clipboard = application.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("Link", itemLink)
                clipboard.setPrimaryClip(clip)

                Toast.makeText(
                    application,
                    "Survey's link is copied!",
                    Toast.LENGTH_SHORT
                ).show()

                true
            }
        }
    }

    override fun getItemCount(): Int {
        return listOfSurveys.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(mapOfSurveys: MutableMap<String, String>) {
        mapOfSurveys.forEach { s, s2 ->
            listOfSurveys.add(s)
        }
        mapOfSurveys.forEach { s, s2 ->
            listOfLink.add(s2)
        }
        notifyDataSetChanged()
    }
}