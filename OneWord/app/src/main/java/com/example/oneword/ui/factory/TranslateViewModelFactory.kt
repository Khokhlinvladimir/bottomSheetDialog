package com.example.oneword.ui.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.oneword.ui.viewmodel.TranslateViewModel

class TranslateViewModelFactory(private val application : Application, val text : String) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TranslateViewModel (application, text) as T
    }


}