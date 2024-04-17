package dev.micfro.javaapachehttpclientweatherapi;


import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

public class ApacheweatherAPI {

    public static void main(String[] args) {
        // Define the city for which to fetch the weather forecast
        String zip = "04177";

        // Fetch the weather forecast for the specified city
        fetchWeatherForecast(zip);
    }


    private static void fetchWeatherForecast(String theZip) {


        // Define the URL of the weather API endpoint
        String baseUrl = "http://api.openweathermap.org/data/2.5/weather";
        String apiKey = Config.getApiKey();
        String units = "metric";
        String zip = theZip;
        String Country = "de";
        String language = "de";
        String url = baseUrl + "?zip=" + zip + "," + Country + "&units=" + units + "&lang=" + language + "&appid=" + apiKey;

        CloseableHttpClient theHttpClient = null;

        try {
            theHttpClient = HttpClients.createDefault();
            HttpGet requestGET = new HttpGet(url);
            // Send the request
            String responseBody = theHttpClient.execute(requestGET, httpResponse -> EntityUtils.toString(httpResponse.getEntity()));

            // Parse JSON response
            JSONObject json = new JSONObject(responseBody);
            System.out.println("Weather data for: " + json.getString("name"));
            System.out.println("Temperature: " + json.getJSONObject("main").getDouble("temp") + "°C");
            System.out.println("Feels Like: " + json.getJSONObject("main").getDouble("feels_like") + "°C");
            System.out.println("Humidity: " + json.getJSONObject("main").getInt("humidity") + "%");
            System.out.println("Wind Speed: " + json.getJSONObject("wind").getDouble("speed") + " m/s");
            System.out.println("Conditions: " + json.getJSONArray("weather").getJSONObject(0).getString("description"));

        } catch (IOException e) {
            System.err.println("Error occurred while retrieving weather data: " + e.getMessage());
        }
    }
}


