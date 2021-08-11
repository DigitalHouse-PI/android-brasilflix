package com.lucasesteves.brasilflixapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lucasesteves.brasilflixapp.R
import com.lucasesteves.brasilflixapp.adapter.InicialVPAdapter
import com.lucasesteves.brasilflixapp.databinding.FragmentInicialBinding


class inicialFragment : Fragment() {

    private var binding: FragmentInicialBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInicialBinding.inflate(inflater, container, false)
        return binding?.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentsList = listOf(LoginFragment(), CadastrarFragment())
        val fragmentsTitleList = listOf("Login", "Cadastro")

        activity?.let {
            val viewPagerAdapter = InicialVPAdapter(
                fragmentManager = childFragmentManager,
                fragmentsList = fragmentsList,
                fragmentsTitleList = fragmentsTitleList
            )
            binding?.let { bindingNonNull ->
                with(bindingNonNull) {
                    vpPages.adapter = viewPagerAdapter
                    tabLayout.setupWithViewPager(vpPages)

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}