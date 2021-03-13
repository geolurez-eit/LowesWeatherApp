package com.terremoto.lowesweatherapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.terremoto.lowesweatherapp.R
import com.terremoto.lowesweatherapp.model.OpenWeatherResponse
import com.terremoto.lowesweatherapp.viewmodel.WeatherViewModel
import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatterBuilder
import java.time.format.TextStyle
import java.time.temporal.ChronoField

class ForecastAdapter(private val forecastDelegate: ForecastDelegate) :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    private var weatherList: List<OpenWeatherResponse.WeatherData>? = listOf()

    inner class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val forecastDate: TextView = itemView.findViewById(R.id.listitem_date_textview)
        val description: TextView = itemView.findViewById(R.id.listitem_description_textview)
        val temp: TextView = itemView.findViewById(R.id.listitem_temp)
        val card: CardView = itemView.findViewById(R.id.listitem_card)
        val icon: ImageView = itemView.findViewById(R.id.listitem_icon_imageview)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        Log.d("TAG_X", "ForecastAdapter.onCreateViewHolder")
        return ForecastViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val dayForecast = WeatherViewModel.forecast.value?.list?.get(position)
        holder.apply {
            forecastDate.text = getDate(dayForecast)
            description.text = dayForecast?.weather?.first()?.description ?: "No Data"
            temp.text = StringBuffer().append(this.itemView.context.getString(R.string.temp))
                .append(dayForecast?.main?.temp.toString())
                .append(this.itemView.context.getString(R.string.degrees))
            card.setOnClickListener {
                forecastDelegate.openDetailsFragment(dayForecast)
            }
            if (dayForecast != null) {
                Glide.with(this.itemView.context)
                    .load(WeatherViewModel.getIconForWeather(dayForecast.weather?.first()?.icon))
                    .placeholder(R.drawable.ic_cloud)
                    .into(icon)
            }
        }
    }

    private fun getDate(dayForecast: OpenWeatherResponse.WeatherData?): String {
        return ZonedDateTime.ofInstant(
            Instant.ofEpochSecond(dayForecast?.dt?.toLong() ?: 0),
            ZoneOffset.UTC
        ).format(
            DateTimeFormatterBuilder()
                .appendText(
                    ChronoField.DAY_OF_WEEK,
                    TextStyle.FULL_STANDALONE
                )
                .appendLiteral(" ")
                .appendValue(ChronoField.CLOCK_HOUR_OF_AMPM)
                .appendLiteral(" ")
                .appendText(ChronoField.AMPM_OF_DAY)
                .toFormatter()
        )

    }


    override fun getItemCount(): Int {
        return weatherList?.size ?: 0
    }

    fun update(openWeatherResponse: OpenWeatherResponse) {
        weatherList = openWeatherResponse.list
        notifyDataSetChanged()
    }

    interface ForecastDelegate {
        fun openDetailsFragment(dayForecast: OpenWeatherResponse.WeatherData?)
    }


}