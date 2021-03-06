package com.grupo7.brflixapp.ui.activity.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grupo7.brflixapp.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding?.backSearch?.setOnClickListener {
            this?.onBackPressed()
        }
    }
}