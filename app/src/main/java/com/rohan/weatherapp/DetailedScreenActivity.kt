package com.rohan.weatherapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailedScreenActivity : AppCompatActivity() {

    private lateinit var tvWeatherDetails: TextView
    private lateinit var btnBack: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detailed_screen)

        // Typecasting
        tvWeatherDetails = findViewById(R.id.tvWeatherDetails)
        btnBack = findViewById(R.id.btnBack)

        // String variable used to store the full weather report
        var weatherReport = ""

        // Loop through all WeatherDay objects
        for (day in HomeActivity.weatherArray) {

            // Adds weather information to the report string
            weatherReport +=
                "Day: ${day.dayName}\n" +
                        "Minimum Temperature: ${day.minTempreture}°C\n" +
                        "Maximum Temperature: ${day.maxTempreture}°C\n" +
                        "Weather Condition: ${day.weatherCondition}\n\n"
        }

        // Displays all weekly weather information
        tvWeatherDetails.text = weatherReport

        // Returns user back to the Home screen
        btnBack.setOnClickListener {
            finish()
        }





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}