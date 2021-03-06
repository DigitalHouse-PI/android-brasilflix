package com.grupo7.brflixapp.ui.fragments.series.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo7.brflixapp.R
import com.grupo7.brflixapp.base.BaseFragment
import com.grupo7.brflixapp.data.api.util.Command
import com.grupo7.brflixapp.databinding.FragmentSeriesBinding
import com.grupo7.brflixapp.ui.fragments.series.adapter.seriesAdapter
import com.grupo7.brflixapp.ui.fragments.series.adapter.popular.SeriesPopularAdapter
import com.grupo7.brflixapp.ui.fragments.series.adapter.toprated.SeriesTopRatedAdapter
import com.grupo7.brflixapp.ui.fragments.series.viewmodel.SeriesViewModel
import com.grupo7.brflixapp.util.constants.Constants


class seriesFragment : BaseFragment() {

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

            Handler(Looper.getMainLooper()).postDelayed({
                view.post {
                    binding?.layoutRecycleMain?.isVisible = true
                    binding?.loadingLottieSeries?.isVisible = false
                    setupObservablesSeries()
                    setupRecyclerViewSeries()
                    setupObservablesSeriesTopRated()
                    setupRecyclerViewSeriesTopRated()
                    setupObservablesSeriesPopular()
                    setupRecyclerViewSeriesPopular()
                }
            }, 1000L)

        }
    }

//    <------------------------------------------------------ Setup Page 2 - Series On The Air -------------------------------------->

    private val seriesAdapter: seriesAdapter by lazy {
        seriesAdapter { series ->
            val bundle = Bundle()
            bundle.putInt(Constants.Home.KEY_BUNDLE_SERIE_ID, series.id ?: -1)
            findNavController().navigate(
                R.id.action_seriesFragment_to_detailSeriesFragment,
                bundle
            )
        }
    }

    private fun setupObservablesSeries() {
        viewModel.seriesPagedList?.observe(viewLifecycleOwner, {
            seriesAdapter.submitList(it)
        })

        viewModel.command.observe(viewLifecycleOwner, {
            when (it) {
                is Command.Loading -> {

                }
                is Command.Error -> {

                }
            }
        })

    }

    private fun setupRecyclerViewSeries() {
        binding?.seriesRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = seriesAdapter
            adapter?.stateRestorationPolicy = RecyclerView
                .Adapter.StateRestorationPolicy
                .PREVENT_WHEN_EMPTY
        }
    }

    //    <------------------------------------------------------ Setup Page 2 - Series TopRated -------------------------------------->

    private val seriesTopRatedAdapter: SeriesTopRatedAdapter by lazy {
        SeriesTopRatedAdapter { toprated ->
            val bundle = Bundle()
            bundle.putInt(Constants.Home.KEY_BUNDLE_SERIE_ID, toprated.id ?: -1)
            findNavController().navigate(
                R.id.action_seriesFragment_to_detailSeriesFragment,
                bundle
            )
        }
    }

    private fun setupObservablesSeriesTopRated() {
        viewModel.seriesTopRatedPagedList?.observe(viewLifecycleOwner, {
            seriesTopRatedAdapter.submitList(it)
        })

        viewModel.command.observe(viewLifecycleOwner, {
            when (it) {
                is Command.Loading -> {

                }
                is Command.Error -> {

                }
            }
        })

    }

    private fun setupRecyclerViewSeriesTopRated() {
        binding?.seriesRecyclerViewTopRated?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = seriesTopRatedAdapter
            adapter?.stateRestorationPolicy = RecyclerView
                .Adapter.StateRestorationPolicy
                .PREVENT_WHEN_EMPTY
        }
    }

    //    <------------------------------------------------------ Setup Page 2 - Series Popular -------------------------------------->

    private val seriesPopularAdapter: SeriesPopularAdapter by lazy {
        SeriesPopularAdapter { popular ->
            val bundle = Bundle()
            bundle.putInt(Constants.Home.KEY_BUNDLE_SERIE_ID, popular.id ?: -1)
            findNavController().navigate(
                R.id.action_seriesFragment_to_detailSeriesFragment,
                bundle
            )
        }
    }

    private fun setupObservablesSeriesPopular() {
        viewModel.seriesPopularPagedList?.observe(viewLifecycleOwner, {
            seriesPopularAdapter.submitList(it)
        })

        viewModel.command.observe(viewLifecycleOwner, {
            when (it) {
                is Command.Loading -> {

                }
                is Command.Error -> {

                }
            }
        })

    }

    private fun setupRecyclerViewSeriesPopular() {
        binding?.seriesRecyclerViewPopular?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = seriesPopularAdapter
            adapter?.stateRestorationPolicy = RecyclerView
                .Adapter.StateRestorationPolicy
                .PREVENT_WHEN_EMPTY
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override var command: MutableLiveData<Command> = MutableLiveData()

}