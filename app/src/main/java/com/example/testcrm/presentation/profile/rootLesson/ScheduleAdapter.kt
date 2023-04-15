package com.example.testcrm.presentation.profile.rootLesson

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.testcrm.R
import com.example.testcrm.presentation.models.Lesson

class ScheduleAdapter : RecyclerView.Adapter<ScheduleAdapter.ScheduleHolder>() {

    private var lessonList = emptyList<Lesson>()

    class ScheduleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val subject: TextView = itemView.findViewById(R.id.tv_subject)
        val studentName: TextView = itemView.findViewById(R.id.tv_student)
        val level: TextView = itemView.findViewById(R.id.tv_level)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lesson_item, parent, false)
        return ScheduleHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduleHolder, position: Int) {
        val lesson = lessonList[position]
        val lessonId = lesson.id
        val lessonDate = lesson.lessonDate
        holder.apply {
            subject.text = lesson.title
            studentName.text = lesson.student
            level.text = lesson.level
            itemView.setOnClickListener {
                val action =
                    ScheduleFragmentDirections.actionScheduleFragmentToAddLessonFragment(
                        lessonDate,
                        lessonId
                    )
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return lessonList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Lesson>) {
        lessonList = list
        notifyDataSetChanged()
    }


}