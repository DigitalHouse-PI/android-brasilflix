package com.grupo7.brasilflixapp.fragments.Preferences

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.databinding.FragmentPreferencesBinding


class PreferencesFragment : Fragment() {

    private var binding: FragmentPreferencesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPreferencesBinding.inflate(inflater,container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}