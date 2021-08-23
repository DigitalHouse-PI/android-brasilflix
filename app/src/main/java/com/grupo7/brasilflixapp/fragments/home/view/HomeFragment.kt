package com.grupo7.brasilflixapp.fragments.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.adapter.films.filmsAdapter
import com.grupo7.brasilflixapp.adapter.home.homeVPAdapter
import com.grupo7.brasilflixapp.adapter.upcoming.upcomingAdapter
import com.grupo7.brasilflixapp.databinding.FragmentHomeBinding
import com.grupo7.brasilflixapp.api.main.Endpoint
import com.grupo7.brasilflixapp.model.films.films
import com.grupo7.brasilflixapp.model.films.filmsResults
import com.grupo7.brasilflixapp.api.util.RetrofitInstance
import com.grupo7.brasilflixapp.fragments.home.viewpager.HomeImageFragment
import com.grupo7.brasilflixapp.fragments.home.viewmodel.HomeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
            val filmeAdapter = filmsAdapter(filmsList)
            binding?.let {
                with(it) {
                    filmesRecyclerView.layoutManager = LinearLayoutManager(context)
                    filmesRecyclerView.adapter = filmeAdapter
                }
            }
        }
    }

    private fun showUpComing(filmsList: List<films>) {
        filmsList.forEach {
            val filmeAdapter = upcomingAdapter(filmsList)
            binding?.let {
                with(it) {
                    upcomingRecyclerView.layoutManager = LinearLayoutManager(context)
                    upcomingRecyclerView.adapter = filmeAdapter
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