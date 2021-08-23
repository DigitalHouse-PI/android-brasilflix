package com.grupo7.brasilflixapp.fragments.series.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.grupo7.brasilflixapp.adapter.series.seriesAdapter
import com.grupo7.brasilflixapp.databinding.FragmentSeriesBinding
import com.grupo7.brasilflixapp.api.main.Endpoint
import com.grupo7.brasilflixapp.model.series.Series
import com.grupo7.brasilflixapp.model.series.SeriesResults
import com.grupo7.brasilflixapp.api.util.RetrofitInstance
import com.grupo7.brasilflixapp.fragments.home.viewmodel.HomeViewModel
import com.grupo7.brasilflixapp.fragments.series.viewmodel.SeriesViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class seriesFragment : Fragment() {

    private var binding: FragmentSeriesBinding? = null
    private lateinit var viewModel: SeriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeriesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ------------- Chamando ViewModel -------------//

        activity?.let {
            viewModel = ViewModelProvider(it)[SeriesViewModel::class.java]

            viewModel.command = MutableLiveData()

            viewModel.getSeries()

        }

        // ------------- Setar dados ViewModel no RecycleView -------------//

        viewModel.onSuccessTopRated.observe(viewLifecycleOwner, {
            it?.let {
                showSeries(it)
            }
        })


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