package com.example.subwaymvvm.presentation.ui.station

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.subwaymvvm.R
import com.example.subwaymvvm.databinding.FragmentStationBinding
import com.example.subwaymvvm.presentation.adapter.StationAdapter
import com.example.subwaymvvm.presentation.base.BaseFragment

class StationFragment : BaseFragment<FragmentStationBinding, StationViewModel>(
    R.layout.fragment_station,
    StationViewModel::class
){

    private lateinit var stationAdapter : StationAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews()
    }

    override fun onCreate() {
        stationAdapter = StationAdapter()
        mViewDataBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = stationAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }
    }

    override fun observeData() = with(viewModel) {
        stations.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                stationAdapter.submitList(listOf())
            } else {
                stationAdapter.submitList(it)
                hideProgress()
            }
        })

    }

    private fun bindViews() {
        mViewDataBinding.searchEditText.addTextChangedListener {
            viewModel.filterStation(it.toString())
        }

        stationAdapter.apply {
            onItemClickListener =  { station ->
                val action = StationFragmentDirections.actionStationFragmentToStationArrivalsFragment(station)
                findNavController().navigate(action)
            }
            onFavoriteClickListener = {

            }
        }

    }
}