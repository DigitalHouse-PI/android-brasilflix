package com.grupo7.brasilflixapp.fragments.home.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.adapter.films.filmsAdapter
import com.grupo7.brasilflixapp.adapter.home.homeVPAdapter
import com.grupo7.brasilflixapp.adapter.upcoming.upcomingAdapter
import com.grupo7.brasilflixapp.databinding.FragmentHomeBinding
import com.grupo7.brasilflixapp.model.films.films
import com.grupo7.brasilflixapp.fragments.home.viewpager.HomeImageFragment
import com.grupo7.brasilflixapp.fragments.home.viewmodel.HomeViewModel
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ------------- Chamando ViewModel -------------//

        activity?.let {
            viewModel = ViewModelProvider(it)[HomeViewModel::class.java]


            viewModel.command = MutableLiveData()

            viewModel.getTopRatedMovies()

            viewModel.getUpcomingMovies()
        }

        // ------------- Mostrar ViewPager Tela Home -------------//
        showViewPagerHome()


        // ------------- Setar dados ViewModel no RecycleView -------------//

        viewModel.onSuccessTopRated.observe(viewLifecycleOwner, {
            it?.let {
                showTopRated(it)
            }
        })

        viewModel.onSuccessUpcoming.observe(viewLifecycleOwner, {
            it?.let {
                showUpComing(it)
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun showTopRated(filmsList: List<films>) {
        filmsList.forEach {
            val filmeAdapter = filmsAdapter(filmsList){ movie ->
                val bundle = Bundle()
                bundle.putInt(KEY_BUNDLE_MOVIE_ID, movie.id)
                findNavController().navigate(
                    R.id.action_HomeFragment_to_detailFragment,
                    bundle
                )
            }
            binding?.let {
                with(it) {
                    filmesRecyclerView.layoutManager = LinearLayoutManager(context)
                    filmesRecyclerView.adapter = filmeAdapter
                    filmesRecyclerView.adapter?.stateRestorationPolicy = RecyclerView
                        .Adapter.StateRestorationPolicy
                        .PREVENT_WHEN_EMPTY
                }
            }
        }
    }

    private fun showUpComing(filmsList: List<films>) {
        filmsList.forEach {
            val filmeAdapter = upcomingAdapter(filmsList){ movie ->
                val bundle = Bundle()
                bundle.putInt(KEY_BUNDLE_MOVIE_ID, movie.id)
                findNavController().navigate(
                    R.id.action_HomeFragment_to_detailFragment,
                    bundle
                )

            }
            binding?.let {
                with(it) {
                    upcomingRecyclerView.layoutManager = LinearLayoutManager(context)
                    upcomingRecyclerView.adapter = filmeAdapter
                    upcomingRecyclerView.adapter?.stateRestorationPolicy = RecyclerView
                        .Adapter.StateRestorationPolicy
                        .PREVENT_WHEN_EMPTY
                }
            }
        }
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
}