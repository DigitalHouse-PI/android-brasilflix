package com.lucasesteves.brasilflixapp.fragments.initial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lucasesteves.brasilflixapp.adapter.initial.initialVPAdapter
import com.lucasesteves.brasilflixapp.databinding.FragmentInicialBinding
import com.lucasesteves.brasilflixapp.fragments.login.LoginFragment
import com.lucasesteves.brasilflixapp.fragments.register.RegisterFragment


class initialFragment : Fragment() {

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
        val fragmentsList = listOf(LoginFragment(), RegisterFragment())
        val fragmentsTitleList = listOf("Login", "Cadastro")

        activity?.let {
            val viewPagerAdapter = initialVPAdapter(
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