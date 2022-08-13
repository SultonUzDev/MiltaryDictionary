package com.sultonuzdev.militarydic.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sultonuzdev.militarydic.R
import com.sultonuzdev.militarydic.databinding.FragmentMainBinding
import com.sultonuzdev.militarydic.ui.words.WordViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    lateinit var wordViewModel: WordViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wordViewModel = ViewModelProvider(this)[WordViewModel::class.java]
        initializeData()
//        wordViewModel.allWord.observe(viewLifecycleOwner) {
//            Log.d("mlog", "Words: $it");
//
//        }
//                findNav
    }

    private fun initializeData() {
        with(binding) {
            cvEnglishWords.setOnClickListener { findNavController().navigate(R.id.action_mainFragment_to_englishFragment) }
            cvUzbWords.setOnClickListener { findNavController().navigate(R.id.action_mainFragment_to_uzbekFragment) }
            cvRussianWords.setOnClickListener { findNavController().navigate(R.id.action_mainFragment_to_russianFragment) }
            imgBtn.setOnClickListener { findNavController().navigate(R.id.action_mainFragment_to_infoFragment) }

        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}