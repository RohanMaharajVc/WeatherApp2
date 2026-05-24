# 🌦️ WeatherWay Weather Application

## 👤 Student Information

**Student Name:** Rohan Maharaj  
**Student Number:** ST10084484  

---

## 🔗 GitHub Repository

**GitHub Repository Link:**  
[Insert GitHub Repository Link Here]

---

## 📌 Purpose of the Application

The purpose of the **WeatherWay** application is to allow users to capture, manage, calculate, and view weekly weather information in a simple and user-friendly Android application.

The application was developed using **Kotlin** in **Android Studio**. It demonstrates the use of arrays, loops, functions, screen navigation, data classes, input validation, and error handling.

The application allows users to:

- 🌡️ Enter minimum and maximum temperatures for each day of the week.
- ☁️ Enter weather conditions such as Sunny, Rainy, Cloudy, Windy, or Cold.
- 💾 Save weather information for all seven days.
- 📊 Calculate and display the average weekly temperature.
- 📋 View detailed weather information for each day.
- 🧹 Clear incorrect data and re-enter weather information.
- 🔄 Navigate between the Splash Screen, Home Screen, and Detailed Weather Screen.

---

## 📱 Application Overview

The application contains three main screens:

---

### 1. 🌤️ Splash Screen

The **Splash Screen** is the first screen displayed when the application launches.

It includes:

- Application name
- Student name
- Student number
- Weather app logo
- Start button
- Exit button

The **Start** button allows the user to navigate to the Home Screen, while the **Exit** button closes the application.

---

### 2. 🏠 Home Screen

The **Home Screen** is the main working screen of the application.

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

---

### 3. 📋 Detailed Weather Screen

The **Detailed Weather Screen** displays the weather information entered by the user.

It displays:

- Day name
- Minimum temperature
- Maximum temperature
- Weather condition

The screen also includes a **Back** button, allowing the user to return to the Home Screen.

---

## 🧠 Programming Concepts Used

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

## 💾 Data Storage

The application uses a `WeatherDay` data class to store weather information for each day.

Each `WeatherDay` object stores:

- Day name
- Minimum temperature
- Maximum temperature
- Weather condition
- Whether data was captured for that day

The seven days of the week are stored in an array of `WeatherDay` objects.

---

# 🧾 Pseudocode

## 1. 🌤️ Splash Screen Pseudocode

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
```

---

## 2. 🏠 Home Screen Pseudocode

```text
START

Display Home Screen

Create array of WeatherDay objects
Populate array with Monday to Sunday

Display spinner with days of the week

Display input fields for:
    Minimum temperature
    Maximum temperature
    Weather condition

Display buttons for:
    Save Weather
    Calculate Average
    View Detailed Weather
    Clear Data
    Exit

IF Save Weather button is clicked THEN
    Run Save Weather Data process
ENDIF

IF Calculate Average button is clicked THEN
    Run Calculate Average Temperature process
ENDIF

IF View Detailed Weather button is clicked THEN
    IF all seven days have data THEN
        Navigate to Detailed Weather Screen
    ELSE
        Display error message
    ENDIF
ENDIF

IF Clear Data button is clicked THEN
    Run Clear Weather Data process
ENDIF

IF Exit button is clicked THEN
    Close the application
ENDIF

STOP
```

---

## 3. 💾 Save Weather Data Pseudocode

```text
START

Get selected day from spinner
Get minimum temperature input
Get maximum temperature input
Get weather condition input

IF minimum temperature is empty OR maximum temperature is empty OR weather condition is empty THEN
    Display error message: "Please complete all fields"
    STOP process
ENDIF

Convert minimum temperature to number
Convert maximum temperature to number

IF minimum temperature is not a valid number OR maximum temperature is not a valid number THEN
    Display error message: "Temperatures must be valid numbers"
    STOP process
ENDIF

IF minimum temperature is greater than maximum temperature THEN
    Display error message: "Minimum temperature cannot be greater than maximum temperature"
    STOP process
ENDIF

Store minimum temperature in selected WeatherDay object
Store maximum temperature in selected WeatherDay object
Store weather condition in selected WeatherDay object
Mark selected WeatherDay object as captured

Display confirmation message

Clear minimum temperature input
Clear maximum temperature input
Clear weather condition input

STOP
```

---

## 4. 📊 Calculate Average Temperature Pseudocode

```text
START

Check if all seven days have weather data

IF any day does not have weather data THEN
    Display error message: "Please enter all data for the 7 days before calculating"
    STOP process
ENDIF

Set total to 0

FOR each WeatherDay object in weekly weather array
    Add maximum temperature to total
ENDFOR

Calculate average = total / number of days

Display average temperature

STOP
```

---

## 5. ✅ Check All Data Entered Pseudocode

```text
START

FOR each WeatherDay object in weekly weather array
    IF dataCaptured is false THEN
        Return false
    ENDIF
ENDFOR

Return true

STOP
```

---

## 6. 🧹 Clear Weather Data Pseudocode

```text
START

FOR each WeatherDay object in weekly weather array
    Set minimum temperature to 0
    Set maximum temperature to 0
    Set weather condition to empty
    Set dataCaptured to false
ENDFOR

Clear minimum temperature input field
Clear maximum temperature input field
Clear weather condition input field

Reset average temperature display

Display message: "All weather data has been cleared"

STOP
```

---

## 7. 📋 Detailed Weather Screen Pseudocode

```text
START

Display Detailed Weather Screen

Create empty weather report string

FOR each WeatherDay object in weekly weather array
    Add day name to weather report
    Add minimum temperature to weather report
    Add maximum temperature to weather report
    Add weather condition to weather report
ENDFOR

Display weather report on screen

IF Back button is clicked THEN
    Return to Home Screen
ENDIF

STOP
```

---

# 📸 Screenshots

## Screenshot 1: Splash Screen

[Insert Screenshot 1 Here]

---

## Screenshot 2: Home Screen

[Insert Screenshot 2 Here]

---

## Screenshot 3: Detailed Weather Screen

[Insert Screenshot 3 Here]

---

## Screenshot 4: Error Message Example

[Insert Screenshot 4 Here]

---

# ⚠️ Error Handling

The application includes error handling for the following situations:

| Error | Message Displayed |
|---|---|
| Empty fields | Please complete all the fields |
| Invalid numbers | Temperatures must be valid numbers |
| Minimum temperature greater than maximum temperature | Minimum temperature cannot be greater than the maximum temperature |
| Attempting to calculate before all days are entered | Please enter all the data for the 7 days before calculating |
| Attempting to view details before all days are entered | Please enter data for all 7 days first |

---

# ✅ Conclusion

The **WeatherWay** application successfully meets the requirements of the task by allowing users to enter, save, calculate, clear, and view weekly weather information.

The app demonstrates the use of **Kotlin**, arrays, loops, functions, data classes, input validation, error handling, and screen navigation. It also includes a professional user interface with three main screens and a structured user experience.
