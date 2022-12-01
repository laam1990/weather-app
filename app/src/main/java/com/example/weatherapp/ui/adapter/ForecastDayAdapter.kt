package com.example.weatherapp.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.weatherapp.R
import com.example.weatherapp.ui.models.ForecastDayViewData

class ForecastDayAdapter : RecyclerView.Adapter<ForecastDayAdapter.ForecastDayViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<ForecastDayViewData>() {
        override fun areItemsTheSame(
            oldItem: ForecastDayViewData,
            newItem: ForecastDayViewData
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ForecastDayViewData,
            newItem: ForecastDayViewData
        ): Boolean {
            return oldItem.day == newItem.day
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var forecasts: MutableList<ForecastDayViewData>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastDayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.forecast_item, parent, false)
        return ForecastDayViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastDayViewHolder, position: Int) {
        val location = forecasts[position]
        holder.bind(location)
    }

    class ForecastDayViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvMaxTempC: TextView = view.findViewById(R.id.tvMaxTempC)
        private val tvMinTempC: TextView = view.findViewById(R.id.tvMinTempC)
        private val tvCondition: TextView = view.findViewById(R.id.tvCondition)
        private val ivIcon: AppCompatImageView = view.findViewById(R.id.ivIcon)
        fun bind(forecastDay: ForecastDayViewData) {
            tvMaxTempC.text = forecastDay.day?.maxTempC.toString()
            tvMinTempC.text = forecastDay.day?.minTempC.toString()
            tvCondition.text = forecastDay.day?.conditionData?.text
            ivIcon.load("https:"+forecastDay.day?.conditionData?.icon)
        }
    }

    override fun getItemCount(): Int {
        return forecasts.size
    }
}