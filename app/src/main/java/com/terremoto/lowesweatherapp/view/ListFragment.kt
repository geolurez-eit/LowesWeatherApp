package com.terremoto.lowesweatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.terremoto.lowesweatherapp.R
import com.terremoto.lowesweatherapp.model.OpenWeatherResponse
import com.terremoto.lowesweatherapp.viewmodel.WeatherViewModel

class ListFragment : Fragment(), ForecastAdapter.ForecastDelegate {

    private lateinit var forecastRecyclerView: RecyclerView
    private lateinit var topAppBar : MaterialToolbar

    private val forecastAdapter = ForecastAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            forecastRecyclerView = findViewById(R.id.list_recyclerview)
            topAppBar = findViewById(R.id.list_topAppBar)
        }
        forecastRecyclerView.adapter = forecastAdapter
        topAppBar.title = WeatherViewModel.city.value.toString()
        topAppBar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
        WeatherViewModel.forecast.observe(this.viewLifecycleOwner,{
            forecastAdapter.update(it)
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun openDetailsFragment(dayForecast: OpenWeatherResponse.WeatherData?) {
        val detailsFragment = DetailsFragment(dayForecast)
        parentFragmentManager.beginTransaction()
            .add(R.id.main_framelayout, detailsFragment)
            .addToBackStack(detailsFragment.tag)
            .commit()
    }
}