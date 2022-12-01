package com.example.weatherapp.ui.activity

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import coil.load
import com.example.weatherapp.R
import com.example.weatherapp.common.view.ErrorFullScreen
import com.example.weatherapp.common.view.UIErrorContent
import com.example.weatherapp.common.view.visible
import com.example.weatherapp.data.util.Resource
import com.example.weatherapp.databinding.ActivityForecastDetailBinding
import com.example.weatherapp.ui.adapter.ForecastDayAdapter
import com.example.weatherapp.ui.models.ForecastViewData
import com.example.weatherapp.ui.viewmodel.ForecastDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForecastDetailActivity : AppCompatActivity(),
    ErrorFullScreen.ErrorScreenListener {

    private lateinit var binding: ActivityForecastDetailBinding
    private val viewModel: ForecastDetailViewModel by viewModels()
    private var name: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForecastDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObservers()
        initViews()
    }

    private fun initViews() {
        intent.extras?.let { int ->
            name = int.getString("name")
            name?.let {
                viewModel.getForecast(it)
            }
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
                ivIcon.load("https:" + fc.current?.conditionData?.icon)
                tvTempC.text = getString(R.string.temp_c, fc.current?.tempC.toString())
                tvWindKphValue.text = fc.current?.windKph.toString()
                tvFeelsLikeValue.text = getString(R.string.temp_c, fc.current?.feelsLikeC)
                tvHumidityValue.text = getString(R.string.humidity, fc.current?.humidity.toString())
                tvNextDays.text =
                    getString(R.string.next_days, fc.forecast?.forecastDay?.size.toString())
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

    private fun configureBTFErrorDisplay(isConnectionError: Boolean) {
        binding.apply {
            error.apply {
                setListener(this@ForecastDetailActivity)
                visible()
                val typeError: UIErrorContent = if (isConnectionError) {
                    UIErrorContent.Connection(
                        getString(R.string.has_a_internet_problem),
                        getString(R.string.internet_problem),
                        getString(R.string.retry),
                        getString(R.string.check_internet_connection),
                        ContextCompat.getDrawable(
                            this@ForecastDetailActivity,
                            R.drawable.img_error_internet_connection
                        )
                    )
                } else {
                    UIErrorContent.Generic(
                        getString(R.string.trouble_loading),
                        getString(R.string.has_a_backend_problem),
                        getString(R.string.retry),
                        getString(R.string.check_internet_connection),
                        ContextCompat.getDrawable(
                            this@ForecastDetailActivity,
                            R.drawable.img_error_be_response
                        )
                    )
                }
                setContentData(typeError)
            }
        }
    }

    private fun openAndroidWirelessSettings() {
        try {
            startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
        } catch (activityNotFoundException: ActivityNotFoundException) {
            activityNotFoundException.printStackTrace()
        }
    }

    override fun actionBTFErrorScreen(isConnectionError: Boolean) {
        if (isConnectionError)
            openAndroidWirelessSettings()
        else {
            name?.let {
                viewModel.getForecast(it)
            }
        }
    }
}