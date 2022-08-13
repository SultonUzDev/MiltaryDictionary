package com.sultonuzdev.militarydic.ui.words.english

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sultonuzdev.militarydic.data.room.entity.Word
import com.sultonuzdev.militarydic.databinding.ItemWordsListBinding
import com.sultonuzdev.militarydic.ui.words.OnClickListener

class EnglishWordAdapter : RecyclerView.Adapter<EnglishWordAdapter.WordViewHolder>() {
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

    fun submitWordList(words: List<Word>) {
        this.wordList = words
        notifyDataSetChanged()
    }


    inner class WordViewHolder(private val binding: ItemWordsListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(word: Word) {
            binding.apply {
                tvWord.text = word.englishName
                root.setOnClickListener { onClickListener.onClick(word) }
            }

        }
    }
}