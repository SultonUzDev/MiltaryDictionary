package com.sultonuzdev.militarydic.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.sultonuzdev.militarydic.data.room.entity.Word
import com.sultonuzdev.militarydic.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    private val args by navArgs<DetailsFragmentArgs>()
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showTheData(args.data)

    }

    private fun showTheData(word: Word) {
        with(binding) {
            tvEnglish.text = word.englishName
            tvRussian.text = word.russianName
            tvUzbek.text = word.uzbName
            if (!word.synName.isNullOrEmpty()) {
                tvSynonm.isVisible = true
                tvSyn.isVisible = true
                vSynonm.isVisible = true
                tvSynonm.text = word.synName
            } else {
                tvSynonm.isVisible = false
                tvSyn.isVisible = false
                vSynonm.isVisible = false
            }
        }
    }


}