package com.sultonuzdev.militarydic.ui.words

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.sultonuzdev.militarydic.data.room.db.WordDatabase
import com.sultonuzdev.militarydic.data.room.entity.Word
import com.sultonuzdev.militarydic.domain.WordRepository

class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val wordRepository: WordRepository

    init {
        val wordDao = WordDatabase.getDatabaseInstance(application).wordDao
        wordRepository = WordRepository(wordDao)
    }

    fun getEnglishWords(): LiveData<List<Word>> {
        return wordRepository.getEnglishWord()
    }

    fun getUzbekWords(): LiveData<List<Word>> {
        return wordRepository.getUzbekWords()
    }

    fun getRussianWords(): LiveData<List<Word>> {
        return wordRepository.getRussianWords()
    }

    fun searchEnglish(query: String): LiveData<List<Word>> =
        wordRepository.searchEnglishDatabase(word = query)

    fun searchUzbek(query: String): LiveData<List<Word>> =
        wordRepository.searchUzbekDatabase(word = query)

    fun searchRussian(query: String): LiveData<List<Word>> =
        wordRepository.searchRussianDatabase(word = query)
}