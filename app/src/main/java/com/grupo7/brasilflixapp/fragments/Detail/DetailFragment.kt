package com.grupo7.brasilflixapp.fragments.Detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.R.id.action_preferencesFragment_to_myPreferenceFragment
import com.grupo7.brasilflixapp.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.ivHeart?.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment2_to_preferencesFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}