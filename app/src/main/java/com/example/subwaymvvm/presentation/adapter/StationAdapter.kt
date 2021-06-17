package com.example.subwaymvvm.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.subwaymvvm.R
import com.example.subwaymvvm.databinding.ItemStationBinding
import com.example.subwaymvvm.domain.model.Station
import com.example.subwaymvvm.presentation.extensions.dip
import com.example.subwaymvvm.presentation.view.Badge

class StationAdapter : ListAdapter<Station, StationAdapter.StationViewHolder>(diffUtil) {

    var onItemClickListener : ((Station) -> Unit)? = null
    var onFavoriteClickListener: ((Station) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemStationBinding>(layoutInflater, R.layout.item_station, parent, false)
        return StationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class StationViewHolder(private val binding: ItemStationBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(currentList[adapterPosition])
            }
            binding.favoriteCheckBox.setOnClickListener {
                onFavoriteClickListener?.invoke(currentList[adapterPosition])
            }
        }

        fun bind(station: Station) {
            binding.model = station
            binding.badgeContainer.removeAllViews()

            station.connectedSubways.forEach { subway ->
                binding.badgeContainer.addView(Badge(binding.root.context).apply {
                    badgeColor = subway.color
                    text = subway.label
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        rightMargin = dip(6f)
                    }
                })
            }
            binding.favoriteCheckBox.isChecked = station.isFavorited
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Station>() {
            override fun areItemsTheSame(oldItem: Station, newItem: Station): Boolean {
                return oldItem.connectedSubways[0].id == newItem.connectedSubways[0].id
            }

            override fun areContentsTheSame(oldItem: Station, newItem: Station): Boolean {
                return oldItem == newItem
            }
        }
    }

}