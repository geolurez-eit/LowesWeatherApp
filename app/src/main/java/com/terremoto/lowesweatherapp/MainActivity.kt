package com.terremoto.lowesweatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.terremoto.lowesweatherapp.view.CityLookupFragment

class MainActivity : AppCompatActivity() {

    private val cityLookupFragment = CityLookupFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.main_framelayout, cityLookupFragment)
            .addToBackStack(cityLookupFragment.tag)
            .commit()
    }
}