package com.grupo7.brasilflixapp.activity.redoPassword

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.grupo7.brasilflixapp.databinding.ActivityRedoPasswordBinding

class RedoPasswordActivity: AppCompatActivity() {

    private lateinit var binding: ActivityRedoPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRedoPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}