package com.terremoto.lowesweatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.appbar.MaterialToolbar
import com.terremoto.lowesweatherapp.R
import com.terremoto.lowesweatherapp.model.OpenWeatherResponse
import com.terremoto.lowesweatherapp.viewmodel.WeatherViewModel
import java.util.*

class DetailsFragment(private val dayForecast: OpenWeatherResponse.WeatherData?) : Fragment() {

    private lateinit var tempMain: TextView
    private lateinit var tempFeel: TextView
    private lateinit var description: TextView
    private lateinit var icon: ImageView
    private lateinit var topAppBar: MaterialToolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            tempMain = findViewById(R.id.details_tempmain_textview)
            tempFeel = findViewById(R.id.details_tempfeel_textview)
            description = findViewById(R.id.details_description_textview)
            icon = findViewById(R.id.details_icon_imageview)
            topAppBar = findViewById(R.id.details_topAppBar)
        }
        topAppBar.title = WeatherViewModel.city.value.toString()
        val stringBuilder = StringBuffer()
        tempMain.text = stringBuilder.append(dayForecast?.main?.temp.toString())
            .append(getString(R.string.degrees))
        tempFeel.text = StringBuffer().append(getString(R.string.feels_like))
            .append(dayForecast?.main?.feels_like.toString())
            .append(getString(R.string.degrees))
        description.text = dayForecast?.weather?.first()?.description?.capitalize(Locale.ROOT)
        topAppBar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
        Glide.with(view.context)
            .load(WeatherViewModel.getIconForWeather(dayForecast?.weather?.first()?.icon))
            .placeholder(R.drawable.ic_cloud)
            .into(icon)
    }
}