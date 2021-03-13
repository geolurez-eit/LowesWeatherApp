package com.terremoto.lowesweatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.terremoto.lowesweatherapp.R
import com.terremoto.lowesweatherapp.viewmodel.WeatherViewModel

class CityLookupFragment : Fragment() {

    private lateinit var cityName: EditText
    private lateinit var lookupButton: MaterialButton

    private var listFragment = ListFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_citylookup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            cityName = findViewById(R.id.citylookup_edittext)
            lookupButton = findViewById(R.id.citylookup_lookupbutton)
        }
        lookupButton.setOnClickListener {
            WeatherViewModel.city.value = cityName.text.trim().toString()
            WeatherViewModel.getWeather(WeatherViewModel.city.value.toString())
            parentFragmentManager.beginTransaction()
                .add(R.id.main_framelayout, listFragment)
                .addToBackStack(listFragment.tag)
                .commit()
        }
    }
}