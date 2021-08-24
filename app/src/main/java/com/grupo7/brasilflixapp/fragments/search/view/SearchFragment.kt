package com.grupo7.brasilflixapp.fragments.search.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.adapter.films.filmsAdapter
import com.grupo7.brasilflixapp.adapter.search.searchAdapter
import com.grupo7.brasilflixapp.databinding.FragmentHomeBinding
import com.grupo7.brasilflixapp.databinding.FragmentSearchBinding
import com.grupo7.brasilflixapp.fragments.home.viewmodel.HomeViewModel
import com.grupo7.brasilflixapp.fragments.search.viewmodel.SearchViewModel
import com.grupo7.brasilflixapp.model.films.films


class SearchFragment : Fragment() {

    private var binding: FragmentSearchBinding? = null
    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.buttonSearch?.setOnClickListener{
            val searchtext = binding?.searchText?.text.toString()

            // ------------- Chamando ViewModel -------------//

            activity?.let {
                viewModel = ViewModelProvider(it)[SearchViewModel::class.java]

                viewModel.command = MutableLiveData()

                viewModel.searchMovies(searchtext)

            }
          // ------------- Setar dados ViewModel no RecycleView -------------//

            viewModel.onSuccessSearch.observe(viewLifecycleOwner, {
                it?.let {
                    showTopRated(it)
                }
            })


        }


    }

    private fun showTopRated(filmsList: List<films>) {
        filmsList.forEach {
            val searchAdapter = searchAdapter(filmsList)
            binding?.let {
                with(it) {
                    searchRecyclerView.layoutManager = LinearLayoutManager(context)
                    searchRecyclerView.adapter = searchAdapter
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}