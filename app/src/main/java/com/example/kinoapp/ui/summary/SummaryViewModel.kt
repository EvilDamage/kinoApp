package com.example.kinoapp.ui.summary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SummaryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
//        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text
}