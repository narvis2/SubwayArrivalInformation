package com.example.subwaymvvm.presentation.ui.arrival

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.subwaymvvm.R
import com.example.subwaymvvm.databinding.FragmentStationArrivalsBinding
import com.example.subwaymvvm.presentation.adapter.StationArrivalsAdapter
import com.example.subwaymvvm.presentation.base.BaseFragment
import com.example.subwaymvvm.presentation.extensions.toGone
import com.example.subwaymvvm.presentation.extensions.toVisible
import com.example.subwaymvvm.presentation.utils.EventObserver
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class StationArrivalsFragment : BaseFragment<FragmentStationArrivalsBinding, StationArrivalsViewModel>(
    R.layout.fragment_station_arrivals
) {

    override val viewModel: StationArrivalsViewModel by viewModel {
        parametersOf(arguments.station)
    }

    private val arguments: StationArrivalsFragmentArgs by navArgs()

    private val arrivalAdapter : StationArrivalsAdapter by lazy {
        StationArrivalsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreate() {
        arrivalAdapter
        initViews()
    }

    private fun initViews() {
        mViewDataBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = arrivalAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        }
    }

    override fun observeData() = with(viewModel) {
        error.observe(viewLifecycleOwner, EventObserver {
            mViewDataBinding.recyclerView.toGone()
            mViewDataBinding.errorDescriptionTextView.toVisible()
            mViewDataBinding.errorDescriptionTextView.text = it
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_station_arrivals, menu)
        menu.findItem(R.id.favoriteAction).apply {
            setIcon(
                if (arguments.station.isFavorited) {
                    R.drawable.ic_star
                } else {
                    R.drawable.ic_star_empty
                }
            )
            isChecked = arguments.station.isFavorited
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when(item.itemId) {
            R.id.refreshAction -> {
                arrivalAdapter.notifyDataSetChanged()
                viewModel.fetchStationArrivals(arguments.station.name)
                true
            }
            R.id.favoriteAction -> {
                item.isChecked = !item.isChecked
                item.setIcon(
                    if (item.isChecked) {
                        R.drawable.ic_star
                    } else {
                        R.drawable.ic_star_empty
                    }
                )
                viewModel.toggleStationFavorite(arguments.station)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

}