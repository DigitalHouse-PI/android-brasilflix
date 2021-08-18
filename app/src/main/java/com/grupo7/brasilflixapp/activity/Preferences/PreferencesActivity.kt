package com.grupo7.brasilflixapp.activity.Preferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.databinding.ActivityPreferencesBinding

class PreferencesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreferencesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferencesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}