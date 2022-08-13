package com.sultonuzdev.militarydic.ui.words

import com.sultonuzdev.militarydic.data.room.entity.Word

interface OnClickListener {
    fun onClick(word: Word)
}