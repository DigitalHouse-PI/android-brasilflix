package com.grupo7.brasilflixapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.databinding.FragmentConfirmationEmailBinding


class ConfirmationEmailFragment : Fragment() {

    private var binding: FragmentConfirmationEmailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentConfirmationEmailBinding.inflate(layoutInflater, container, false);
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btGoToHome?.setOnClickListener{
            findNavController().navigate(R.id.action_confirmationEmailFragment_to_home_nav)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}