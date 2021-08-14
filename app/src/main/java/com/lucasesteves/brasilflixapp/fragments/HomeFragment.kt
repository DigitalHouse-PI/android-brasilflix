package com.lucasesteves.brasilflixapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.lucasesteves.brasilflixapp.R
import com.lucasesteves.brasilflixapp.adapter.FilmesAdapter
import com.lucasesteves.brasilflixapp.adapter.HomeVPAdapter
import com.lucasesteves.brasilflixapp.databinding.FragmentHomeBinding
import com.lucasesteves.brasilflixapp.endpoint.Endpoint
import com.lucasesteves.brasilflixapp.fragments.homeVP.HomeImageFragment
import com.lucasesteves.brasilflixapp.model.Filmes
import com.lucasesteves.brasilflixapp.model.FilmesResults
import com.lucasesteves.brasilflixapp.util.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private val retrofitClient = RetrofitInstance
        .getRetrofitInstance("https://api.themoviedb.org/3/")
    private val endpoint = retrofitClient.create(Endpoint::class.java)
    private val callback = endpoint.getFilmes(2)
    var fragments: List<Fragment>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        callback.enqueue(object : Callback<FilmesResults> {
            override fun onFailure(call: Call<FilmesResults>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<FilmesResults>, response: Response<FilmesResults>) {
                showData(response.body()!!.results)
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

        showViewPagerHome()


    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
    private fun showData(filmesList: List<Filmes>) {
        filmesList.forEach {
            val filmeAdapter = FilmesAdapter(filmesList)
            binding?.let {
                with(it) {
                    filmesRecyclerView.layoutManager = LinearLayoutManager(context)
                    filmesRecyclerView.adapter = filmeAdapter
                }
            }
        }
    }

    private fun showViewPagerHome(){

        val fragments = listOf(
            HomeImageFragment.newInstance(0),
            HomeImageFragment.newInstance(1)
        )

        val homeViewPager = HomeVPAdapter(fragments, childFragmentManager)
        val viewPager = view?.findViewById<ViewPager>(R.id.viewPagerHome)
        viewPager?.adapter = homeViewPager
        if (viewPager != null) {
            binding?.dotsIndicatorHome?.setViewPager(viewPager)
        }
    }
}