package com.grupo7.brasilflixapp.adapter.tips

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class tipsVPAdapter (
    private val fragments: List<Fragment>,
    manager: FragmentManager
        ): FragmentPagerAdapter(manager){
    override fun getCount() = fragments.size

    override fun getItem(position: Int) = fragments[position]
        }