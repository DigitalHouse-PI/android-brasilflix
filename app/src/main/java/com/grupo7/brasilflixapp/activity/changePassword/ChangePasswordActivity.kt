package com.grupo7.brasilflixapp.activity.changePassword

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.grupo7.brasilflixapp.databinding.ActivityChangePasswordBinding

class ChangePasswordActivity: AppCompatActivity() {

    private lateinit var binding: ActivityChangePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}