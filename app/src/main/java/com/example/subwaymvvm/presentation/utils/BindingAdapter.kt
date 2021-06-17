package com.example.subwaymvvm.presentation.utils

import android.graphics.Color
import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.subwaymvvm.domain.model.ArrivalInformation
import com.example.subwaymvvm.domain.model.Station
import com.example.subwaymvvm.presentation.adapter.StationAdapter
import com.example.subwaymvvm.presentation.adapter.StationArrivalsAdapter
import com.example.subwaymvvm.presentation.view.Badge
import org.w3c.dom.Text

@BindingAdapter("setColor")
fun TextView.textColor(arrivalMessage: String) {
    this.setTextColor(if (arrivalMessage.contains("당역")) Color.RED else Color.DKGRAY)
}

@BindingAdapter("destination")
fun TextView.destination(destination: String) {
    text = ("\uD83D\uDEA9" + destination)
}

@BindingAdapter("updateTime")
fun TextView.updateTime(updateTime: String) {
    text = ("측정 시간 :" + updateTime)
}

@BindingAdapter("labelBadge", "directionBadge")
fun TextView.badgeText(label: String, direction: String) {
    text = (label+"-"+direction)
}

@BindingAdapter("badgeColor")
fun Badge.badgeColor(color:Int) {
    badgeColor = color
}

@BindingAdapter("setItem")
fun RecyclerView.setItem(data : List<Any>?) {
    when(adapter) {
        is StationAdapter -> {
            if (data.isNullOrEmpty()) {
                with(adapter as StationAdapter) {
                    notifyDataSetChanged()
                    submitList(emptyList())
                }
            } else {
                with(adapter as StationAdapter) {
                    notifyDataSetChanged()
                    submitList(data as List<Station>)
                }
            }
        }
        is StationArrivalsAdapter -> {

            if (data.isNullOrEmpty()) {
                with(adapter as StationArrivalsAdapter) {
                    notifyDataSetChanged()
                    submitList(emptyList())
                }
            } else {
                with(adapter as StationArrivalsAdapter) {
                    notifyDataSetChanged()
                    submitList(data as List<ArrivalInformation>)
                }
            }
        }
        else -> {
            Log.d("BindingAdapter", "${adapter}-> 에러")
        }
    }
}

