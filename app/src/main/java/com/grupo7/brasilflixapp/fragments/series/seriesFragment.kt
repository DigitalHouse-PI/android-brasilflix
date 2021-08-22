package com.grupo7.brasilflixapp.fragments.series

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.grupo7.brasilflixapp.adapter.series.seriesAdapter
import com.grupo7.brasilflixapp.databinding.FragmentSeriesBinding
import com.grupo7.brasilflixapp.api.main.Endpoint
import com.grupo7.brasilflixapp.model.series.Series
import com.grupo7.brasilflixapp.model.series.SeriesResults
import com.grupo7.brasilflixapp.api.util.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class seriesFragment : Fragment() {

    private var binding: FragmentSeriesBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //------------------ API - Series -------------------------//
        val retrofitClient = RetrofitInstance
            .getRetrofitInstance("https://api.themoviedb.org/3/")
        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getSeries(1)
        callback.enqueue(object : Callback<SeriesResults> {
            override fun onFailure(call: Call<SeriesResults>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<SeriesResults>, response: Response<SeriesResults>) {
                showSeries(response.body()!!.results)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeriesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    private fun showSeries(seriesList: List<Series>) {
        seriesList.forEach {
            val seriesAdapter = seriesAdapter(seriesList)
            binding?.let {
                with(it) {
                    seriesRecyclerView.layoutManager = LinearLayoutManager(context)
                    seriesRecyclerView.adapter = seriesAdapter
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}