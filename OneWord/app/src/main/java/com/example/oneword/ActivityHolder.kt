package com.example.oneword

import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.viewpager.widget.ViewPager
import com.example.oneword.ui.viewmodel.WordViewModel
import com.example.oneword.ui.factory.WordViewModelFactory
import com.example.oneword.data.model.WordsApplication
import com.example.oneword.ui.adapters.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout

class ActivityHolder : AppCompatActivity() {

    private lateinit var imageButton: ImageButton

    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as WordsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_holder)
        supportActionBar?.hide()

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        imageButton = findViewById(R.id.imageButton_more)

    }


    override fun onStart() {
        super.onStart()

        imageButton.setOnClickListener {
            popupMenu(imageButton)
        }
    }





    private fun popupMenu(view: View){

        val popupMenu = PopupMenu(this, view, Gravity.END, 0, R.style.PopupMenuStyle)
        popupMenu.inflate(R.menu.popup_menu)
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener {
            wordViewModel.delete()
            true
        })
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            popupMenu.setForceShowIcon(true)
        }
        popupMenu.show()
    }




    private fun basicAlert(){


        val builder = AlertDialog.Builder(this)

        with(builder)
        {
            setTitle("Androidly Alert")
            setMessage("We have a message")
            show()
        }


    }





}




