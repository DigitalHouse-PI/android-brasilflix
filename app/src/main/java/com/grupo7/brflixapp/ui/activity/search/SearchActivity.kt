package com.grupo7.brflixapp.ui.activity.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grupo7.brflixapp.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding?.backSearch?.setOnClickListener {
            this?.onBackPressed()
        }

    }
}