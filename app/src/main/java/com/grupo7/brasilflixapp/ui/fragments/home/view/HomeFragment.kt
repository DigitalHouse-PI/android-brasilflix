package com.grupo7.brasilflixapp.ui.fragments.home.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.ui.activity.account.AccountActivity
import com.grupo7.brasilflixapp.ui.activity.profile.ProfileActivity
import com.grupo7.brasilflixapp.ui.activity.search.SearchActivity
import com.grupo7.brasilflixapp.ui.fragments.home.adapter.filmsAdapter
import com.grupo7.brasilflixapp.ui.fragments.home.adapter.homeVPAdapter
import com.grupo7.brasilflixapp.databinding.FragmentHomeBinding
import com.grupo7.brasilflixapp.ui.fragments.home.viewpager.HomeImageFragment
import com.grupo7.brasilflixapp.ui.fragments.home.viewmodel.HomeViewModel
import com.grupo7.brasilflixapp.util.constants.Constants.Home.KEY_BUNDLE_MOVIE_ID


class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    var fragments: List<Fragment>? = null
    private lateinit var viewModel: HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    // ------------- Configuração Top Bar -------------//

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.topAppBar?.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.SearchFragment -> {
                    startActivity(Intent(activity, SearchActivity::class.java))
                    true
                }
                R.id.profileFragment -> {
                    startActivity(Intent(activity, ProfileActivity::class.java))
                    true
                }
                R.id.accountFragment -> {
                    startActivity(Intent(activity, AccountActivity::class.java))
                    true
                }
                else -> false
            }
        }

        // ------------- Chamando ViewModel -------------//

        activity?.let {
            viewModel = ViewModelProvider(it)[HomeViewModel::class.java]

            viewModel.command = MutableLiveData()

            setupObservables()
            setupRecyclerView()

        }

        // ------------- Mostrar ViewPager Tela Home -------------//

        showViewPagerHome()
    }

    private fun showViewPagerHome() {

        val fragments = listOf(
            HomeImageFragment.newInstance(0),
            HomeImageFragment.newInstance(1),
            HomeImageFragment.newInstance(2),
            HomeImageFragment.newInstance(3)
        )
        val homeViewPager = homeVPAdapter(fragments, childFragmentManager)
        val viewPager = view?.findViewById<ViewPager>(R.id.viewPagerHome)
        viewPager?.adapter = homeViewPager
        if (viewPager != null) {
            binding?.dotsIndicatorHome?.setViewPager(viewPager)
        }
    }

//    <------------------------------------------------------ Setup Page 2 -------------------------------------->

    private val filmsAdapter: filmsAdapter by lazy {
        filmsAdapter { topRated ->
            val bundle = Bundle()
            bundle.putInt(KEY_BUNDLE_MOVIE_ID, topRated.id ?: -1)
            findNavController().navigate(
                R.id.action_HomeFragment_to_detailFragment,
                bundle
            )
        }
    }

    private fun setupObservables() {
        viewModel.topRatedPagedList?.observe(viewLifecycleOwner, {
            filmsAdapter.submitList(it)
        })

    }

    private fun setupRecyclerView() {
        binding?.filmesRecyclerView?.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = filmsAdapter
            adapter?.stateRestorationPolicy = RecyclerView
                .Adapter.StateRestorationPolicy
                .PREVENT_WHEN_EMPTY
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}