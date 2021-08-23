package com.grupo7.brasilflixapp.activity.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.PopupMenu
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.activity.account.AccountActivity
import com.grupo7.brasilflixapp.activity.profile.ProfileActivity
import com.grupo7.brasilflixapp.activity.search.SearchActivity
import com.grupo7.brasilflixapp.activity.splash.SplashActivity
import com.grupo7.brasilflixapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSmoothBottomMenu()

        binding?.topAppBar?.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.SearchFragment -> {
                    startActivity(Intent(this, SearchActivity::class.java))
                    true
                }
                R.id.profileFragment -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                R.id.accountFragment -> {
                    startActivity(Intent(this, AccountActivity::class.java))
                    true
                }
                else -> false
            }
        }

    }


    private fun setupSmoothBottomMenu() {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val popupMenu = PopupMenu(this, null)
        popupMenu.inflate(R.menu.bottom_nav_home)
        val menu = popupMenu.menu
        binding.bottomBar.setupWithNavController(menu, navController)
    }




}