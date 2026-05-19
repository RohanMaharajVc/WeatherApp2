package com.rohan.weatherapp

data class WeatherDay(

    //Store the name of the day (e.g Monday, Tuesday - Sunday)
    val dayName: String,

    //Store minimum tempreture
    var minTempreture: Int = 0,

    //Store our maximum temp
    var maxTempreture: Int = 0,

    //Store our weather Condition (e.g sunny, rainy)
    var weatherCondition: String = "",

    //Tracks whether the user has entered data for this day
    var dataCaptured: Boolean = false


)
