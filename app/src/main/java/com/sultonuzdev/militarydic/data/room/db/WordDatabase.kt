package com.sultonuzdev.militarydic.data.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sultonuzdev.militarydic.data.room.dao.WordDao
import com.sultonuzdev.militarydic.data.room.entity.Word

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordDatabase : RoomDatabase() {
    abstract val wordDao: WordDao


    companion object {
        @Volatile
        private var INSTANCE: WordDatabase? = null

        fun getDatabaseInstance(context: Context): WordDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(context, WordDatabase::class.java, "wordDb")
                    .createFromAsset("data.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()


                INSTANCE = instance
                return instance
            }


        }
    }


}