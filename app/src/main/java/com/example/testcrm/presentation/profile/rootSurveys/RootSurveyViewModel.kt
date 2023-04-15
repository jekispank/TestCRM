package com.example.testcrm.presentation.profile.rootSurveys

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testcrm.presentation.profile.Survey

class RootSurveyViewModel : ViewModel() {
    private val survey = Survey()

    private var _mapOfSurvey = MutableLiveData<MutableMap<String, String>>()
    val mapOfSurvey: LiveData<MutableMap<String, String>> = _mapOfSurvey

    init {
        _mapOfSurvey.value = survey.getListOfSurvey()
    }

    fun addSurvey(name: String, link: String) {
        val currentMap = _mapOfSurvey.value
        val newMap = currentMap?.plus((name to link))
        _mapOfSurvey.value = newMap?.toMutableMap()
    }

}