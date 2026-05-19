package com.rohan.weatherapp

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
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
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,dayNames)
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

    private fun saveWeatherData(){

        // Gets the selected spinner position
        val selectedDayIndex = spinner.selectedItemPosition

        val minText = edtMinTemp.text.toString()
        val maxText = edtMaxTemp.text.toString()
        val condition = edtWeatherCondition.text.toString()

        //Error handling to make sure all input fields are completed
        if(minText.isEmpty() || maxText.isEmpty() || condition.isEmpty()){
            Toast.makeText(this, "Please complete all the fields!", Toast.LENGTH_SHORT).show()
            return
        }


        val minTemp = minText.toIntOrNull()
        val maxTemp = maxText.toIntOrNull()

        //ensures Temperatures are valid numeric values
        if(minTemp == null || maxTemp == null){
            Toast.makeText(this, "Temperatures must be valid numbers", Toast.LENGTH_SHORT).show()
            return
        }
        
        //ensure that there is a logical Temperatures input
        if(minTemp > maxTemp){
            Toast.makeText(this, "Minimum temperature cannot be greater than the maximum temperature", Toast.LENGTH_SHORT).show()
            return
        }

        //store the data into the selected weatherDay object
        weeklyWeather[selectedDayIndex].minTempreture = minTemp

        weeklyWeather[selectedDayIndex].maxTempreture = maxTemp

        weeklyWeather[selectedDayIndex].weatherCondition = condition

        weeklyWeather[selectedDayIndex].dataCaptured = true

        //Confirms that the selected days data has been saved
        Toast.makeText(this, "${weeklyWeather[selectedDayIndex].dayName} weather saved", Toast.LENGTH_SHORT).show()

        //clear fields for the next entry
        edtMinTemp.text.clear()
        edtMaxTemp.text.clear()
        edtWeatherCondition.text.clear()


    }


    private fun calculateAverageTempreture(){
        //calculating the average weekly minimum tempreture

        //ensure that all the days temp is there before we calculate
        if(!allDataEntered()) {
            Toast.makeText(
                this, "Please enter all the data for the  7 days before calculating",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        var total = 0

        //add all the maximum tempretures together
        for(day in weeklyWeather){
            total += day.maxTempreture
        }

        //calculate the average
        val average = total / weeklyWeather.size.toDouble()

        //display the average temp
        tvAverage.text = "Average Tempreture: %.1C".format(average)

    }


    //checks if all days have data entered
    private fun allDataEntered() : Boolean{

        for (day in weeklyWeather){

            //returns false if any day is incomplete
            if(!day.dataCaptured){
                return false
            }
        }

        return true
    }



    private fun clearData(){}

}