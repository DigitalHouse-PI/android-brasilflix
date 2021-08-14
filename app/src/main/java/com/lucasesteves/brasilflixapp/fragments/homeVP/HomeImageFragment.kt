package com.lucasesteves.brasilflixapp.fragments.homeVP

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.lucasesteves.brasilflixapp.R
import com.lucasesteves.brasilflixapp.databinding.FragmentHomeImageBinding
import com.lucasesteves.brasilflixapp.fragments.tips.PARAM_NUMBER
import com.lucasesteves.brasilflixapp.fragments.tips.TipsImageFragment
import com.lucasesteves.brasilflixapp.model.Filmes

const val PARAM_NUMBER2 = "param_number"

class HomeImageFragment : Fragment() {
    private var binding: FragmentHomeImageBinding? = null
    private val Filmes: Filmes? = null
    private var number: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            number = it.getInt(PARAM_NUMBER2)
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeImageBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when(number){
            0 -> view.findViewById<ImageView>(R.id.imgImageTabHome).setImageDrawable(
                context?.let { ContextCompat.getDrawable(it, R.drawable.tipsimageone) })
            1 -> view.findViewById<ImageView>(R.id.imgImageTabHome).setImageDrawable(
                context?.let { ContextCompat.getDrawable(it, R.drawable.tipsimagetwo) })

        }
    }
    companion object {
        @JvmStatic fun newInstance(param1: Int) =
            HomeImageFragment().apply {
                arguments = Bundle().apply {
                    putInt(PARAM_NUMBER2, param1)
                }
            }
    }


}