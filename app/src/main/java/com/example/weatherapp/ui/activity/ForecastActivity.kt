package com.example.weatherapp.ui.activity

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import com.example.weatherapp.R
import com.example.weatherapp.data.util.Resource
import com.example.weatherapp.databinding.ActivityForecastBinding
import com.example.weatherapp.ui.adapter.LocationAdapter
import com.example.weatherapp.ui.viewmodel.ForecastViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForecastActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityForecastBinding
    private val viewModel: ForecastViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForecastBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObservers()
        initViews()
    }

    private fun initViews() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        configureRecycler()
        handleSearchIntent(intent)
    }

    private fun configureRecycler() {
        val adapter = LocationAdapter(LocationAdapter.OnClickListener {
            Toast.makeText(this, it.country, Toast.LENGTH_LONG).show()
        })
        binding.list.adapter = adapter
    }

    private fun searchLocation(query: String) {
        viewModel.getLocations(query)
    }

    private fun initObservers() {
        viewModel.locationLiveData.observe(this) { result ->
            when (result) {
                is Resource.Error -> {

                }

                is Resource.Loading -> {
                    binding.apply {

                    }
                }

                is Resource.Success -> {
                    result.data?.let {
                        (binding.list.adapter as LocationAdapter).locations = it.toMutableList()
                    }
                }
            }
        }
    }

    private fun handleSearchIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                searchLocation(query)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleSearchIntent(intent)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.forecast_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        val searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView

        searchView.setSearchableInfo(
            searchManager.getSearchableInfo(
                ComponentName(this, ForecastActivity::class.java)))

        searchView.setOnQueryTextListener(this)
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let {
            searchLocation(it)
        }
        return true
    }
}