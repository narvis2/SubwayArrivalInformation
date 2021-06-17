package com.example.subwaymvvm.presentation.ui.arrival

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.subwaymvvm.R
import com.example.subwaymvvm.databinding.FragmentStationArrivalsBinding
import com.example.subwaymvvm.presentation.base.BaseFragment

class StationArrivalsFragment : BaseFragment<FragmentStationArrivalsBinding, StationArrivalsViewModel>(
    R.layout.fragment_station_arrivals,
    StationArrivalsViewModel::class
) {
    override fun onCreate() {
    }

    override fun observeData() {
    }
}