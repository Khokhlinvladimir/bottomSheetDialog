package com.example.oneword.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oneword.R
import com.example.oneword.data.model.WordsApplication
import com.example.oneword.ui.adapters.WordListAdapter
import com.example.oneword.ui.factory.WordViewModelFactory
import com.example.oneword.ui.viewmodel.SafeViewModel
import com.example.oneword.ui.viewmodel.WordViewModel


class SafeFragment : Fragment() {

//    companion object {
//        fun newInstance(s: String) = SafeFragment()
//    }

    private lateinit var viewModel: SafeViewModel
    private lateinit var rootview: View



    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((requireActivity().application as WordsApplication).repository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        rootview = inflater.inflate(R.layout.fragment_safe, container, false)

        // recyclerView
        val recyclerView = rootview.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        wordViewModel.allWords.observe(requireActivity()) { words -> words.let {
            adapter.submitList(it)

            Log.d("LOG", "msg: $it")

        }
        }

        return rootview
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this) [SafeViewModel::class.java]


        }
    }

