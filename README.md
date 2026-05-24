# WeatherWay Weather Application

## Student Information

**Student Name:** Rohan Maharaj  
**Student Number:** ST10084484  

---

## GitHub Repository

**GitHub Repository Link:**  
[Insert GitHub Repository Link Here]

---

## Purpose of the Application

The purpose of the WeatherWay application is to allow users to capture, manage, calculate, and view weekly weather information in a simple and user-friendly Android application.

The application was developed using **Kotlin** in **Android Studio**. It demonstrates the use of arrays, loops, functions, screen navigation, data classes, input validation, and error handling.

The application allows users to:

- Enter minimum and maximum temperatures for each day of the week.
- Enter weather conditions such as Sunny, Rainy, Cloudy, Windy, or Cold.
- Save weather information for all seven days.
- Calculate and display the average weekly temperature.
- View detailed weather information for each day.
- Clear incorrect data and re-enter weather information.
- Navigate between the Splash Screen, Home Screen, and Detailed Weather Screen.

---

## Application Overview

The application contains three main screens:

### 1. Splash Screen

The Splash Screen is the first screen displayed when the application launches.

It includes:

- Application name
- Student name
- Student number
- Weather app logo
- Start button
- Exit button

The Start button allows the user to navigate to the Home Screen, while the Exit button closes the application.

### 2. Home Screen

The Home Screen is the main working screen of the application.

It includes:

- A Spinner to select the day of the week.
- An input field for minimum temperature.
- An input field for maximum temperature.
- An input field for the weather condition.
- A Save Weather button.
- A Calculate Average button.
- A View Detailed Weather button.
- A Clear Data button.
- An Exit button.

The Home Screen stores the user's weather information using an array of `WeatherDay` objects. The average temperature is calculated using a loop.

### 3. Detailed Weather Screen

The Detailed Weather Screen displays the weather information entered by the user.

It displays:

- Day name
- Minimum temperature
- Maximum temperature
- Weather condition

The screen also includes a Back button, allowing the user to return to the Home Screen.

---

## Programming Concepts Used

| Concept | Explanation |
|---|---|
| Kotlin | Used as the main programming language |
| Android Studio | Used as the development environment |
| ConstraintLayout | Used to design the app screens |
| Spinner | Used to allow the user to select a day of the week |
| EditText | Used to capture user input |
| Button | Used to trigger app actions |
| TextView | Used to display headings, average temperature, and weather details |
| Data Class | Used to store information for each day |
| Arrays | Used to store weekly weather objects |
| Loops | Used to calculate averages and display detailed data |
| Functions | Used to organise app logic |
| Toast Messages | Used to display error and confirmation messages |
| Intents | Used to navigate between screens |
| ScrollView | Used to allow scrolling on the detailed weather screen |

---

## Data Storage

The application uses a `WeatherDay` data class to store weather information for each day.

Each `WeatherDay` object stores:

- Day name
- Minimum temperature
- Maximum temperature
- Weather condition
- Whether data was captured for that day

The seven days of the week are stored in an array of `WeatherDay` objects.

---

# Pseudocode

## 1. Splash Screen Pseudocode

```text
START

Display Splash Screen

Display application logo
Display application name
Display student name
Display student number

IF Start button is clicked THEN
    Navigate to Home Screen
ENDIF

IF Exit button is clicked THEN
    Close the application
ENDIF

STOP
