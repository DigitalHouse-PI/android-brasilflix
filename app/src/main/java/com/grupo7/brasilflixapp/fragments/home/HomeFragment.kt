package com.grupo7.brasilflixapp.fragments.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.activity.home.HomeActivity
import com.grupo7.brasilflixapp.adapter.films.filmsAdapter
import com.grupo7.brasilflixapp.adapter.home.homeVPAdapter
import com.grupo7.brasilflixapp.adapter.upcoming.upcomingAdapter
import com.grupo7.brasilflixapp.databinding.FragmentHomeBinding
import com.grupo7.brasilflixapp.endpoint.main.Endpoint
import com.grupo7.brasilflixapp.endpoint.upcoming.EndpointUpComing
import com.grupo7.brasilflixapp.model.films.films
import com.grupo7.brasilflixapp.model.films.filmsResults
import com.grupo7.brasilflixapp.util.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    var fragments: List<Fragment>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //------------------ API - Top Rated -------------------------//
        val retrofitClient = RetrofitInstance
            .getRetrofitInstance("https://api.themoviedb.org/3/")
        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getFilmes(2)
        callback.enqueue(object : Callback<filmsResults> {
            override fun onFailure(call: Call<filmsResults>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<filmsResults>, response: Response<filmsResults>) {
                showTopRated(response.body()!!.results)
            }
        })
        //------------------ API - Upcoming -------------------------//
        val endpointUp = retrofitClient.create(EndpointUpComing::class.java)
        val callbackUp = endpointUp.getUpComing(1)
        callbackUp.enqueue(object : Callback<filmsResults> {
            override fun onFailure(call: Call<filmsResults>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<filmsResults>, response: Response<filmsResults>) {
                showUpComing(response.body()!!.results)
            }
        })
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

        // ------------- Mostrar ViewPager Tela Home -------------//
        showViewPagerHome()


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