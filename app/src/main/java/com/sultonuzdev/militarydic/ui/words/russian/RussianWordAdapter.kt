package com.sultonuzdev.militarydic.ui.words.russian

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sultonuzdev.militarydic.data.room.entity.Word
import com.sultonuzdev.militarydic.databinding.ItemWordsListBinding
import com.sultonuzdev.militarydic.ui.words.OnClickListener

class RussianWordAdapter : RecyclerView.Adapter<RussianWordAdapter.WordViewHolder>() {
    private var wordList = emptyList<Word>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder(
            ItemWordsListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = wordList.size

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(wordList[position])
    }

    private lateinit var onClickListener: OnClickListener
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitWordList(words: List<Word>) {
        this.wordList = words
        notifyDataSetChanged()
    }

    inner class WordViewHolder(private val binding: ItemWordsListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(word: Word) {
            binding.apply {
                tvWord.text = word.russianName?.get(0)?.uppercaseChar().toString() + word.russianName?.substring(1, word.russianName!!.length )
                root.setOnClickListener { onClickListener.onClick(word) }
            }
        }
    }
}