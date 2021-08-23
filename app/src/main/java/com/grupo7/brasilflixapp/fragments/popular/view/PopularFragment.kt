package com.grupo7.brasilflixapp.fragments.popular.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.grupo7.brasilflixapp.adapter.popular.popularAdapter
import com.grupo7.brasilflixapp.databinding.FragmentPopularBinding
import com.grupo7.brasilflixapp.api.main.Endpoint
import com.grupo7.brasilflixapp.model.films.films
import com.grupo7.brasilflixapp.model.films.filmsResults
import com.grupo7.brasilflixapp.api.util.RetrofitInstance
import com.grupo7.brasilflixapp.fragments.home.viewmodel.HomeViewModel
import com.grupo7.brasilflixapp.fragments.popular.viewmodel.PopularViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class popularFragment : Fragment() {

    private var binding: FragmentPopularBinding? = null
    private lateinit var viewModel: PopularViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ------------- Chamando ViewModel -------------//

        activity?.let {
            viewModel = ViewModelProvider(it)[PopularViewModel::class.java]

            viewModel.command = MutableLiveData()

            viewModel.getPopular()
        }

        // ------------- Setar dados ViewModel no RecycleView -------------//

        viewModel.onSuccessPopular.observe(viewLifecycleOwner, {
            it?.let {
                showPopularFilms(it)
            }
        })
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