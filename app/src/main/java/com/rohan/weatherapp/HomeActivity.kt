package com.rohan.weatherapp

import android.content.Intent
import android.os.Bundle
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

    // Declarations
    // These variables represent the UI components from activity_home.xml.
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

    /*
        COMPANION OBJECT EXPLANATION

        Previously, we could have created the array like this:

        private val weatherArray = arrayOf(...)

        However, that would make the array belong only to this HomeActivity object.
        The DetailedScreenActivity would not be able to access it directly.

        A companion object allows the array to belong to the HomeActivity class itself.
        This means another activity can access it using:

        HomeActivity.weatherArray

        We use this because the user enters the weather data on the Home screen,
        but the Detailed screen also needs to read and display that same data.

        In short:
        - Normal private array: only HomeActivity can use it.
        - Companion object array: HomeActivity and DetailedScreenActivity can use it.

        This is useful here because we are not using a database yet.
        The companion object keeps the data available while the app is running.
    */
    companion object {

        // Array of WeatherDay objects used to store all weekly weather data.
        // Each WeatherDay object stores:
        // day name, minimum temperature, maximum temperature, condition, and whether data was captured.
        val weatherArray = arrayOf(
            WeatherDay("Monday"),
            WeatherDay("Tuesday"),
            WeatherDay("Wednesday"),
            WeatherDay("Thursday"),
            WeatherDay("Friday"),
            WeatherDay("Saturday"),
            WeatherDay("Sunday")
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Allows the app layout to extend behind system bars for a modern look.
        enableEdgeToEdge()

        // Loads the Home screen XML layout.
        setContentView(R.layout.activity_home)

        // Typecasting / linking XML components to Kotlin variables.
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

        /*
            SPINNER SETUP

            The spinner needs a list of text values to display.
            Our weatherArray stores WeatherDay objects, not simple strings.
            Therefore, we use map to extract only the dayName from each WeatherDay object.

            Example:
            WeatherDay("Monday") becomes "Monday"
            WeatherDay("Tuesday") becomes "Tuesday"
        */
        val dayNames = weatherArray.map { it.dayName }

        // Creates an adapter to place the day names into the spinner.
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dayNames)

        // Sets the layout used when the spinner dropdown opens.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Connects the adapter to the spinner so the days appear in the dropdown.
        spinner.adapter = adapter

        // When the Save Weather button is clicked, save the selected day's weather data.
        btnSaveWeather.setOnClickListener {
            saveWeatherData()
        }

        // When the Calculate Average button is clicked, calculate the weekly average temperature.
        btnAverage.setOnClickListener {
            calculateAverageTempreture()
        }

        // When the Clear button is clicked, reset all stored weather data.
        btnClear.setOnClickListener {
            clearData()
        }

        // When the Exit button is clicked, close the whole application.
        btnExit.setOnClickListener {
            finishAffinity()
        }

        /*
            Detailed screen navigation.

            The user should only be allowed to view the detailed screen
            once all 7 days have weather data.
        */
        btnDetailedScreen.setOnClickListener {

            if (allDataEntered()) {
                val intent = Intent(this, DetailedScreenActivity::class.java)
                startActivity(intent)

                /*
                    Do not use finish() here if you want the user to come back
                    to this HomeActivity using the Back button on the detailed screen.

                    If you use finish(), HomeActivity closes.
                    Then the detailed screen cannot simply return to it.
                */
            } else {
                Toast.makeText(
                    this,
                    "Please enter data for all 7 days first",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Adjusts padding so content does not overlap with status/navigation bars.
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

    private fun saveWeatherData() {

        /*
            Gets the selected day from the spinner.

            Example:
            If Monday is selected, selectedDayIndex = 0.
            If Tuesday is selected, selectedDayIndex = 1.
            If Sunday is selected, selectedDayIndex = 6.

            This index matches the position of the WeatherDay object inside weatherArray.
        */
        val selectedDayIndex = spinner.selectedItemPosition

        // Gets text entered by the user.
        val minText = edtMinTemp.text.toString()
        val maxText = edtMaxTemp.text.toString()
        val condition = edtWeatherCondition.text.toString()

        // Error handling: checks that all fields are completed.
        if (minText.isEmpty() || maxText.isEmpty() || condition.isEmpty()) {
            Toast.makeText(this, "Please complete all the fields!", Toast.LENGTH_SHORT).show()
            return
        }

        /*
            Converts the temperature text into numbers.

            toIntOrNull() is safer than toInt().
            If the user enters invalid text, it returns null instead of crashing the app.
        */
        val minTemp = minText.toIntOrNull()
        val maxTemp = maxText.toIntOrNull()

        // Error handling: checks that temperatures are valid numbers.
        if (minTemp == null || maxTemp == null) {
            Toast.makeText(this, "Temperatures must be valid numbers", Toast.LENGTH_SHORT).show()
            return
        }

        // Error handling: minimum temperature should not be greater than maximum temperature.
        if (minTemp > maxTemp) {
            Toast.makeText(
                this,
                "Minimum temperature cannot be greater than the maximum temperature",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        /*
            Stores the user input into the selected WeatherDay object.

            For example:
            If the user selected Wednesday, selectedDayIndex will be 2.
            This means the data will be stored in weatherArray[2].
        */
        weatherArray[selectedDayIndex].minTempreture = minTemp
        weatherArray[selectedDayIndex].maxTempreture = maxTemp
        weatherArray[selectedDayIndex].weatherCondition = condition
        weatherArray[selectedDayIndex].dataCaptured = true

        // Confirms that the selected day's data has been saved.
        Toast.makeText(
            this,
            "${weatherArray[selectedDayIndex].dayName} weather saved",
            Toast.LENGTH_SHORT
        ).show()

        // Clears fields so the user can enter the next day's data.
        edtMinTemp.text.clear()
        edtMaxTemp.text.clear()
        edtWeatherCondition.text.clear()
    }

    private fun calculateAverageTempreture() {

        /*
            Before calculating, we check that all 7 days have been entered.
            This prevents the average from being calculated with missing or default values.
        */
        if (!allDataEntered()) {
            Toast.makeText(
                this,
                "Please enter all the data for the 7 days before calculating",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        // Variable used to store the total of all maximum temperatures.
        var total = 0

        /*
            Loop through each WeatherDay object in the weatherArray.

            Each day has a maxTempreture value.
            We add each maxTempreture to total.
        */
        for (day in weatherArray) {
            total += day.maxTempreture
        }

        /*
            Calculates the average.

            weatherArray.size gives the number of days in the array.
            Since there are 7 WeatherDay objects, weatherArray.size = 7.
        */
        val average = total / weatherArray.size.toDouble()

        // Displays the average temperature to one decimal place.
        tvAverage.text = "Average Temperature: %.1f°C".format(average)
    }

    private fun allDataEntered(): Boolean {

        /*
            This function checks whether every day has data.

            If even one WeatherDay object has dataCaptured = false,
            the function returns false.
        */
        for (day in weatherArray) {

            // If one day is incomplete, stop checking and return false.
            if (!day.dataCaptured) {
                return false
            }
        }

        // If the loop finishes, it means all days were completed.
        return true
    }

    private fun clearData() {

        /*
            This loop resets every WeatherDay object.

            It clears:
            - minimum temperature
            - maximum temperature
            - weather condition
            - data captured status
        */
        for (day in weatherArray) {
            day.minTempreture = 0
            day.maxTempreture = 0
            day.weatherCondition = ""
            day.dataCaptured = false
        }

        // Clears all input fields.
        edtMinTemp.text.clear()
        edtMaxTemp.text.clear()
        edtWeatherCondition.text.clear()

        // Resets the average temperature display.
        tvAverage.text = "Average Temperature:"

        // Displays confirmation message.
        Toast.makeText(
            this,
            "All weather data has been cleared",
            Toast.LENGTH_SHORT
        ).show()
    }
}