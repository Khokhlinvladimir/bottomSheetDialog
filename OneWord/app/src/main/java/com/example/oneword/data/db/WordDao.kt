package com.example.oneword.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.oneword.data.model.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    // Поток всегда хранит/кэширует последнюю версию данных. Уведомляет своих наблюдателей, когда
    // данные изменились.

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()

    @Query("DELETE FROM word_table WHERE word = :str")
    suspend fun deleteByWord(str: String)
}