package com.example.testcrm.presentation.profile.rootStudent

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testcrm.R
import com.example.testcrm.presentation.models.Student

class StudentAdapter(private val application: Application) :
    RecyclerView.Adapter<StudentAdapter.StudentHolder>() {

    private var listOfStudent = emptyList<Student>()

    class StudentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val fullName = itemView.findViewById<TextView>(R.id.tv_full_name)
        val phoneNumber = itemView.findViewById<TextView>(R.id.tv_phone_number)
        val studentTelegram = itemView.findViewById<TextView>(R.id.tv_student_telegram)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)
        return StudentHolder(view)
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        val student = listOfStudent[position]
        holder.apply {
            fullName.text = student.fullName
            phoneNumber.text = student.phoneNumber
            studentTelegram.text = student.telegram
            itemView.setOnClickListener {
                Log.d("STUDENT_ADAPTER", "Telegram link is ${studentTelegram.text}")
                val telegramIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://t.me/${studentTelegram.text.drop(1).trim()}")
                )
                application.startActivity(telegramIntent)
            }
        }
    }

    override fun getItemCount(): Int {
        return listOfStudent.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<Student>) {
        listOfStudent = list
        notifyDataSetChanged()
    }
}