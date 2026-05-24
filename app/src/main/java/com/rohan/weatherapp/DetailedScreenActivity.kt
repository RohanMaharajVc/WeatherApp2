package com.rohan.weatherapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailedScreenActivity : AppCompatActivity() {

    // Declarations
    // These variables represent the UI components from activity_detailed_screen.xml.
    private lateinit var tvWeatherDetails: TextView
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enables edge-to-edge display for a modern full-screen layout.
        enableEdgeToEdge()

        // Loads the detailed weather screen layout.
        setContentView(R.layout.activity_detailed_screen)

        // Typecasting / linking XML components to Kotlin variables.
        tvWeatherDetails = findViewById(R.id.tvWeatherDetails)
        btnBack = findViewById(R.id.btnBack)

        /*
            WEATHER REPORT STRING

            This variable starts as an empty string.
            As the loop runs, each day's weather information is added to this string.
            Once the loop is finished, the full report is displayed inside tvWeatherDetails.
        */
        var weatherReport = ""

        /*
            ACCESSING DATA FROM HOMEACTIVITY

            The weather data was entered by the user on HomeActivity.
            That data is stored in:

            HomeActivity.weatherArray

            We can access it here because weatherArray was placed inside a companion object
            in HomeActivity.

            This means the detailed screen can read the same WeatherDay objects that were
            updated on the Home screen.
        */
        for (day in HomeActivity.weatherArray) {

            /*
                Each item in weatherArray is a WeatherDay object.

                Example:
                day.dayName gives the name of the day.
                day.minTempreture gives the minimum temperature.
                day.maxTempreture gives the maximum temperature.
                day.weatherCondition gives the weather condition.
            */
            weatherReport +=
                "Day: ${day.dayName}\n" +
                        "Minimum Temperature: ${day.minTempreture}°C\n" +
                        "Maximum Temperature: ${day.maxTempreture}°C\n" +
                        "Weather Condition: ${day.weatherCondition}\n\n"
        }

        // Displays the completed weekly weather report in the TextView.
        tvWeatherDetails.text = weatherReport

        /*
            BACK BUTTON

            finish() closes the current DetailedScreenActivity.
            Because HomeActivity was opened before this screen, the user returns to HomeActivity.
        */
        btnBack.setOnClickListener {
            finish()
        }

        /*
            WINDOW INSETS

            This adjusts screen padding so the layout does not overlap with the phone's
            status bar or navigation bar.
        */
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }
    }
}