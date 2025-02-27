module com.uopeople.weatherforecastapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires okhttp3;
    requires java.dotenv;


    opens com.uopeople.weatherforecastapp to javafx.fxml;
    exports com.uopeople.weatherforecastapp;
}