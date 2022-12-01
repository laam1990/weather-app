package com.example.weatherapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import coil.load
import com.example.weatherapp.R
import com.example.weatherapp.data.util.Resource
import com.example.weatherapp.databinding.ActivityForecastDetailBinding
import com.example.weatherapp.ui.adapter.ForecastDayAdapter
import com.example.weatherapp.ui.models.ForecastViewData
import com.example.weatherapp.ui.viewmodel.ForecastDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForecastDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForecastDetailBinding

    private val viewModel: ForecastDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForecastDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObservers()
        initViews()
    }

    private fun initViews() {
        intent.extras?.let {
            val name = it.getString("name")
            if (name != null) viewModel.getForecast(name)
        }
        configureRecycler()
    }

    private fun initObservers() {
        viewModel.forecastLiveData.observe(this) { result ->
            when (result) {
                is Resource.Error -> {
                    toggleProgressBar(false)
                    toggleDetails(false)
                }
                is Resource.Loading -> {
                    toggleProgressBar(true)
                    toggleDetails(false)
                }
                is Resource.Success -> {
                    result.data?.let {
                        showSuccessView(it)
                    }
                }
            }
        }
    }

    private fun configureRecycler() {
        val adapter = ForecastDayAdapter()
        binding.rvNextDays.adapter = adapter
    }

    private fun showSuccessView(forecastViewData: ForecastViewData) {
        toggleDetails(true)
        toggleProgressBar(false)
        binding.apply {
            forecastViewData.let { fc ->
                tvLocationName.text = fc.location?.name
                tvCondition.text = fc.current?.conditionData?.text
                ivIcon.load("https:"+fc.current?.conditionData?.icon)
                tvTempC.text = getString(R.string.temp_c, fc.current?.tempC.toString())
                tvWindKphValue.text = fc.current?.windKph.toString()
                tvFeelsLikeValue.text = getString(R.string.temp_c, fc.current?.feelsLikeC)
                tvHumidityValue.text = getString(R.string.humidity, fc.current?.humidity.toString())
                tvNextDays.text = getString(R.string.next_days, fc.forecast?.forecastDay?.size.toString())
                fc.forecast?.forecastDay?.let {
                    (rvNextDays.adapter as ForecastDayAdapter).forecasts = it.toMutableList()
                }
            }
        }
    }

    private fun toggleProgressBar(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun toggleDetails(show: Boolean) {
        binding.forecastDetail.visibility = if (show) View.VISIBLE else View.GONE
    }
}