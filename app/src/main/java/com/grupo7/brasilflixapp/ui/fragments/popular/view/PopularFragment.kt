package com.grupo7.brasilflixapp.ui.fragments.popular.view

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
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.ui.fragments.popular.adapter.popularAdapter
import com.grupo7.brasilflixapp.databinding.FragmentPopularBinding
import com.grupo7.brasilflixapp.model.films.films
import com.grupo7.brasilflixapp.ui.fragments.popular.viewmodel.PopularViewModel
import com.grupo7.brasilflixapp.util.constants.Constants


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
            val filmeAdapter = popularAdapter(filmsList){ movie ->
                val bundle = Bundle()
                movie.id?.let { it1 -> bundle.putInt(Constants.Home.KEY_BUNDLE_MOVIE_ID, it1) }
                findNavController().navigate(
                    R.id.action_popularFragment_to_detailFragment,
                    bundle
                )
            }
            binding?.let {
                with(it) {
                    popularRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    popularRecyclerView.adapter = filmeAdapter
                    popularRecyclerView.adapter?.stateRestorationPolicy = RecyclerView
                        .Adapter.StateRestorationPolicy
                        .PREVENT_WHEN_EMPTY
                }
            }
        }




    }

}