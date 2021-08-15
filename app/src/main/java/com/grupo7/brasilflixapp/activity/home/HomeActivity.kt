package com.grupo7.brasilflixapp.activity.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grupo7.brasilflixapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
//        setupSmoothBottomMenu(navController)
//    }
//
//    private fun setupSmoothBottomMenu(navController: NavController) {
//        val popupMenu = PopupMenu(this, null)
//        popupMenu.inflate(R.menu.navigation_menu)
//        val menu = popupMenu.menu
//        binding.bottomBar.setupWithNavController(menu, navController)
   }
}