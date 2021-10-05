package com.grupo7.brasilflixapp.ui.fragments.detail.seriedetail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.data.database.favorites.entity.FavoritesSeries
import com.grupo7.brasilflixapp.databinding.FragmentDetailSeriesBinding
import com.grupo7.brasilflixapp.ui.fragments.detail.moviedetail.adapter.DetailReviewAdapter
import com.grupo7.brasilflixapp.ui.fragments.detail.seriedetail.adapter.DetailReviewSeriesAdapter
import com.grupo7.brasilflixapp.ui.fragments.detail.seriedetail.viewmodel.DetailSeriesViewModel
import com.grupo7.brasilflixapp.util.constants.Constants


class DetailSeriesFragment : Fragment() {

    private var binding: FragmentDetailSeriesBinding? = null
    private lateinit var detailSeriesViewModel: DetailSeriesViewModel

    private val serieId: Int by lazy {
        arguments?.getInt(Constants.Home.KEY_BUNDLE_SERIE_ID) ?: -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailSeriesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            detailSeriesViewModel = ViewModelProvider(it)[DetailSeriesViewModel::class.java]

            detailSeriesViewModel.command = MutableLiveData()

            detailSeriesViewModel.getReviewsSeries(serieId)

            detailSeriesViewModel.getSerieByIdFromDb(serieId)

            setupReviewsSeries()

            setupDetailSerie()

        }

        binding?.ivMenu?.setOnClickListener {
            activity?.onBackPressed()
        }

        binding?.ivMovie?.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt(Constants.Detail.KEY_BUNDLE_VIDEO_ID_SERIE, serieId)
            findNavController().navigate(
                R.id.action_detailSeriesFragment_to_VideosFragment,
                bundle
            )

        }

        binding?.ivHeart?.setOnClickListener {

            detailSeriesViewModel.onSuccessSerieDbByIdFromDb.observe(viewLifecycleOwner, {

                val id = it.id
                val poster = it.poster_path
                val title = it.original_name
                val favorite = FavoritesSeries(id, poster, title)
                detailSeriesViewModel.saveFavoritesSeriesDb(favorite)

                Snackbar.make(
                    this.requireView(),
                    getString(R.string.favoriteadded),
                    Snackbar.LENGTH_SHORT
                ).show()
            })
        }
    }

    private fun setupDetailSerie() {

        detailSeriesViewModel.onSuccessSerieDbByIdFromDb.observe(viewLifecycleOwner, {
            it?.let { serie ->
                binding?.let { bindingNonNull ->
                    with(bindingNonNull) {
                        activity?.let { activityNonNull ->
                            Glide.with(activityNonNull)
                                .load(serie.poster_path)
                                .placeholder(R.drawable.brflixlogo)
                                .override(900, 500)
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

    private fun setupReviewsSeries() {
        detailSeriesViewModel.onSuccessReviewsSeries.observe(viewLifecycleOwner, {
            if(it.isNullOrEmpty()){
                binding?.nocomentsCard?.isVisible = true
                binding?.reviewsRecyclerView?.isVisible = false
            }else {
                it?.let {
                    val ReviewsAdapter = DetailReviewSeriesAdapter(it)
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
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}