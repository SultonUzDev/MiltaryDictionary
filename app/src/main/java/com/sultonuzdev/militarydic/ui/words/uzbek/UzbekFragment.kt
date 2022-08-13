package com.sultonuzdev.militarydic.ui.words.uzbek

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
import com.sultonuzdev.militarydic.databinding.FragmentUzbekBinding
import com.sultonuzdev.militarydic.ui.words.OnClickListener
import com.sultonuzdev.militarydic.ui.words.WordViewModel


class UzbekFragment : Fragment() {
    private lateinit var wordViewModel: WordViewModel
    private var _binding: FragmentUzbekBinding? = null
    private val binding get() = _binding!!
    private lateinit var wordAdapter: UzbekWordAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUzbekBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wordAdapter = UzbekWordAdapter()
        wordViewModel = ViewModelProvider(this)[WordViewModel::class.java]

        initializeData()
        setUpRecyclerView()
        setUpSearchView()
    }

    private fun setUpSearchView() {
        binding.svUzbek.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    filter(query)

                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    filter(newText)
                    return true
                }


            })
        }

    }

    private fun initializeData() {
        wordViewModel.getUzbekWords().observe(viewLifecycleOwner) { words ->
            wordAdapter.submitWordList(words)
        }
    }


    private fun setUpRecyclerView() {
        binding.rvUzbek.apply {
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
                val action = UzbekFragmentDirections.actionUzbekFragmentToDetailsFragment(word)
                findNavController().navigate(action)
            }

        })

    }

    private fun filter(query: String?) {
        if (query != null && query.isNotEmpty()) {
            val searchQuery = "$query%"
            wordViewModel.searchUzbek(searchQuery).observe(this) { list ->
                list.let {
                    wordAdapter.submitWordList(it)
                }
            }
        } else {
            wordViewModel.getUzbekWords().observe(this) { list ->
                list.let { wordAdapter.submitWordList(it) }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}