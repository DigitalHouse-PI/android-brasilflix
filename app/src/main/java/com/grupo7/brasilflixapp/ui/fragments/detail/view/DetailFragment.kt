package com.grupo7.brasilflixapp.ui.fragments.detail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grupo7.brasilflixapp.databinding.FragmentDetailBinding
import com.grupo7.brasilflixapp.model.reviews.ReviewsUser
import com.grupo7.brasilflixapp.ui.fragments.detail.adapter.DetailReviewAdapter
import com.grupo7.brasilflixapp.ui.fragments.detail.viewmodel.DetailViewModel
import com.grupo7.brasilflixapp.util.constants.Constants.Home.KEY_BUNDLE_MOVIE_ID
import com.grupo7.brasilflixapp.util.constants.Constants.Home.KEY_BUNDLE_SERIE_ID


class DetailFragment : Fragment() {
    private var binding: FragmentDetailBinding? = null
    private lateinit var viewModel: DetailViewModel
    private val movieId: Int by lazy {
        arguments?.getInt(KEY_BUNDLE_MOVIE_ID) ?: -1
    }
    private val serieId: Int by lazy {
        arguments?.getInt(KEY_BUNDLE_SERIE_ID) ?: -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            viewModel = ViewModelProvider(it)[DetailViewModel::class.java]

            viewModel.command = MutableLiveData()

            viewModel.getMovieById(movieId)

            viewModel.getReviewsMovies(movieId)

            viewModel.getSeriesById(serieId)

            setupDetailMovie()

            setupDetailSerie()

        }

        binding?.ivMenu?.setOnClickListener{
            activity?.onBackPressed()
        }

        viewModel.onSuccessReviewsMovies.observe(viewLifecycleOwner, {
            it?.let {
                setupReviewsMovies(it)
            }
        })

    }

    private fun setupDetailMovie() {

        viewModel.onSuccessMovieById.observe(viewLifecycleOwner, {
            it?.let { movie ->
                binding?.let { bindingNonNull ->
                    with(bindingNonNull) {
                        activity?.let { activityNonNull ->
                            Glide.with(activityNonNull)
                                .load(movie.backdrop_path)
                                .into(imageCardDetail)
                        }
                        tvTitle.text = movie.title
                        tvTextSummary.text = movie.overview
                        dateCardDetail.text = ("Data de lançamento:  ${movie.release_date}")
                    }
                }
            }
        })


    }

    private fun setupDetailSerie() {

        viewModel.onSuccessSeriesById.observe(viewLifecycleOwner, {
            it?.let { serie ->
                binding?.let { bindingNonNull ->
                    with(bindingNonNull) {
                        activity?.let { activityNonNull ->
                            Glide.with(activityNonNull)
                                .load(serie.backdrop_path)
                                .into(imageCardDetail)
                        }
                        tvTitle.text = serie.original_name
                        tvTextSummary.text = serie.overview
                        dateCardDetail.text = ("Data de lançamento:  ${serie.first_air_date}")
                    }
                }
            }
        })


    }

    private fun setupReviewsMovies(reviewsList: List<ReviewsUser>) {
        reviewsList.forEach {
                val ReviewsAdapter = DetailReviewAdapter(reviewsList)
            binding?.let {
                with(it) {
                    reviewsRecyclerView.layoutManager = LinearLayoutManager(context)
                    reviewsRecyclerView.adapter = ReviewsAdapter
                    reviewsRecyclerView.adapter?.stateRestorationPolicy = RecyclerView
                        .Adapter.StateRestorationPolicy
                        .PREVENT_WHEN_EMPTY
                }
            }
            }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}