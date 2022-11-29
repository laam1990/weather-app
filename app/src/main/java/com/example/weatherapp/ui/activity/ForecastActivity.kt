package com.example.weatherapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.weatherapp.databinding.ActivityForecastBinding
import com.example.weatherapp.ui.adapter.LocationAdapter
import com.example.weatherapp.ui.viewmodel.ForecastViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForecastActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForecastBinding
    private val viewModel: ForecastViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForecastBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureRecycler()
    }

    private fun configureRecycler() {
        val adapter = LocationAdapter()
        binding.list.adapter = adapter

        //adapter.locations =
    }
}