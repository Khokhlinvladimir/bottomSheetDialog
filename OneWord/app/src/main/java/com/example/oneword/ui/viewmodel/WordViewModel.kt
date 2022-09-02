package com.example.oneword.ui.viewmodel

import androidx.lifecycle.*
import com.example.oneword.data.model.WordRepository
import com.example.oneword.data.model.Word
import kotlinx.coroutines.launch



class WordViewModel(private val repository: WordRepository) : ViewModel() {


    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()


    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }


     fun delete () = viewModelScope.launch {
         repository.delete()
     }

    fun deleteBy (str: String) = viewModelScope.launch {
        repository.deleteBy(str)
    }







}


