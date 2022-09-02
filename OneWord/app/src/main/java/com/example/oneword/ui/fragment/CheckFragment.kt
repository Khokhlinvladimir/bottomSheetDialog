package com.example.oneword.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.oneword.R


class CheckFragment : Fragment() {

    private lateinit var rootview: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View { rootview = inflater.inflate(R.layout.fragment_check, container, false)





        return rootview
    }


    }
