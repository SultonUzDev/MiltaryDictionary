package com.sultonuzdev.militarydic.ui.words.english

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sultonuzdev.militarydic.data.room.entity.Word
import com.sultonuzdev.militarydic.databinding.FragmentEnglishBinding
import com.sultonuzdev.militarydic.ui.words.OnClickListener
import com.sultonuzdev.militarydic.ui.words.WordViewModel


class EnglishFragment : Fragment() {
    private lateinit var wordViewModel: WordViewModel
    private var _binding: FragmentEnglishBinding? = null
    private val binding get() = _binding!!
    private lateinit var wordAdapter: EnglishWordAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEnglishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wordAdapter = EnglishWordAdapter()
        wordViewModel = ViewModelProvider(this)[WordViewModel::class.java]

        initializeData()
        setUpRecyclerView()
        setUpSearchView()
    }

    private fun setUpSearchView() {
        binding.svEnglish.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {

                    filter(query)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    filter(newText)
                    return true
                }
            })
        }

    }

    private fun initializeData() {
        wordViewModel.getEnglishWords().observe(viewLifecycleOwner) { words ->
            wordAdapter.submitWordList(words)
        }
    }


    private fun setUpRecyclerView() {
        binding.rvEnglishWords.apply {
            adapter = wordAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(
                    requireActivity(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
        wordAdapter.setOnClickListener(object : OnClickListener {
            override fun onClick(word: Word) {
                val action = EnglishFragmentDirections.actionEnglishFragmentToDetailsFragment(word)
                findNavController().navigate(action)
            }

        })

    }

    private fun filter(query: String?) {
        if (query != null && query.isNotEmpty()) {
            val searchQuery = "$query%"
            wordViewModel.searchEnglish(searchQuery).observe(this) { list ->
                list.let {
                    wordAdapter.submitWordList(it)
                }
            }
        } else {
            wordViewModel.getEnglishWords().observe(this) { list ->
                wordAdapter.submitWordList(list)

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}