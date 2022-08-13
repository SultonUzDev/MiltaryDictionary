package com.sultonuzdev.militarydic.domain

import androidx.lifecycle.LiveData
import com.sultonuzdev.militarydic.data.room.dao.WordDao
import com.sultonuzdev.militarydic.data.room.entity.Word

class WordRepository(private val wordDao: WordDao) {
    fun getEnglishWord(): LiveData<List<Word>> = wordDao.getAllWords()
    fun getUzbekWords(): LiveData<List<Word>> = wordDao.getUzbekWords()
    fun getRussianWords(): LiveData<List<Word>> = wordDao.getRussianWords()


    fun searchEnglishDatabase(word: String): LiveData<List<Word>> = wordDao.searchEnglishDatabase(word)
    fun searchUzbekDatabase(word: String): LiveData<List<Word>> = wordDao.searchUzbekDatabase(word)
    fun searchRussianDatabase(word: String): LiveData<List<Word>> = wordDao.searchRussianDatabase(word)
}