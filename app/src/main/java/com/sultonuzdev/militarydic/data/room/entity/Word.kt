package com.sultonuzdev.militarydic.data.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "englishwords")
data class Word(
    @PrimaryKey
    @ColumnInfo(name = "_id") var id: Int,
    @ColumnInfo(name = "englishname") var englishName: String?=null,
    @ColumnInfo(name = "uzbname") var uzbName: String?=null,
    @ColumnInfo(name = "russianname") var russianName: String?=null,
    @ColumnInfo(name = "synonm") var synName: String? = null
): Parcelable
