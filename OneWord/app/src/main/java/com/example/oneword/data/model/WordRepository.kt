package com.example.oneword.data.model

import androidx.annotation.WorkerThread
import com.example.oneword.data.db.WordDao
import com.example.oneword.data.model.Word
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {


    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    @WorkerThread
    suspend fun delete() {
        wordDao.deleteAll()
    }

    @WorkerThread
    suspend fun deleteBy(str: String) {
        wordDao.deleteByWord(str)
    }


}
