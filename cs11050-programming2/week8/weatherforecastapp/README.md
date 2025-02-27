# Weather Forecast App

This is a Java-based weather forecast application that allows users to fetch current weather data and a 5-day weather forecast for a given location. The app retrieves data from the OpenWeatherMap API and displays it in a simple, user-friendly interface built using JavaFX.

## Features

- **Current Weather**: Displays temperature, humidity, wind speed, cloudiness, pressure, and visibility.
- **5-Day Forecast**: Shows weather predictions for the next 5 days, including temperature and description of weather conditions.
- **History**: Stores the search history in a local directory, where each search is saved as a JSON file.
- **Unit Toggle**: Switches between Celsius and Fahrenheit.
- **Dynamic Background**: Changes the background color based on the time of day (light blue during the day, dark blue at night).

## Requirements

- Java 11 or later
- OpenWeatherMap API Key
- JavaFX libraries

## Installation

1. **Obtain OpenWeatherMap API Key**:
   - Create an account on [OpenWeatherMap](https://openweathermap.org/) and get your free API key.

2. **Setup Project**:
   - Extract the zip file you received and open the project folder.

3. **Replace the API Key**:
   - Open the `WeatherApp.java` file and replace `"your_api_key_here"` with your actual OpenWeatherMap API key.

4. **Install Dependencies**:
   - Ensure you have Java 11 or later installed on your system.
   - Add JavaFX libraries to your project. You can download them from the [OpenJFX website](https://openjfx.io/).

5. **Run the Application**:
   - Compile and run the `WeatherApp` class using your preferred IDE or command-line tools.

## Usage

1. **Enter Location**: Type a city name or geographic coordinates (latitude,longitude) into the input field and press the "Fetch Weather" button.
2. **View Weather**: The current weather will be displayed along with the 5-day forecast.
3. **Toggle Units**: Switch between Celsius and Fahrenheit by clicking the toggle button.
4. **View History**: Access previously searched locations from the history list. Click a history item to fetch weather data for that location again.

## Application Layout

- **Input Section**: Where users can input a location and request weather data.
- **Weather Info Section**: Displays the current weather, including temperature, humidity, wind speed, and more.
- **Forecast Section**: Displays a 5-day forecast.
- **History Section**: Lists previously searched locations and allows for easy re-fetching of data.

## Example Output

After entering a city (e.g., "London"), the app will display the following:

### Current Weather:
Temperature: 15.5°C Humidity: 70% Wind Speed: 4.5 m/s Cloudiness: 50% Pressure: 1012 hPa Visibility: 10000 meters Condition: Clear sky

### 5-Day Forecast:
2025-01-11 12:00:00: 16.0°C, Clear sky 2025-01-12 12:00:00: 18.2°C, Few clouds 2025-01-13 12:00:00: 17.0°C, Scattered clouds 2025-01-14 12:00:00: 14.5°C, Overcast clouds 2025-01-15 12:00:00: 13.0°C, Light rain

## File Structure

/weather-forecast-app /src WeatherApp.java # Main application code /search_history # Folder where search history is saved as JSON files README.md # This file