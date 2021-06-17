package com.example.subwaymvvm.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.subwaymvvm.R
import com.example.subwaymvvm.databinding.ItemArrivalBinding
import com.example.subwaymvvm.domain.model.ArrivalInformation


class StationArrivalsAdapter : ListAdapter<ArrivalInformation, StationArrivalsAdapter.ArrivalViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArrivalViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemArrivalBinding>(layoutInflater, R.layout.item_arrival, parent, false)
        return ArrivalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArrivalViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ArrivalViewHolder(private val binding: ItemArrivalBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(arrivalInformation: ArrivalInformation) {
            binding.model = arrivalInformation

        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ArrivalInformation>() {
            override fun areItemsTheSame(
                oldItem: ArrivalInformation,
                newItem: ArrivalInformation
            ): Boolean {
                return oldItem.subway.id == newItem.subway.id
            }

            override fun areContentsTheSame(
                oldItem: ArrivalInformation,
                newItem: ArrivalInformation
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}