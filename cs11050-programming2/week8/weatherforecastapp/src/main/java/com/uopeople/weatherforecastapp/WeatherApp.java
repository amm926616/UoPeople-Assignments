package com.uopeople.weatherforecastapp;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WeatherApp extends Application {

    // Hardcoded API key
    private static final String API_KEY = "eb872952b9ee64125f9c6c0a3f65f1e1"; // Replace with your actual API key
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String FORECAST_API_URL = "http://api.openweathermap.org/data/2.5/forecast";

    private Label weatherInfoLabel = new Label("Weather information will appear here.");
    private ListView<String> forecastList = new ListView<>();
    private ListView<String> historyList = new ListView<>();
    private ImageView weatherIcon = new ImageView();
    private boolean isCelsius = true;

    @Override
    public void start(Stage primaryStage) {
        // Input Components
        TextField locationInput = new TextField();
        locationInput.setPromptText("Enter city name or coordinates (lat,lon)");

        Button fetchButton = new Button("Fetch Weather");
        ToggleButton unitToggle = new ToggleButton("°C/°F");

        // Layout for weather details
        VBox weatherDetails = new VBox(10, weatherIcon, weatherInfoLabel);
        weatherDetails.setAlignment(Pos.CENTER);
        weatherDetails.setPadding(new Insets(10));

        // Fetch button action
        fetchButton.setOnAction(event -> {
            String location = locationInput.getText().trim();
            if (!location.isEmpty()) {
                fetchWeather(location);
            } else {
                showError("Please enter a valid location.");
            }
        });

        // Unit toggle action
        unitToggle.setOnAction(event -> {
            isCelsius = !isCelsius;
            weatherInfoLabel.setText("Toggle units and refresh weather.");
        });

        // History click action
        historyList.setOnMouseClicked(event -> {
            String selected = historyList.getSelectionModel().getSelectedItem();
            if (selected != null) {
                fetchWeather(selected.split(" - ")[0]);
            }
        });

        // Main layout
        HBox inputSection = new HBox(10, locationInput, fetchButton, unitToggle);
        inputSection.setAlignment(Pos.CENTER);
        inputSection.setPadding(new Insets(10));

        VBox mainLayout = new VBox(10, inputSection, weatherDetails, new Label("5-Day Forecast:"), forecastList, new Label("Search History:"), historyList);
        mainLayout.setPadding(new Insets(10));
        mainLayout.setStyle("-fx-font-family: Arial; -fx-font-size: 14px;");

        // Scene and Stage
        Scene scene = new Scene(mainLayout, 600, 500);
        setDynamicBackground(scene);
        primaryStage.setTitle("Weather App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void fetchWeather(String location) {
        OkHttpClient client = new OkHttpClient();
        String weatherUrl = API_URL + "?q=" + location + "&appid=" + API_KEY + "&units=" + (isCelsius ? "metric" : "imperial");
        String forecastUrl = FORECAST_API_URL + "?q=" + location + "&appid=" + API_KEY + "&units=" + (isCelsius ? "metric" : "imperial");

        try {
            // Fetch current weather
            String weatherResponse = fetchApiData(client, weatherUrl);

            // Fetch forecast
            String forecastResponse = fetchApiData(client, forecastUrl);

            // Parse weather data
            Gson gson = new Gson();
            WeatherData weatherData = gson.fromJson(weatherResponse, WeatherData.class);
            ForecastData forecastData = gson.fromJson(forecastResponse, ForecastData.class);

            // Save to history
            saveToHistory(location, weatherResponse, forecastResponse);

            // Update UI
            updateWeatherUI(weatherData, forecastData);

        } catch (Exception e) {
            showError("Failed to fetch weather data: " + e.getMessage());
        }
    }

    private String fetchApiData(OkHttpClient client, String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            } else {
                throw new IOException("HTTP error: " + response.code());
            }
        }
    }

    private void saveToHistory(String location, String weather, String forecast) {
        try {
            File historyDir = new File("search_history");
            if (!historyDir.exists()) {
                historyDir.mkdir();
            }

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            FileWriter writer = new FileWriter(new File(historyDir, location + "_" + timestamp + "_weather.json"));
            writer.write(weather);
            writer.close();

            writer = new FileWriter(new File(historyDir, location + "_" + timestamp + "_forecast.json"));
            writer.write(forecast);
            writer.close();

            historyList.getItems().add(location + " - " + timestamp);

        } catch (IOException e) {
            System.err.println("Error saving search history: " + e.getMessage());
        }
    }

    private void updateWeatherUI(WeatherData weatherData, ForecastData forecastData) {
        // Displaying weather details, including new fields
        weatherInfoLabel.setText(String.format("Temperature: %.2f %s\n" +
                        "Humidity: %d%%\n" +
                        "Wind Speed: %.2f %s\n" +
                        "Cloudiness: %d%%\n" +
                        "Pressure: %d hPa\n" +
                        "Visibility: %d meters\n" +
                        "Condition: %s",
                weatherData.main.temp, isCelsius ? "°C" : "°F",
                weatherData.main.humidity,
                weatherData.wind.speed, isCelsius ? "m/s" : "mph",
                weatherData.clouds.all,
                weatherData.main.pressure,
                weatherData.visibility,
                weatherData.weather[0].description));

        // Setting weather icon
        weatherIcon.setImage(new Image("https://openweathermap.org/img/wn/" + weatherData.weather[0].icon + "@2x.png"));

        // Displaying forecast data
        forecastList.getItems().clear();
        for (ForecastData.Forecast forecast : forecastData.list) {
            forecastList.getItems().add(String.format("%s: %.2f %s, %s",
                    forecast.dt_txt, forecast.main.temp, isCelsius ? "°C" : "°F", forecast.weather[0].description));
        }
    }

    private void setDynamicBackground(Scene scene) {
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        String color = (hour >= 6 && hour <= 18) ? "lightblue" : "darkblue";
        scene.getRoot().setStyle("-fx-background-color: " + color + ";");
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }

    public static class WeatherData {
        public Main main;
        public Wind wind;
        public Weather[] weather;
        public Clouds clouds;  // New field for cloudiness
        public int visibility; // Visibility in meters
        public int pressure;   // Atmospheric pressure

        public static class Main {
            public double temp;
            public int humidity;
            public int pressure;  // Atmospheric pressure
        }

        public static class Wind {
            public double speed;
            public double deg;    // Wind direction
        }

        public static class Weather {
            public String description;
            public String icon;
        }

        public static class Clouds {
            public int all; // Cloudiness percentage
        }
    }

    public static class ForecastData {
        public Forecast[] list;

        public static class Forecast {
            public Main main;
            public Weather[] weather;
            public String dt_txt;

            public static class Main {
                public double temp;
            }

            public static class Weather {
                public String description;
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
