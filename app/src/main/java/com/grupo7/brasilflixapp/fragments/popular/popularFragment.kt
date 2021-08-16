package com.grupo7.brasilflixapp.fragments.popular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.grupo7.brasilflixapp.adapter.popular.popularAdapter
import com.grupo7.brasilflixapp.databinding.FragmentPopularBinding
import com.grupo7.brasilflixapp.endpoint.popular.EndpointPopular
import com.grupo7.brasilflixapp.model.films.films
import com.grupo7.brasilflixapp.model.films.filmsResults
import com.grupo7.brasilflixapp.util.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class popularFragment : Fragment() {

    private var binding: FragmentPopularBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //------------------ API - Popular -------------------------//
        val retrofitClient = RetrofitInstance
            .getRetrofitInstance("https://api.themoviedb.org/3/")
        val endpoint = retrofitClient.create(EndpointPopular::class.java)
        val callback = endpoint.getPopular(1)
        callback.enqueue(object : Callback<filmsResults> {
            override fun onFailure(call: Call<filmsResults>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<filmsResults>, response: Response<filmsResults>) {
                showPopularFilms(response.body()!!.results)
            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun showPopularFilms(filmsList: List<films>) {
        filmsList.forEach {
            val filmeAdapter = popularAdapter(filmsList)
            binding?.let {
                with(it) {
                    popularRecyclerView.layoutManager = LinearLayoutManager(context)
                    popularRecyclerView.adapter = filmeAdapter
                }
            }
        }
    }

}