package com.example.oneword.ui.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.oneword.ActivityHolder
import com.example.oneword.R
import com.example.oneword.data.model.Word
import com.example.oneword.data.model.WordsApplication
import com.example.oneword.ui.custom.SuperBottomSheetFragment
import com.example.oneword.ui.factory.TranslateViewModelFactory
import com.example.oneword.ui.factory.WordViewModelFactory
import com.example.oneword.ui.viewmodel.TranslateViewModel
import com.example.oneword.ui.viewmodel.WordViewModel

class TranslateFragment : Fragment() {
    companion object {
        fun newInstance() = TranslateFragment()
    }

    private lateinit var rootView: View
    private lateinit var editText: EditText
    private lateinit var inputEditText : String
    private lateinit var viewModel: TranslateViewModel
    private lateinit var imageSave : ImageView
    private lateinit var textView: TextView

    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((requireActivity().application as WordsApplication).repository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        rootView = inflater.inflate(R.layout.fragment_translate, container, false)
        editText = rootView.findViewById(R.id.editText)
        textView = rootView.findViewById(R.id.langChange)


        textView.setOnClickListener {
            val sheet = DemoBottomSheetFragment()
            sheet.show(parentFragmentManager, "DemoBottomSheetFragment")
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =ViewModelProvider(requireActivity(), TranslateViewModelFactory(requireActivity().application, ""))[TranslateViewModel::class.java]
        imageSave = rootView.findViewById(R.id.imageSave)
        imageSave.setOnClickListener {
            animImage()
            secondSetDrawable()
            inputEditText = editText.text.toString()

            if (inputEditText != "") {
                viewModel.setData(inputEditText)
                viewModel.liveData.observe(requireActivity()) {
                    val word = Word(it[0], it[1], it[2], it[3])
                    wordViewModel.insert(word)
                }
            }
        }

        viewModel.liveData2.observe(requireActivity()) {
            Log.d("TAG", "DEBUG : $it")

        }

    }

    override fun onStart() {
        super.onStart()
        val buttonAllWords : Button = rootView.findViewById(R.id.buttonAllWords)
        buttonAllWords.setOnClickListener {
            mButtonClick()
        }
    }

    private fun mButtonClick () {
        val intent = Intent(activity, ActivityHolder::class.java)
        startActivity(intent)
    }


    private fun animImage(){
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.save_anim)
        imageSave.startAnimation(animation)

    }

    private fun firstSetDrawable() {
        imageSave.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_save))
    }

    private fun secondSetDrawable() {
        imageSave.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_save_full))
    }


    class DemoBottomSheetFragment : SuperBottomSheetFragment() {

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            super.onCreateView(inflater, container, savedInstanceState)
            return inflater.inflate(R.layout.fragment_demo_sheet, container, false)
        }

        override fun getCornerRadius() =
            this.resources.getDimension(R.dimen.demo_sheet_rounded_corner)

        override fun getStatusBarColor() = Color.RED
    }




}


