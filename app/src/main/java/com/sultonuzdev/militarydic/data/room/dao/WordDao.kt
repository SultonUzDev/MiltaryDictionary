package com.sultonuzdev.militarydic.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.sultonuzdev.militarydic.data.room.entity.Word

@Dao
interface WordDao {

    @Query("SELECT * FROM englishwords ORDER BY englishname")
    fun getAllWords(): LiveData<List<Word>>

    @Query("SELECT * FROM englishwords  WHERE englishname LIKE :query")
    fun searchEnglishDatabase(query: String): LiveData<List<Word>>


    @Query("select * from englishwords order by russianname")
    fun getRussianWords(): LiveData<List<Word>>

    @Query("SELECT * FROM englishwords  WHERE russianname LIKE :query")
    fun searchRussianDatabase(query: String): LiveData<List<Word>>

    @Query("select * from englishwords order by uzbname")
    fun getUzbekWords(): LiveData<List<Word>>

    @Query("SELECT * FROM englishwords  WHERE uzbname LIKE :query")
    fun searchUzbekDatabase(query: String): LiveData<List<Word>>

}