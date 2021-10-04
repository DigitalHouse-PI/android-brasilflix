package com.grupo7.brasilflixapp.ui.fragments.videos.view

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.databinding.FragmentDetailBinding
import com.grupo7.brasilflixapp.databinding.FragmentVideosBinding
import com.grupo7.brasilflixapp.ui.fragments.detail.main.adapter.DetailReviewSearchAdapter
import com.grupo7.brasilflixapp.ui.fragments.home.adapter.filmsAdapter
import com.grupo7.brasilflixapp.ui.fragments.home.viewmodel.HomeViewModel
import com.grupo7.brasilflixapp.ui.fragments.videos.adapter.VideosAdapter
import com.grupo7.brasilflixapp.ui.fragments.videos.viewmodel.VideosViewModel
import com.grupo7.brasilflixapp.ui.model.videos.Videos
import com.grupo7.brasilflixapp.util.constants.Constants
import com.grupo7.brasilflixapp.util.constants.Constants.Detail.KEY_BUNDLE_VIDEO_ID_MOVIE
import com.grupo7.brasilflixapp.util.constants.Constants.Detail.KEY_BUNDLE_VIDEO_ID_SERIE


class VideosFragment : Fragment() {

    private var binding: FragmentVideosBinding? = null
    private lateinit var viewModel: VideosViewModel
    private val serieId: Int by lazy {
        arguments?.getInt(KEY_BUNDLE_VIDEO_ID_SERIE) ?: -1
    }

    private val movieId: Int by lazy {
        arguments?.getInt(KEY_BUNDLE_VIDEO_ID_MOVIE) ?: -1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideosBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.ivMenu?.setOnClickListener {
            activity?.onBackPressed()
        }

        // ------------- Chamando ViewModel -------------//

        activity?.let {
            viewModel = ViewModelProvider(it)[VideosViewModel::class.java]

            viewModel.command = MutableLiveData()

            viewModel.getMoviesVideos(movieId)
            viewModel.getSeriesVideos(serieId)

            setupObservablesMovies()
        }
    }
    private fun setupObservablesMovies() {
        viewModel.onSuccessMoviesVideos?.observe(viewLifecycleOwner, {
            it?.let {
                val VideosAdapter = VideosAdapter(it){

                }
                binding?.let {
                    with(it) {
                        videosRecyclerView.layoutManager = LinearLayoutManager(context)
                        videosRecyclerView.adapter = VideosAdapter
                        videosRecyclerView.adapter?.stateRestorationPolicy = RecyclerView
                            .Adapter.StateRestorationPolicy
                            .PREVENT_WHEN_EMPTY
                    }
                }
            }
        })

    }



}