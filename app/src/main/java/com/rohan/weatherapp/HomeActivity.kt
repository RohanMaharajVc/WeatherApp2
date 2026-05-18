package com.rohan.weatherapp

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {

    //Declarations
    private lateinit var spinner: Spinner
    private lateinit var edtMinTemp: EditText
    private lateinit var edtMaxTemp: EditText
    private lateinit var edtWeatherCondition: EditText
    private lateinit var btnSaveWeather: Button
    private lateinit var btnAverage: Button
    private lateinit var btnDetailedScreen: Button
    private lateinit var btnClear: Button
    private lateinit var btnExit: Button
    private lateinit var tvAverage: TextView

    // Array of WeatherDay objects used to store all the weekly weather data
    private val weeklyWeather = arrayOf(
        WeatherDay("Monday"),
        WeatherDay("Tuesday"),
        WeatherDay("Wednesday"),
        WeatherDay("Thursday"),
        WeatherDay("Friday"),
        WeatherDay("Saturday"),
        WeatherDay("Sunday")

    )

    //track which days the user selected from the spinner
    private var selectedDayIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)


        //typecasting
        spinner = findViewById(R.id.spinner)
        edtMinTemp = findViewById(R.id.edtMinTemp)
        edtMaxTemp = findViewById(R.id.edtMaxTemp)
        edtWeatherCondition = findViewById(R.id.edtWeatherCondition)
        btnSaveWeather = findViewById(R.id.btnSaveWeather)
        btnAverage = findViewById(R.id.btnAverage)
        btnDetailedScreen = findViewById(R.id.btnDetailedScreen)
        btnClear = findViewById(R.id.btnClear)
        btnExit = findViewById(R.id.btnExit)
        tvAverage = findViewById(R.id.tvAverage)

        //Extract day names from the WeatherDay objects for the spinner
        val dayNames = weeklyWeather.map { it.dayName }

        //creates spinner adapter for day selection
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter


        // saves weather data for the selected day
        btnSaveWeather.setOnClickListener {
            saveWeatherData()
        }

        //calculate the average weekly Tempreture
        btnAverage.setOnClickListener {
            calculateAverageTempreture()
        }

        btnClear.setOnClickListener {
            clearData()
        }

        //exits the app
        btnExit.setOnClickListener {
            finishAffinity()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun saveWeatherData(){}


    private fun calculateAverageTempreture(){}


    private fun clearData(){}

}